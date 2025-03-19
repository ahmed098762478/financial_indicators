package com.gov.cmr.transparisation_module.service;

import com.gov.cmr.transparisation_module.model.entitys.FichePortefeuille;
import com.gov.cmr.transparisation_module.repository.FichePortefeuilleRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@AllArgsConstructor
public class FichePortefeuilleService {

    private final FichePortefeuilleRepository fichePortefeuilleRepository;

    /**
     * Reads an Excel file and inserts rows into the FichePortefeuille table.
     * @param filePath Absolute path to the Excel file (e.g., "C:\\Users\\PC\\Desktop\\CMR\\fp.xlsx")
     */
    public void importFromExcel(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Read the first sheet

            // Loop through all rows
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue; // Skip empty rows

                // Optional: Skip header row by checking if the first cell contains a known header (e.g., "Code")
                String headerCell = getCellValueAsString(row.getCell(0));
                if (headerCell != null && headerCell.equalsIgnoreCase("Code")) {
                    continue;
                }

                // Create a new entity instance
                FichePortefeuille fp = new FichePortefeuille();

                // Map cells to entity fields (adjust column indexes as needed)
                fp.setCode(getCellValueAsInteger(row.getCell(5)));
                fp.setCode1(getCellValueAsInteger(row.getCell(5)));
                fp.setAct(getCellValueAsString(row.getCell(1)));
                fp.setActif(getCellValueAsInteger(row.getCell(9)));
                fp.setClasse(getCellValueAsString(row.getCell(2)));
                fp.setDevise(getCellValueAsString(row.getCell(3)));
                fp.setDescription(getCellValueAsString(row.getCell(4)));
                fp.setPdrTotalNet(getCellValueAsBigDecimal(row.getCell(6)));
                fp.setTotalValo(getCellValueAsBigDecimal(row.getCell(7)));
                fp.setDateReference(getCellValueAsDate(row.getCell(8)));
                fp.setPret(getCellValueAsFloat(row.getCell(10)));
                fp.setEmprunt(getCellValueAsFloat(row.getCell(11)));
                fp.setPdrUnitNet(getCellValueAsFloat(row.getCell(12)));
                fp.setValoUnitaire(getCellValueAsFloat(row.getCell(13)));
                fp.setPmvNette(getCellValueAsBigDecimal(row.getCell(14)));
                fp.setTauxDeChange(getCellValueAsFloat(row.getCell(15)));
                fp.setValoUnitCV(getCellValueAsFloat(row.getCell(16)));
                fp.setValoTotalCV(getCellValueAsBigDecimal(row.getCell(17)));
                fp.setPourcTotalTitre(getCellValueAsFloat(row.getCell(18)));
                fp.setDepositaire(getCellValueAsString(row.getCell(19)));
                fp.setPourcClasseActif(getCellValueAsFloat(row.getCell(20)));
                fp.setPourcEmetTotalTitre(getCellValueAsFloat(row.getCell(21)));
                fp.setPourcEmetActifNet(getCellValueAsFloat(row.getCell(22)));
                fp.setValoN1(getCellValueAsFloat(row.getCell(23)));
                fp.setVariationValo(getCellValueAsFloat(row.getCell(24)));
                fp.setTauxCourbe(getCellValueAsFloat(row.getCell(25)));
                fp.setSensibilite(getCellValueAsFloat(row.getCell(26)));
                fp.setDuration(getCellValueAsFloat(row.getCell(27)));
                fp.setConvexite(getCellValueAsFloat(row.getCell(28)));

                // Save the entity into the database
                fichePortefeuilleRepository.save(fp);
            }
        }
    }

    // Returns the trimmed string value of a cell or null if the cell is empty.
    private String getCellValueAsString(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        }
        return null;
    }

    // Converts a cell to an Integer.
    private Integer getCellValueAsInteger(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return (int) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Integer.parseInt(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    // Converts a cell to a Float.
    private Float getCellValueAsFloat(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return (float) cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Float.parseFloat(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    // Converts a cell to a BigDecimal.
    private BigDecimal getCellValueAsBigDecimal(Cell cell) {
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            return BigDecimal.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return new BigDecimal(cell.getStringCellValue().trim());
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    // Converts a cell to a Date. If the cell is empty, a header, or contains known non-date values, returns null.
    private Date getCellValueAsDate(Cell cell) {
        if (cell == null) return null;
        try {
            if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                return cell.getDateCellValue();
            }
            if (cell.getCellType() == CellType.STRING) {
                String value = cell.getStringCellValue().trim();
                // Skip if value is empty or matches known header/non-date strings
                if (value.isEmpty() ||
                        value.equalsIgnoreCase("DateRéférence") ||
                        value.equalsIgnoreCase("VACT") ||
                        value.equalsIgnoreCase("Poste")) {
                    return null;
                }
                // Adjust the date format if necessary
                return new SimpleDateFormat("MM/dd/yyyy").parse(value);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
