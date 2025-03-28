package com.gov.cmr.transparisation_module.service;

import com.gov.cmr.transparisation_module.model.DTO.AggregatedAllClassesDTO;
import com.gov.cmr.transparisation_module.model.DTO.SituationAvantGroupedDTO;
import com.gov.cmr.transparisation_module.model.DTO.SituationAvantTraitementDTO;
import java.util.List;

public interface SituationAvantTraitementService {
    List<SituationAvantTraitementDTO> getAll();
    SituationAvantTraitementDTO getById(Integer id);
    SituationAvantTraitementDTO create(SituationAvantTraitementDTO dto);
    SituationAvantTraitementDTO update(Integer id, SituationAvantTraitementDTO dto);
    void delete(Integer id);
    public AggregatedAllClassesDTO getAggregatedAllClasses();
}
