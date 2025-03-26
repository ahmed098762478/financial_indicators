package com.gov.cmr.transparisation_module.service.impl;

import com.gov.cmr.transparisation_module.model.DTO.ReferentielTitreDTO;
import com.gov.cmr.transparisation_module.model.entitys.ReferentielTitre;
import com.gov.cmr.transparisation_module.repository.ReferentielTitreRepository;
import com.gov.cmr.transparisation_module.service.ReferentielTitreService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReferentielTitreServiceImpl implements ReferentielTitreService {

    private final ReferentielTitreRepository repository;

    public ReferentielTitreServiceImpl(ReferentielTitreRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ReferentielTitreDTO> getAll() {
        List<ReferentielTitre> entities = repository.findAll();
        List<ReferentielTitreDTO> dtos = new ArrayList<>();
        for (ReferentielTitre entity : entities) {
            dtos.add(mapToDTO(entity));
        }
        return dtos;
    }

    @Override
    public ReferentielTitreDTO getByCode(String code) {
        Optional<ReferentielTitre> optional = repository.findById(code);
        return optional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public ReferentielTitreDTO create(ReferentielTitreDTO dto) {
        ReferentielTitre entity = mapToEntity(dto);
        entity = repository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public ReferentielTitreDTO update(String code, ReferentielTitreDTO dto) {
        Optional<ReferentielTitre> optional = repository.findById(code);
        if (optional.isPresent()) {
            ReferentielTitre entity = optional.get();
            // Update fields from DTO
            entity.setCodeIsin(dto.getCodeIsin());
            entity.setDescription(dto.getDescription());
            entity.setLibCourt(dto.getLibCourt());
            entity.setFlagActif(dto.getFlagActif());
            entity.setTitrePere(dto.getTitrePere());
            entity.setClasse(dto.getClasse());
            entity.setCategorie(dto.getCategorie());
            entity.setEmetteur(dto.getEmetteur());
            entity.setFormeDetention(dto.getFormeDetention());
            entity.setSecteurEconomique(dto.getSecteurEconomique());
            entity.setNombreTitreEmis(dto.getNombreTitreEmis());
            entity.setNominal(dto.getNominal());
            entity.setTypeSpreadEmission(dto.getTypeSpreadEmission());
            entity.setSpreadEmission(dto.getSpreadEmission());
            entity.setPrixEmission(dto.getPrixEmission());
            entity.setPrimeRembou(dto.getPrimeRembou());
            entity.setQuotite(dto.getQuotite());
            entity.setDivision(dto.getDivision());
            entity.setTypeTaux(dto.getTypeTaux());
            entity.setValeurTaux(dto.getValeurTaux());
            entity.setMethodeCoupon(dto.getMethodeCoupon());
            entity.setPeriodiciteCoupon(dto.getPeriodiciteCoupon());
            entity.setPeriodiciteRembou(dto.getPeriodiciteRembou());
            entity.setBaseCalcul(dto.getBaseCalcul());
            entity.setTypePrecision(dto.getTypePrecision());
            entity.setDateEmission(dto.getDateEmission());
            entity.setDateJouissance(dto.getDateJouissance());
            entity.setDateEcheance(dto.getDateEcheance());
            entity.setDateMaj(dto.getDateMaj());
            entity.setGarantie(dto.getGarantie());
            entity.setTiersGarant(dto.getTiersGarant());
            entity.setCourbeTaux(dto.getCourbeTaux());
            entity.setMethodeValo(dto.getMethodeValo());
            entity.setTypeCotation(dto.getTypeCotation());
            entity.setPlaceCotation(dto.getPlaceCotation());
            entity.setMarche(dto.getMarche());
            entity.setGroupe1(dto.getGroupe1());
            entity.setGroupe2(dto.getGroupe2());
            entity.setGroupe3(dto.getGroupe3());
            entity.setDepositaire(dto.getDepositaire());
            entity.setDeviseCotation(dto.getDeviseCotation());

            entity = repository.save(entity);
            return mapToDTO(entity);
        }
        return null;
    }

    @Override
    public void delete(String code) {
        repository.deleteById(code);
    }

    @Override
    public void importFromExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum(); // get last row index (0-based)
            List<ReferentielTitre> entities = new ArrayList<>();

            // Start at 1 assuming row 0 is header
            for (int i = 1; i <= lastRowNum; i++) {
                Row row = sheet.getRow(i);
                if (row == null) {
                    continue; // skip empty rows
                }

                ReferentielTitre entity = new ReferentielTitre();

                // Mapping columns (0-based index)
                entity.setCode(getCellValueAsString(row.getCell(0)));
                entity.setCodeIsin(getCellValueAsString(row.getCell(1)));
                entity.setDescription(getCellValueAsString(row.getCell(2)));
                entity.setLibCourt(getCellValueAsString(row.getCell(3)));
                entity.setFlagActif(getCellValueAsString(row.getCell(4)));
                entity.setTitrePere(getCellValueAsString(row.getCell(5)));
                entity.setClasse(getCellValueAsString(row.getCell(6)));
                entity.setCategorie(getCellValueAsString(row.getCell(7)));
                entity.setEmetteur(getCellValueAsString(row.getCell(8)));
                entity.setFormeDetention(getCellValueAsString(row.getCell(9)));
                entity.setSecteurEconomique(getCellValueAsString(row.getCell(10)));

                String nombreTitreEmisStr = getCellValueAsString(row.getCell(11));
                entity.setNombreTitreEmis(nombreTitreEmisStr.isEmpty() ? null : Long.parseLong(nombreTitreEmisStr));

                String nominalStr = getCellValueAsString(row.getCell(12));
                entity.setNominal(nominalStr.isEmpty() ? null : new BigDecimal(nominalStr));

                entity.setTypeSpreadEmission(getCellValueAsString(row.getCell(13)));

                String spreadEmissionStr = getCellValueAsString(row.getCell(14));
                entity.setSpreadEmission(spreadEmissionStr.isEmpty() ? null : new BigDecimal(spreadEmissionStr));

                String prixEmissionStr = getCellValueAsString(row.getCell(15));
                entity.setPrixEmission(prixEmissionStr.isEmpty() ? null : new BigDecimal(prixEmissionStr));

                String primeRembouStr = getCellValueAsString(row.getCell(16));
                entity.setPrimeRembou(primeRembouStr.isEmpty() ? null : new BigDecimal(primeRembouStr));

                String quotiteStr = getCellValueAsString(row.getCell(17));
                entity.setQuotite(quotiteStr.isEmpty() ? null : new BigDecimal(quotiteStr));

                entity.setDivision(getCellValueAsString(row.getCell(18)));
                entity.setTypeTaux(getCellValueAsString(row.getCell(19)));

                String valeurTauxStr = getCellValueAsString(row.getCell(20));
                entity.setValeurTaux(valeurTauxStr.isEmpty() ? null : new BigDecimal(valeurTauxStr));

                entity.setMethodeCoupon(getCellValueAsString(row.getCell(21)));
                entity.setPeriodiciteCoupon(getCellValueAsString(row.getCell(22)));
                entity.setPeriodiciteRembou(getCellValueAsString(row.getCell(23)));
                entity.setBaseCalcul(getCellValueAsString(row.getCell(24)));
                entity.setTypePrecision(getCellValueAsString(row.getCell(25)));
                entity.setDateEmission(parseDateCell(row.getCell(26)));
                entity.setDateJouissance(parseDateCell(row.getCell(27)));
                entity.setDateEcheance(parseDateCell(row.getCell(28)));
                entity.setDateMaj(parseDateCell(row.getCell(29)));
                entity.setGarantie(getCellValueAsString(row.getCell(30)));
                entity.setTiersGarant(getCellValueAsString(row.getCell(31)));
                entity.setCourbeTaux(getCellValueAsString(row.getCell(32)));
                entity.setMethodeValo(getCellValueAsString(row.getCell(33)));
                entity.setTypeCotation(getCellValueAsString(row.getCell(34)));
                entity.setPlaceCotation(getCellValueAsString(row.getCell(35)));
                entity.setMarche(getCellValueAsString(row.getCell(36)));
                entity.setGroupe1(getCellValueAsString(row.getCell(37)));
                entity.setGroupe2(getCellValueAsString(row.getCell(38)));
                entity.setGroupe3(getCellValueAsString(row.getCell(39)));
                entity.setDepositaire(getCellValueAsString(row.getCell(40)));
                entity.setDeviseCotation(getCellValueAsString(row.getCell(41)));

                entities.add(entity);
            }

            // Optionally, if you're dealing with a very large number of rows,
            // you could flush in batches (e.g., every 500 rows)
            repository.saveAll(entities);
        } catch (Exception e) {
            throw new RuntimeException("Failed to import Excel data: " + e.getMessage());
        }
    }

    // Helper: get cell value as String
    private String getCellValueAsString(Cell cell) {
        if(cell == null) return "";
        switch(cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if(DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toLocalDate().toString();
                } else {
                    double numericValue = cell.getNumericCellValue();
                    if(numericValue == (long) numericValue) {
                        return String.valueOf((long) numericValue);
                    } else {
                        return String.valueOf(numericValue);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            default:
                return "";
        }
    }


    // Helper: parse date cell to LocalDate
    private LocalDate parseDateCell(Cell cell) {
        if(cell == null) return null;
        if(cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else if(cell.getCellType() == CellType.STRING) {
            String dateStr = cell.getStringCellValue().trim();
            if(!dateStr.isEmpty()){
                return LocalDate.parse(dateStr);
            }
        }
        return null;
    }

    // Mapper: entity to DTO
    private ReferentielTitreDTO mapToDTO(ReferentielTitre entity) {
        if(entity == null) return null;
        return ReferentielTitreDTO.builder()
                .code(entity.getCode())
                .codeIsin(entity.getCodeIsin())
                .description(entity.getDescription())
                .libCourt(entity.getLibCourt())
                .flagActif(entity.getFlagActif())
                .titrePere(entity.getTitrePere())
                .classe(entity.getClasse())
                .categorie(entity.getCategorie())
                .emetteur(entity.getEmetteur())
                .formeDetention(entity.getFormeDetention())
                .secteurEconomique(entity.getSecteurEconomique())
                .nombreTitreEmis(entity.getNombreTitreEmis())
                .nominal(entity.getNominal())
                .typeSpreadEmission(entity.getTypeSpreadEmission())
                .spreadEmission(entity.getSpreadEmission())
                .prixEmission(entity.getPrixEmission())
                .primeRembou(entity.getPrimeRembou())
                .quotite(entity.getQuotite())
                .division(entity.getDivision())
                .typeTaux(entity.getTypeTaux())
                .valeurTaux(entity.getValeurTaux())
                .methodeCoupon(entity.getMethodeCoupon())
                .periodiciteCoupon(entity.getPeriodiciteCoupon())
                .periodiciteRembou(entity.getPeriodiciteRembou())
                .baseCalcul(entity.getBaseCalcul())
                .typePrecision(entity.getTypePrecision())
                .dateEmission(entity.getDateEmission())
                .dateJouissance(entity.getDateJouissance())
                .dateEcheance(entity.getDateEcheance())
                .dateMaj(entity.getDateMaj())
                .garantie(entity.getGarantie())
                .tiersGarant(entity.getTiersGarant())
                .courbeTaux(entity.getCourbeTaux())
                .methodeValo(entity.getMethodeValo())
                .typeCotation(entity.getTypeCotation())
                .placeCotation(entity.getPlaceCotation())
                .marche(entity.getMarche())
                .groupe1(entity.getGroupe1())
                .groupe2(entity.getGroupe2())
                .groupe3(entity.getGroupe3())
                .depositaire(entity.getDepositaire())
                .deviseCotation(entity.getDeviseCotation())
                .build();
    }

    // Mapper: DTO to entity
    private ReferentielTitre mapToEntity(ReferentielTitreDTO dto) {
        if(dto == null) return null;
        return ReferentielTitre.builder()
                .code(dto.getCode())
                .codeIsin(dto.getCodeIsin())
                .description(dto.getDescription())
                .libCourt(dto.getLibCourt())
                .flagActif(dto.getFlagActif())
                .titrePere(dto.getTitrePere())
                .classe(dto.getClasse())
                .categorie(dto.getCategorie())
                .emetteur(dto.getEmetteur())
                .formeDetention(dto.getFormeDetention())
                .secteurEconomique(dto.getSecteurEconomique())
                .nombreTitreEmis(dto.getNombreTitreEmis())
                .nominal(dto.getNominal())
                .typeSpreadEmission(dto.getTypeSpreadEmission())
                .spreadEmission(dto.getSpreadEmission())
                .prixEmission(dto.getPrixEmission())
                .primeRembou(dto.getPrimeRembou())
                .quotite(dto.getQuotite())
                .division(dto.getDivision())
                .typeTaux(dto.getTypeTaux())
                .valeurTaux(dto.getValeurTaux())
                .methodeCoupon(dto.getMethodeCoupon())
                .periodiciteCoupon(dto.getPeriodiciteCoupon())
                .periodiciteRembou(dto.getPeriodiciteRembou())
                .baseCalcul(dto.getBaseCalcul())
                .typePrecision(dto.getTypePrecision())
                .dateEmission(dto.getDateEmission())
                .dateJouissance(dto.getDateJouissance())
                .dateEcheance(dto.getDateEcheance())
                .dateMaj(dto.getDateMaj())
                .garantie(dto.getGarantie())
                .tiersGarant(dto.getTiersGarant())
                .courbeTaux(dto.getCourbeTaux())
                .methodeValo(dto.getMethodeValo())
                .typeCotation(dto.getTypeCotation())
                .placeCotation(dto.getPlaceCotation())
                .marche(dto.getMarche())
                .groupe1(dto.getGroupe1())
                .groupe2(dto.getGroupe2())
                .groupe3(dto.getGroupe3())
                .depositaire(dto.getDepositaire())
                .deviseCotation(dto.getDeviseCotation())
                .build();
    }




}
