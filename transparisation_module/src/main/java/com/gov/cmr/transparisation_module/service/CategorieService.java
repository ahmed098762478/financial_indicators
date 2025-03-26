package com.gov.cmr.transparisation_module.service;

import com.gov.cmr.transparisation_module.model.DTO.CategorieDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CategorieService {
    List<CategorieDTO> getAllCategories();
    CategorieDTO getCategorieById(String id);
    CategorieDTO createCategorie(CategorieDTO categorieDTO);
    CategorieDTO updateCategorie(String id, CategorieDTO categorieDTO);
    void deleteCategorie(String id);
    void importCategoriesFromExcel(MultipartFile file);


}
