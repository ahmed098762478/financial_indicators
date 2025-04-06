package com.gov.cmr.transparisation_module.service.impl;

import com.gov.cmr.transparisation_module.model.DTO.SituationApresTraitementDTO;
import com.gov.cmr.transparisation_module.model.DTO.TransparisationDTO;
import com.gov.cmr.transparisation_module.model.entitys.SituationApresTraitement;
import com.gov.cmr.transparisation_module.repository.SituationApresTraitementRepository;
import com.gov.cmr.transparisation_module.service.SituationApresTraitementService;
import com.jayway.jsonpath.spi.mapper.MappingException;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j

public class SituationApresTraitementServiceImpl implements SituationApresTraitementService {

    private final SituationApresTraitementRepository repository;

    public SituationApresTraitementServiceImpl(SituationApresTraitementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SituationApresTraitementDTO> calculateAndSaveSituation() {
        try {
            log.info("Nettoyage des données...");
            repository.clearAll();
            repository.refresh(); // Force le rafraîchissement

            log.info("Insertion depuis situation_avant...");
            repository.insertFromSituationAvant();
            repository.refresh();

            log.info("Insertion depuis trans_tempo...");
            repository.insertFromTransTempo();
            repository.refresh();

            return repository.findAll().stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Erreur de calcul", e);
            throw new RuntimeException("Calcul échoué: " + e.getMessage());
        }
    }

    @Override
     @Transactional
      public List<SituationApresTraitementDTO> getAll() {
        try {
            // Méthode 1 : Utilisation directe du repository
            List<SituationApresTraitement> entities = repository.findAll();

            // Méthode alternative si problème de cache :
            EntityManager em = null;
            entities = em.createQuery(
                    "SELECT s FROM situation_apres_traitement s", SituationApresTraitement.class).getResultList();

            log.debug("DB returned {} entities", entities.size());
            return entities.stream()
                    .map(this::mapToDTO)
                    .toList();

        } catch (Exception e) {
            log.error("Service error", e);
            throw new RuntimeException("Database access error", e);
        }
    }

    private SituationApresTraitementDTO mapToDTO(SituationApresTraitement entity) {
        if (entity == null) {
            log.warn("Attempt to map null SituationApresTraitement entity");
            return null;
        }

        try {
            return SituationApresTraitementDTO.builder()
                    .idSituation(entity.getIdSituation())
                    .categorie(StringUtils.hasText(entity.getCategorie()) ?
                            entity.getCategorie() : "N/A")
                    .valeurComptable(entity.getValeurComptable() != null ?
                            entity.getValeurComptable() : 0.0)
                    .valeurMarche(entity.getValeurMarche() != null ?
                            entity.getValeurMarche() : 0.0)
                    .dateCreation(entity.getDateCreation() != null ?
                            entity.getDateCreation() : LocalDate.now())
                    .build();
        } catch (Exception e) {
            log.error("Mapping error for entity: {}", entity, e);
            throw new MappingException("Failed to map SituationApresTraitement to DTO");
        }
    }
}