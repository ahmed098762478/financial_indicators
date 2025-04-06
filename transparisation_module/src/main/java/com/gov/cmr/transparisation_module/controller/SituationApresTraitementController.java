package com.gov.cmr.transparisation_module.controller;

import com.gov.cmr.transparisation_module.model.DTO.SituationApresTraitementDTO;
import com.gov.cmr.transparisation_module.service.SituationApresTraitementService;
import com.gov.cmr.transparisation_module.service.TransparisationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/situation-apres")
@CrossOrigin(origins = "http://localhost:4200")
public class SituationApresTraitementController {

    private final SituationApresTraitementService service;
    private final TransparisationService transparisationService;

    public SituationApresTraitementController(SituationApresTraitementService service,
                                              TransparisationService transparisationService) {
        this.service = service;
        this.transparisationService = transparisationService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        try {
            if (date != null) {
                // Force le recalcul si une date est fournie
                transparisationService.getTransparisationByDate(date);
                service.calculateAndSaveSituation();
            }

            List<SituationApresTraitementDTO> result = service.getAll();

            if (result.isEmpty()) {
                return ResponseEntity.noContent()
                        .header("X-Empty-Reason", "No data calculated yet. Call /process first")
                        .build();
            }

            return ResponseEntity.ok(result);
        } catch (Exception e) {
            log.error("API Error", e);
            return ResponseEntity.internalServerError()
                    .body(Map.of(
                            "error", e.getMessage(),
                            "cause", e.getCause() != null ? e.getCause().getMessage() : "none"
                    ));
        }
    }
}
