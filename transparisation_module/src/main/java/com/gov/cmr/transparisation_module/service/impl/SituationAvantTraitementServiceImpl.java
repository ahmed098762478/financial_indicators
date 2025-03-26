package com.gov.cmr.transparisation_module.service.impl;

import com.gov.cmr.transparisation_module.model.DTO.SituationAvantTraitementDTO;
import com.gov.cmr.transparisation_module.model.entitys.SituationAvantTraitement;
import com.gov.cmr.transparisation_module.repository.SituationAvantTraitementRepository;
import com.gov.cmr.transparisation_module.service.SituationAvantTraitementService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
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
}
