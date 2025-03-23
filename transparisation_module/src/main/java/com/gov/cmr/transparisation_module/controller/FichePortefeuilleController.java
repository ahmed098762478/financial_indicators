package com.gov.cmr.transparisation_module.controller;

import com.gov.cmr.transparisation_module.service.FichePortefeuilleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.gov.cmr.transparisation_module.model.DTO.FichePortefeuilleSummary;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/fiche-portefeuille")
@RequiredArgsConstructor
@Slf4j // Enables logging
public class FichePortefeuilleController {

    private final FichePortefeuilleService fichePortefeuilleService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty.");
        }

        File tempFile = null;
        try {
            // Save the uploaded file to a temporary location
            String originalFilename = file.getOriginalFilename();
            tempFile = File.createTempFile("fiche_portefeuille_", "_" + originalFilename);
            file.transferTo(tempFile);

            log.info("File uploaded successfully: {}", tempFile.getAbsolutePath());

            // Call service to process the Excel file
            fichePortefeuilleService.importFromExcel(tempFile.getAbsolutePath());

            return ResponseEntity.ok("File uploaded and data imported successfully.");
        } catch (IOException e) {
            log.error("Error processing file upload", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file: " + e.getMessage());
        } finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.deleteOnExit();
            }
        }
    }





    @GetMapping("/summary")
    public List<FichePortefeuilleSummary> getFichePortefeuilleSummary() {
        return fichePortefeuilleService.getFichePortefeuilleSummary();
    }





}
