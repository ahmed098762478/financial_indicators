package com.gov.cmr.transparisation_module.service;

import com.gov.cmr.transparisation_module.model.DTO.ReferentielTitreDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReferentielTitreService {
    List<ReferentielTitreDTO> getAll();
    ReferentielTitreDTO getByCode(String code);
    ReferentielTitreDTO create(ReferentielTitreDTO dto);
    ReferentielTitreDTO update(String code, ReferentielTitreDTO dto);
    void delete(String code);
    void importFromExcel(MultipartFile file);
}
