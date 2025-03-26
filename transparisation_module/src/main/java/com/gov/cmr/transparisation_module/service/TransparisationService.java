package com.gov.cmr.transparisation_module.service;

import com.gov.cmr.transparisation_module.model.DTO.TransparisationDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TransparisationService {
    List<TransparisationDTO> getAll();
    TransparisationDTO getById(String titre);
    TransparisationDTO create(TransparisationDTO dto);
    TransparisationDTO update(String titre, TransparisationDTO dto);
    void delete(String titre);
    void importFromExcel(MultipartFile file);
}
