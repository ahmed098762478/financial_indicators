package com.gov.cmr.transparisation_module.service;

import com.gov.cmr.transparisation_module.model.DTO.SituationApresTraitementDTO;

import java.time.LocalDate;
import java.util.List;

public interface SituationApresTraitementService {
    List<SituationApresTraitementDTO> calculateAndSaveSituation();
    List<SituationApresTraitementDTO> getAll();
}