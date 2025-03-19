package com.gov.cmr.transparisation_module.repository;

import com.gov.cmr.transparisation_module.model.entitys.FichePortefeuille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FichePortefeuilleRepository extends JpaRepository<FichePortefeuille, Integer> {
}