package com.gov.cmr.transparisation_module.controller;

import com.gov.cmr.transparisation_module.model.DTO.SituationApresTraitementDTO;
import com.gov.cmr.transparisation_module.model.DTO.TransparisationDTO;
import com.gov.cmr.transparisation_module.service.SituationApresTraitementService;
import com.gov.cmr.transparisation_module.service.TransparisationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/transparisation")
@CrossOrigin(origins = "http://localhost:4200")
public class TransparisationController {

    private final TransparisationService transparisationService;
    private final SituationApresTraitementService situationService;



    public TransparisationController(TransparisationService transparisationService,
                                     SituationApresTraitementService situationService) {
        this.transparisationService = transparisationService;
        this.situationService = situationService;
    }

    @GetMapping
    public ResponseEntity<List<TransparisationDTO>> getAll() {
        List<TransparisationDTO> dtos = transparisationService.getAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransparisationDTO> getById(@PathVariable("id") Integer id) {
        TransparisationDTO dto = transparisationService.getById(id);
        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TransparisationDTO> create(@RequestBody TransparisationDTO dto) {
        TransparisationDTO created = transparisationService.create(dto);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransparisationDTO> update(@PathVariable("id") Integer id,
                                                     @RequestBody TransparisationDTO dto) {
        TransparisationDTO updated = transparisationService.update(id, dto);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        transparisationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            transparisationService.importFromExcel(file);
            return new ResponseEntity<>("Excel file imported successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to import Excel file: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/by-date")
    public ResponseEntity<List<TransparisationDTO>> getTransparisationByDate(
            @RequestParam("targetDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDate,
            @RequestParam(value = "dateFin", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
            @RequestParam(value = "ptf", required = false) String ptf) {

        List<TransparisationDTO> result = transparisationService.getTransparisationByDate(targetDate);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/process")
    public ResponseEntity<?> processTransparisation(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate targetDate) {

        try {
            // 1. Créer trans_tempo et récupérer les données
            List<TransparisationDTO> transparisations = transparisationService.getTransparisationByDate(targetDate);

            // 2. Calculer et sauvegarder la situation après traitement
            List<SituationApresTraitementDTO> situations = situationService.calculateAndSaveSituation();

            Map<String, Object> response = new HashMap<>();
            response.put("transparisations", transparisations);
            response.put("situations", situations);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body("Erreur lors du traitement: " + e.getMessage());
        }
    }

}
