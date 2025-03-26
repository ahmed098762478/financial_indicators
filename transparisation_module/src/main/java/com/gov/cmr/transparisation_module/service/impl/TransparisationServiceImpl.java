package com.gov.cmr.transparisation_module.service.impl;

import com.gov.cmr.transparisation_module.model.DTO.TransparisationDTO;
import com.gov.cmr.transparisation_module.model.entitys.Transparisation;
import com.gov.cmr.transparisation_module.repository.TransparisationRepository;
import com.gov.cmr.transparisation_module.service.TransparisationService;
import jakarta.transaction.Transactional;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional
public class TransparisationServiceImpl implements TransparisationService {

    private final TransparisationRepository repository;

    public TransparisationServiceImpl(TransparisationRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<TransparisationDTO> getAll() {
        List<Transparisation> entities = repository.findAll();
        List<TransparisationDTO> dtos = new ArrayList<>();
        for (Transparisation entity : entities) {
            dtos.add(mapToDTO(entity));
        }
        return dtos;
    }

    // Implementing the interface method getById(String titre)
    @Override
    public TransparisationDTO getById(String titre) {
        Optional<Transparisation> optional = repository.findById(titre);
        return optional.map(this::mapToDTO).orElse(null);
    }

    @Override
    public TransparisationDTO create(TransparisationDTO dto) {
        Transparisation entity = mapToEntity(dto);
        entity = repository.save(entity);
        return mapToDTO(entity);
    }

    @Override
    public TransparisationDTO update(String titre, TransparisationDTO dto) {
        Optional<Transparisation> optional = repository.findById(titre);
        if (optional.isPresent()) {
            Transparisation entity = optional.get();
            // Do not update titre as it's the identifier.
            entity.setDateImage(dto.getDateImage());
            entity.setDateImageFin(dto.getDateImageFin());
            entity.setCodeIsin(dto.getCodeIsin());
            entity.setDescription(dto.getDescription());
            entity.setCategorie(dto.getCategorie());
            entity.setDettePublic(dto.getDettePublic());
            entity.setDettePrivee(dto.getDettePrivee());
            entity.setAction(dto.getAction());
            entity = repository.save(entity);
            return mapToDTO(entity);
        }
        return null;
    }

    @Override
    public void delete(String titre) {
        repository.deleteById(titre);
    }

    @Override
    public void importFromExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            // Skip header row
            if (rows.hasNext()) {
                rows.next();
            }
            List<Transparisation> entities = new ArrayList<>();
            while (rows.hasNext()) {
                Row row = rows.next();
                Transparisation entity = new Transparisation();

                // Column mapping (0-based index)
                entity.setDateImage(parseDateCell(row.getCell(0)));
                entity.setDateImageFin(parseDateCell(row.getCell(1)));
                // Use TITRE as the id
                String titreValue = getCellValueAsString(row.getCell(2));
                entity.setTitre(titreValue);
                entity.setCodeIsin(getCellValueAsString(row.getCell(3)));
                entity.setDescription(getCellValueAsString(row.getCell(4)));
                entity.setCategorie(getCellValueAsString(row.getCell(5)));

                // For integer columns, check cell type
                entity.setDettePublic(parseIntegerCell(row.getCell(6)));
                entity.setDettePrivee(parseIntegerCell(row.getCell(7)));
                entity.setAction(parseIntegerCell(row.getCell(8)));

                entities.add(entity);
            }
            repository.saveAll(entities);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to import Excel data: " + e.getMessage());
        }
    }

    /**
     * Helper method to parse an integer cell.
     * If the cell is numeric, it casts the numeric value to int.
     * If the cell is a string, it removes any commas before parsing.
     */
    private Integer parseIntegerCell(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            // Directly cast the numeric value to int (this truncates any decimals)
            return (int) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            String value = cell.getStringCellValue().trim();
            if (value.isEmpty()) return null;
            try {
                // Using a locale-aware number format (change Locale.FRANCE if needed)
                NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
                Number number = format.parse(value);
                return number.intValue();
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
    // Helper: get cell value as String (unchanged)
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toLocalDate().toString();
                } else {
                    double numericValue = cell.getNumericCellValue();
                    return (numericValue == (long) numericValue)
                            ? String.valueOf((long) numericValue)
                            : String.valueOf(numericValue);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            default:
                return "";
        }
    }

    // Helper: convert a date cell to LocalDate (with custom formatter as needed)
    private LocalDate parseDateCell(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } else if (cell.getCellType() == CellType.STRING) {
            String dateStr = cell.getStringCellValue().trim();
            if (!dateStr.isEmpty()) {
                try {
                    // Adjust pattern if necessary; here we assume "yyyy-MM-dd"
                    return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (Exception ex) {
                    System.err.println("Error parsing date: " + dateStr + " - " + ex.getMessage());
                }
            }
        }
        return null;
    }


    // Mapper: convert entity to DTO
    private TransparisationDTO mapToDTO(Transparisation entity) {
        if (entity == null) return null;
        return TransparisationDTO.builder()
                .titre(entity.getTitre())
                .dateImage(entity.getDateImage())
                .dateImageFin(entity.getDateImageFin())
                .codeIsin(entity.getCodeIsin())
                .description(entity.getDescription())
                .categorie(entity.getCategorie())
                .dettePublic(entity.getDettePublic())
                .dettePrivee(entity.getDettePrivee())
                .action(entity.getAction())
                .build();
    }

    // Mapper: convert DTO to entity
    private Transparisation mapToEntity(TransparisationDTO dto) {
        if (dto == null) return null;
        return Transparisation.builder()
                .titre(dto.getTitre())
                .dateImage(dto.getDateImage())
                .dateImageFin(dto.getDateImageFin())
                .codeIsin(dto.getCodeIsin())
                .description(dto.getDescription())
                .categorie(dto.getCategorie())
                .dettePublic(dto.getDettePublic())
                .dettePrivee(dto.getDettePrivee())
                .action(dto.getAction())
                .build();
    }
}
