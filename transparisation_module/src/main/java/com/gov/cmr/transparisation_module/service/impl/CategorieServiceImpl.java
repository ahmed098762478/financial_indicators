package com.gov.cmr.transparisation_module.service.impl;

import com.gov.cmr.transparisation_module.model.DTO.CategorieDTO;
import com.gov.cmr.transparisation_module.model.entitys.Categorie;
import com.gov.cmr.transparisation_module.repository.CategorieRepository;
import com.gov.cmr.transparisation_module.service.CategorieService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public List<CategorieDTO> getAllCategories() {
        List<Categorie> categories = categorieRepository.findAll();
        List<CategorieDTO> dtos = new ArrayList<>();
        for (Categorie cat : categories) {
            dtos.add(mapToDTO(cat));
        }
        return dtos;
    }

    @Override
    public CategorieDTO getCategorieById(String id) {
        Optional<Categorie> optional = categorieRepository.findById(id);
        return optional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public CategorieDTO createCategorie(CategorieDTO categorieDTO) {
        Categorie categorie = mapToEntity(categorieDTO);
        categorie = categorieRepository.save(categorie);
        return mapToDTO(categorie);
    }

    @Override
    public CategorieDTO updateCategorie(String id, CategorieDTO categorieDTO) {
        Optional<Categorie> optional = categorieRepository.findById(id);
        if (optional.isPresent()) {
            Categorie categorie = optional.get();
            // Update the modifiable fields
            categorie.setClasseReglementaire(categorieDTO.getClasseReglementaire());
            categorie.setClasse(categorieDTO.getClasse());
            categorie = categorieRepository.save(categorie);
            return mapToDTO(categorie);
        }
        return null;
    }

    @Override
    public void deleteCategorie(String id) {
        categorieRepository.deleteById(id);
    }

    @Override
    public void importCategoriesFromExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Skip header row
            if (rows.hasNext()) rows.next();

            List<Categorie> categories = new ArrayList<>();
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                String categorieStr = currentRow.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString();
                String classeReglementaireStr = currentRow.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString();
                String classeStr = currentRow.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).toString();

                Categorie categorie = Categorie.builder()
                        .categorie(categorieStr)
                        .classeReglementaire(classeReglementaireStr)
                        .classe(classeStr)
                        .build();

                categories.add(categorie);
            }

            categorieRepository.saveAll(categories);
            System.out.println("✔ Categories imported successfully from Excel.");
        } catch (Exception e) {
            throw new RuntimeException("❌ Failed to import Excel data: " + e.getMessage(), e);
        }
    }


    // Private mapper methods inside the service

    private CategorieDTO mapToDTO(Categorie categorie) {
        if (categorie == null) {
            return null;
        }
        return CategorieDTO.builder()
                .categorie(categorie.getCategorie())
                .classeReglementaire(categorie.getClasseReglementaire())
                .classe(categorie.getClasse())
                .build();
    }

    private Categorie mapToEntity(CategorieDTO dto) {
        if (dto == null) {
            return null;
        }
        return Categorie.builder()
                .categorie(dto.getCategorie())
                .classeReglementaire(dto.getClasseReglementaire())
                .classe(dto.getClasse())
                .build();
    }
}
