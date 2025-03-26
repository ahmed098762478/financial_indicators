package com.gov.cmr.transparisation_module.controller;

import com.gov.cmr.transparisation_module.model.DTO.TransparisationDTO;
import com.gov.cmr.transparisation_module.service.TransparisationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/transparisation")
@CrossOrigin(origins = "http://localhost:4200")
public class TransparisationController {

    private final TransparisationService transparisationService;

    public TransparisationController(TransparisationService transparisationService) {
        this.transparisationService = transparisationService;
    }

    @GetMapping
    public ResponseEntity<List<TransparisationDTO>> getAll() {
        List<TransparisationDTO> dtos = transparisationService.getAll();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{titre}")
    public ResponseEntity<TransparisationDTO> getById(@PathVariable("titre") String titre) {
        TransparisationDTO dto = transparisationService.getById(titre);
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

    @PutMapping("/{titre}")
    public ResponseEntity<TransparisationDTO> update(@PathVariable("titre") String titre,
                                                     @RequestBody TransparisationDTO dto) {
        TransparisationDTO updated = transparisationService.update(titre, dto);
        if (updated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{titre}")
    public ResponseEntity<Void> delete(@PathVariable("titre") String titre) {
        transparisationService.delete(titre);
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
}
