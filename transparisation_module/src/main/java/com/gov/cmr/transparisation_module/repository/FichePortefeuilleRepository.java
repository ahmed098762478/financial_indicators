package com.gov.cmr.transparisation_module.repository;

import com.gov.cmr.transparisation_module.model.entitys.FichePortefeuille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.gov.cmr.transparisation_module.model.DTO.FichePortefeuilleSummary;

import java.util.List;

@Repository
public interface FichePortefeuilleRepository extends JpaRepository<FichePortefeuille, Integer> {

    @Query(value = "SELECT act, SUM(pdr_total_net) AS valeur_marche, SUM(total_valo) AS valeur_comptable " +
            "FROM fiche_portefeuille " +
            "GROUP BY act",
            nativeQuery = true)
    List<FichePortefeuilleSummary> findFichePortefeuilleSummary();

}