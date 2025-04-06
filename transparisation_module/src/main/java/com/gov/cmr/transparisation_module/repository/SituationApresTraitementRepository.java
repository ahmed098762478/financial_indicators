package com.gov.cmr.transparisation_module.repository;

import com.gov.cmr.transparisation_module.model.entitys.SituationApresTraitement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@Repository
public interface SituationApresTraitementRepository extends JpaRepository<SituationApresTraitement, Integer> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = "DELETE FROM situation_apres_traitement", nativeQuery = true)
    void clearAll();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
        INSERT INTO situation_apres_traitement (categorie, valeur_comptable, valeur_marche, date_creation)
        SELECT
            sa.categorie,
            sa.valeur_comptable - COALESCE(SUM(f.pdr_total_net), 0) / 100,
            sa.valeur_marche - COALESCE(SUM(f.total_valo), 0) / 100,
            CURRENT_DATE
        FROM situation_avant_traitement sa
        LEFT JOIN fiche_portefeuille f ON f.description = sa.categorie
        WHERE sa.is_situation_avant = TRUE
        GROUP BY sa.categorie, sa.valeur_comptable, sa.valeur_marche
        """, nativeQuery = true)
    void insertFromSituationAvant();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query(value = """
    INSERT INTO situation_apres_traitement (categorie, valeur_comptable, valeur_marche, date_creation)
    SELECT
        t.categorie || suffix,
        (SUM(f.pdr_total_net *
            CASE suffix
                WHEN '__PP' THEN t.dette_privee
                WHEN '_PB' THEN t.dette_public
                WHEN '_act' THEN t.action
            END) / 100)::numeric(20,2),
        (SUM(f.total_valo *
            CASE suffix
                WHEN '__PP' THEN t.dette_privee
                WHEN '_PB' THEN t.dette_public
                WHEN '_act' THEN t.action
            END) / 100)::numeric(20,2),
        CURRENT_DATE
    FROM fiche_portefeuille f
    JOIN trans_tempo t ON f.description = t.description
    CROSS JOIN (VALUES ('__PP'), ('_PB'), ('_act')) AS suffixes(suffix)
    GROUP BY t.categorie, suffix
    """, nativeQuery = true)
    void insertFromTransTempo();

    // Ajoutez cette méthode pour forcer le rafraîchissement
    @Query(value = "SELECT 1", nativeQuery = true)
    void refresh();
}

