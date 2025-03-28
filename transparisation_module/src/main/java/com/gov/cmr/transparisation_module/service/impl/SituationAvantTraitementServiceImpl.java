package com.gov.cmr.transparisation_module.service.impl;

import com.gov.cmr.transparisation_module.model.DTO.*;
import com.gov.cmr.transparisation_module.model.entitys.SituationAvantTraitement;
import com.gov.cmr.transparisation_module.repository.SituationAvantTraitementRepository;
import com.gov.cmr.transparisation_module.service.SituationAvantTraitementService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SituationAvantTraitementServiceImpl implements SituationAvantTraitementService {

    private final SituationAvantTraitementRepository repository;

    public SituationAvantTraitementServiceImpl(SituationAvantTraitementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SituationAvantTraitementDTO> getAll() {
        List<SituationAvantTraitement> entities = repository.findAll();
        List<SituationAvantTraitementDTO> dtos = new ArrayList<>();
        for (SituationAvantTraitement entity : entities) {
            dtos.add(mapToDTO(entity));
        }
        return dtos;
    }

    @Override
    public SituationAvantTraitementDTO getById(Integer id) {
        Optional<SituationAvantTraitement> optional = repository.findById(id);
        return optional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public SituationAvantTraitementDTO create(SituationAvantTraitementDTO dto) {
        SituationAvantTraitement entity = mapToEntity(dto);
        entity = repository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public SituationAvantTraitementDTO update(Integer id, SituationAvantTraitementDTO dto) {
        Optional<SituationAvantTraitement> optional = repository.findById(id);
        if (optional.isPresent()) {
            SituationAvantTraitement entity = optional.get();
            // Do not update the id
            entity.setIsSituationAvant(dto.getIsSituationAvant());
            entity.setPtf(dto.getPtf());
            entity.setDateEnCours(dto.getDateEnCours());
            entity.setCategorie(dto.getCategorie());
            entity.setValeurMarche(dto.getValeurMarche());
            entity.setValeurComptable(dto.getValeurComptable());
            entity = repository.save(entity);
            return mapToDTO(entity);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }

    // Mapper: Entity to DTO
    private SituationAvantTraitementDTO mapToDTO(SituationAvantTraitement entity) {
        if (entity == null) return null;
        return SituationAvantTraitementDTO.builder()
                .idSituation(entity.getIdSituation())
                .isSituationAvant(entity.getIsSituationAvant())
                .ptf(entity.getPtf())
                .dateEnCours(entity.getDateEnCours())
                .categorie(entity.getCategorie())
                .valeurMarche(entity.getValeurMarche())
                .valeurComptable(entity.getValeurComptable())
                .build();
    }

    // Mapper: DTO to Entity
    private SituationAvantTraitement mapToEntity(SituationAvantTraitementDTO dto) {
        if (dto == null) return null;
        return SituationAvantTraitement.builder()
                .idSituation(dto.getIdSituation())
                .isSituationAvant(dto.getIsSituationAvant())
                .ptf(dto.getPtf())
                .dateEnCours(dto.getDateEnCours())
                .categorie(dto.getCategorie())
                .valeurMarche(dto.getValeurMarche())
                .valeurComptable(dto.getValeurComptable())
                .build();
    }

    @Override
    public AggregatedAllClassesDTO getAggregatedAllClasses() {
        List<SituationAvantTraitement> all = repository.findAll();

        // ---------------------------
        // 1) Define numeric accumulators for each "line" you want to show
        //    We'll track Valeur Comptable (VC) and Valeur Marché (VM) for each row
        // -- Class I
        double bdtVC = 0, bdtVM = 0;
        double vjgVC = 0, vjgVM = 0;
        double opciEtatVC = 0, opciEtatVM = 0;      // OPCI_Publique + OPCI_TR
        double omltPursVC = 0, omltPursVM = 0;      // "OMLT_Purs" or "OPCVM Obligataires Purs"
        double opEncoursVC = 0, opEncoursVM = 0;      // e.g. “opération encours” => category = "op" ?
        double omltPbVC = 0, omltPbVM = 0;            // if you have OMLT_PB?

        // -- Class II
        double cdVC = 0, cdVM = 0;
        double ocVC = 0, ocVM = 0;
        double oncVC = 0, oncVM = 0;
        double monetaireVC = 0, monetaireVM = 0;
        double omltVC = 0, omltVM = 0;  // "OMLT" accumulator will also add "omlt_tr" values
        double omltPrVC = 0, omltPrVM = 0;    // If you have "OMLT_PR"
        double omltDedVC = 0, omltDedVM = 0;    // "OMLT Déd."

        // -- Class III
        double actVC = 0, actVM = 0;            // sum of categories ACT + PART
        double opcvmActDivVC = 0, opcvmActDivVM = 0;  // “Actions” + “Diversifié” + “Diversifié_TR”
        double fpctVC = 0, fpctVM = 0;          // If "FPCT" is a single cat
        double actionsDedVC = 0, actionsDedVM = 0;    // “Actions Déd.”
        double omltActVC = 0, omltActVM = 0;    // "OMLT_act" if it exists
        double fondsCapRisqueVC = 0, fondsCapRisqueVM = 0; // "OPCR"

        // -- Class IV
        double opciPriveVC = 0, opciPriveVM = 0; // "OPCIRFA"
        double opciPbTrVC = 0, opciPbTrVM = 0;   // "OPCI PB_TR"?
        double fondsInvVC = 0, fondsInvVM = 0;    // "FondsInv"

        // ---------------------------
        // 2) Loop over DB rows, sum to correct accumulator
        for (SituationAvantTraitement row : all) {
            double vc = (row.getValeurComptable() == null) ? 0.0 : row.getValeurComptable();
            double vm = (row.getValeurMarche() == null) ? 0.0 : row.getValeurMarche();
            String cat = row.getCategorie();

            switch (cat) {
                // ----- Class I -----
                case "BDT":
                    bdtVC += vc; bdtVM += vm;
                    break;
                case "VJG":
                    vjgVC += vc; vjgVM += vm;
                    break;
                case "OPCI_Publique":
                case "OPCI_TR":
                    opciEtatVC += vc; opciEtatVM += vm;
                    break;
                case "OMLT Purs":
                    omltPursVC += vc; omltPursVM += vm;
                    break;
                case "op": // if your “opération encours” is category="op"
                    opEncoursVC += vc; opEncoursVM += vm;
                    break;
                case "OMLT_PB": // if you have it
                    omltPbVC += vc; omltPbVM += vm;
                    break;

                // ----- Class II -----
                case "CD":
                    cdVC += vc; cdVM += vm;
                    break;
                case "OC":
                    ocVC += vc; ocVM += vm;
                    break;
                case "ONC":
                    oncVC += vc; oncVM += vm;
                    break;
                case "Monétaire":
                    monetaireVC += vc; monetaireVM += vm;
                    break;
                // Combine "OMLT" and "omlt_tr" values in the same accumulator
                case "OMLT":
                case "OMLT_TR":
                    omltVC += vc; omltVM += vm;
                    break;
                case "OMLT_PR":
                    omltPrVC += vc; omltPrVM += vm;
                    break;
                case "OMLT Déd.":
                case "OMLT_Déd":
                    omltDedVC += vc; omltDedVM += vm;
                    break;

                // ----- Class III -----
                case "ACT":
                case "PART":
                    actVC += vc; actVM += vm;
                    break;
                case "Actions":
                case "Diversifié":
                case "Diversifié_TR":
                    opcvmActDivVC += vc; opcvmActDivVM += vm;
                    break;
                case "FPCT":
                    fpctVC += vc; fpctVM += vm;
                    break;
                case "Actions Déd.":
                    actionsDedVC += vc; actionsDedVM += vm;
                    break;
                case "OMLT_act":
                    omltActVC += vc; omltActVM += vm;
                    break;
                case "OPCR":
                    fondsCapRisqueVC += vc; fondsCapRisqueVM += vm;
                    break;

                // ----- Class IV -----
                case "OPCIRFA":  // => “OPCI privé”
                    opciPriveVC += vc; opciPriveVM += vm;
                    break;
                case "OPCI PB_TR":
                    opciPbTrVC += vc; opciPbTrVM += vm;
                    break;
                case "FondsInv":
                    fondsInvVC += vc; fondsInvVM += vm;
                    break;

                default:
                    // Unrecognized category – you may log or skip it
                    break;
            }
        }

        // ---------------------------
        // 3) Compute Class totals
        double classIVC = bdtVC + vjgVC + opciEtatVC + omltPursVC + opEncoursVC + omltPbVC;
        double classIVM = bdtVM + vjgVM + opciEtatVM + omltPursVM + opEncoursVM + omltPbVM;

        double classIIVC = cdVC + ocVC + oncVC + monetaireVC + omltVC + omltPrVC + omltDedVC;
        double classIIVM = cdVM + ocVM + oncVM + monetaireVM + omltVM + omltPrVM + omltDedVM;

        double classIIIVC = actVC + opcvmActDivVC + fpctVC + actionsDedVC + omltActVC + fondsCapRisqueVC;
        double classIIIVM = actVM + opcvmActDivVM + fpctVM + actionsDedVM + omltActVM + fondsCapRisqueVM;

        double classIVVC = opciPriveVC + opciPbTrVC + fondsInvVC;
        double classIVVM = opciPriveVM + opciPbTrVM + fondsInvVM;

        // 4) Grand total (all classes)
        double grandTotalVC = classIVC + classIIVC + classIIIVC + classIVVC;
        double grandTotalVM = classIVM + classIIVM + classIIIVM + classIVVM;

        // 5) Compute each class ratio vs. grand total (in percentage)
        double ratioI = (grandTotalVC == 0) ? 0 : (classIVC / grandTotalVC) * 100;
        double ratioII = (grandTotalVC == 0) ? 0 : (classIIVC / grandTotalVC) * 100;
        double ratioIII = (grandTotalVC == 0) ? 0 : (classIIIVC / grandTotalVC) * 100;
        double ratioIV = (grandTotalVC == 0) ? 0 : (classIVVC / grandTotalVC) * 100;

        // ---------------------------
        // 6) Format everything nicely to strings
        DecimalFormat df = new DecimalFormat("#,##0.00");
        DecimalFormat pdf = new DecimalFormat("#0.00'%'");  // for percentages like "44.22%"

        // Build Class I sub-DTO
        ClassIDTO classI = ClassIDTO.builder()
                .bdtVC(df.format(bdtVC)).bdtVM(df.format(bdtVM))
                .vjgVC(df.format(vjgVC)).vjgVM(df.format(vjgVM))
                .opciEtatVC(df.format(opciEtatVC)).opciEtatVM(df.format(opciEtatVM))
                .omltPursVC(df.format(omltPursVC)).omltPursVM(df.format(omltPursVM))
                .operationEncoursVC(df.format(opEncoursVC)).operationEncoursVM(df.format(opEncoursVM))
                .omltPbVC(df.format(omltPbVC)).omltPbVM(df.format(omltPbVM))
                .totalClassIVC(df.format(classIVC))
                .totalClassIVM(df.format(classIVM))
                .ratioI(pdf.format(ratioI))
                .build();

        // Build Class II sub-DTO
        ClassIIDTO classII = ClassIIDTO.builder()
                .cdVC(df.format(cdVC)).cdVM(df.format(cdVM))
                .ocVC(df.format(ocVC)).ocVM(df.format(ocVM))
                .oncVC(df.format(oncVC)).oncVM(df.format(oncVM))
                .monetaireVC(df.format(monetaireVC)).monetaireVM(df.format(monetaireVM))
                .omltVC(df.format(omltVC)).omltVM(df.format(omltVM))
                .omltPrVC(df.format(omltPrVC)).omltPrVM(df.format(omltPrVM))
                .omltDedVC(df.format(omltDedVC)).omltDedVM(df.format(omltDedVM))
                .totalClassIIVC(df.format(classIIVC))
                .totalClassIIVM(df.format(classIIVM))
                .ratioII(pdf.format(ratioII))
                .build();

        // Build Class III sub-DTO
        ClassIIIDTO classIII = ClassIIIDTO.builder()
                .actVC(df.format(actVC)).actVM(df.format(actVM))
                .opcvmActDivVC(df.format(opcvmActDivVC)).opcvmActDivVM(df.format(opcvmActDivVM))
                .fpctVC(df.format(fpctVC)).fpctVM(df.format(fpctVM))
                .actionsDedVC(df.format(actionsDedVC)).actionsDedVM(df.format(actionsDedVM))
                .omltActVC(df.format(omltActVC)).omltActVM(df.format(omltActVM))
                .fondsCapRisqueVC(df.format(fondsCapRisqueVC)).fondsCapRisqueVM(df.format(fondsCapRisqueVM))
                .totalClassIIIVC(df.format(classIIIVC))
                .totalClassIIIVM(df.format(classIIIVM))
                .ratioIII(pdf.format(ratioIII))
                .build();

        // Build Class IV sub-DTO
        ClassIVDTO classIV = ClassIVDTO.builder()
                .opciPriveVC(df.format(opciPriveVC)).opciPriveVM(df.format(opciPriveVM))
                .opciPbTrVC(df.format(opciPbTrVC)).opciPbTrVM(df.format(opciPbTrVM))
                .fondsInvVC(df.format(fondsInvVC)).fondsInvVM(df.format(fondsInvVM))
                .totalClassIVC(df.format(classIVVC))
                .totalClassIVM(df.format(classIVVM))
                .ratioIV(pdf.format(ratioIV))
                .build();

        // Construct and return the main DTO
        return AggregatedAllClassesDTO.builder()
                .classI(classI)
                .classII(classII)
                .classIII(classIII)
                .classIV(classIV)
                .grandTotalVC(df.format(grandTotalVC))
                .grandTotalVM(df.format(grandTotalVM))
                .build();
    }


}
