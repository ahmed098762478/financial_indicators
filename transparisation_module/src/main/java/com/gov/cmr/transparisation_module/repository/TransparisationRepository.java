package com.gov.cmr.transparisation_module.repository;

import com.gov.cmr.transparisation_module.model.entitys.Transparisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransparisationRepository extends JpaRepository<Transparisation, Integer> {

    @Query("SELECT DISTINCT t FROM Transparisation t WHERE " +
            "(:targetDate BETWEEN t.dateImage AND t.dateImageFin OR " +
            "(t.dateImage <= :targetDate AND t.dateImageFin = {d '9999-12-31'})) " +
            "AND t.dettePublic IS NOT NULL AND t.dettePublic != 100 " +
            "AND t.dettePrivee IS NOT NULL AND t.dettePrivee != 100 " +
            "AND t.action IS NOT NULL AND t.action != 100")
    List<Transparisation> findByTargetDate(@Param("targetDate") LocalDate targetDate);

    @Modifying
    @Query(value = """
        DROP TABLE IF EXISTS trans_tempo;
        CREATE TABLE trans_tempo AS
        SELECT DISTINCT
            date_image,
            date_image_fin,
            titre,
            code_isin,
            description,
            categorie,
            dette_public,
            dette_privee,
            action
        FROM transparisation
        WHERE
            (:targetDate BETWEEN date_image AND date_image_fin
            OR (date_image <= :targetDate AND date_image_fin = '9999-12-31'))
            AND dette_public IS NOT NULL AND dette_public != 100
            AND dette_privee IS NOT NULL AND dette_privee != 100
            AND action IS NOT NULL AND action != 100
        """,
            nativeQuery = true)
    void createTransTempo(@Param("targetDate") LocalDate targetDate);
}