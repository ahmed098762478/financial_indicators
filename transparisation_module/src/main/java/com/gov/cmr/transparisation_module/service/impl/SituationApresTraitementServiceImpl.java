package com.gov.cmr.transparisation_module.service.impl;

import com.gov.cmr.transparisation_module.model.DTO.SituationApresTraitementDTO;
import com.gov.cmr.transparisation_module.model.entitys.SituationApresTraitement;
import com.gov.cmr.transparisation_module.repository.SituationApresTraitementRepository;
import com.gov.cmr.transparisation_module.service.SituationApresTraitementService;
import com.jayway.jsonpath.spi.mapper.MappingException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
    private final EntityManager entityManager;

    public SituationApresTraitementServiceImpl(SituationApresTraitementRepository repository,
                                               EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    @Override
    public List<SituationApresTraitementDTO> calculateAndSaveSituation() {
        try {
            log.info("Clearing existing data...");
            int deleted = repository.clearAll();
            log.info("Deleted {} rows", deleted);

            entityManager.flush();

            log.info("Inserting from situation_avant...");
            int insertedFromAvant = repository.insertFromSituationAvant();
            log.info("Inserted {} rows from situation_avant", insertedFromAvant);

            log.info("Inserting from trans_tempo...");
            int insertedFromTempo = repository.insertFromTransTempo();
            log.info("Inserted {} rows from trans_tempo", insertedFromTempo);

            entityManager.flush();

            List<SituationApresTraitement> results = repository.findAll();
            log.info("Final dataset contains {} items", results.size());

            return results.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            log.error("Calculation error", e);
            throw new RuntimeException("Calculation failed", e);
        }
    }


    @Override
    public List<SituationApresTraitementDTO> getAll() {
        try {
            // Nettoyage du cache de persistence
            entityManager.flush();
            entityManager.clear();

            // Récupération des données
            List<SituationApresTraitement> entities = repository.findAll();

            log.debug("Nombre d'entités récupérées: {}", entities.size());
            return entities.stream()
                    .map(this::mapToDTO)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("Erreur lors de la récupération des données", e);
            throw new RuntimeException("Erreur d'accès à la base de données", e);
        }
    }

    private SituationApresTraitementDTO mapToDTO(SituationApresTraitement entity) {
        if (entity == null) {
            log.warn("Tentative de mapper une entité nulle");
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
            log.error("Erreur de mapping pour l'entité: {}", entity, e);
            throw new MappingException("Échec du mapping SituationApresTraitement vers DTO");
        }
    }
}