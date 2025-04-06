package com.gov.cmr.transparisation_module.controller;

import com.gov.cmr.transparisation_module.model.DTO.SituationApresTraitementDTO;
import com.gov.cmr.transparisation_module.service.SituationApresTraitementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/situation-apres")
@CrossOrigin(origins = "http://localhost:4200")
public class SituationApresTraitementController {

    private final SituationApresTraitementService service;

    public SituationApresTraitementController(SituationApresTraitementService service) {
        this.service = service;
        log.info("Controller initialized"); // Vérifiez l'initialisation
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        log.info("GET /api/situation-apres called"); // Trace d'appel
        try {
            List<SituationApresTraitementDTO> result = service.getAll();
            log.debug("Found {} items", result.size());

            return result.isEmpty()
                    ? ResponseEntity.noContent().build()
                    : ResponseEntity.ok(result);

        } catch (Exception e) {
            log.error("Controller error", e); // Log complet de l'erreur
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getMessage());
        }
    }
}
