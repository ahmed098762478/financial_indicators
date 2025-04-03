package com.gov.cmr.transparisation_module.repository;

import com.gov.cmr.transparisation_module.model.entitys.Transparisation;
import org.springframework.data.jpa.repository.JpaRepository;
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
}