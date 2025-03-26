package com.gov.cmr.transparisation_module.controller;

import com.gov.cmr.transparisation_module.model.DTO.ReferentielTitreDTO;
import com.gov.cmr.transparisation_module.service.ReferentielTitreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/referentiel-titres")
public class ReferentielTitreController {

    private final ReferentielTitreService service;

    public ReferentielTitreController(ReferentielTitreService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReferentielTitreDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ReferentielTitreDTO> getByCode(@PathVariable String code) {
        ReferentielTitreDTO dto = service.getByCode(code);
        return dto != null ? new ResponseEntity<>(dto, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<ReferentielTitreDTO> create(@RequestBody ReferentielTitreDTO dto) {
        return new ResponseEntity<>(service.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{code}")
    public ResponseEntity<ReferentielTitreDTO> update(@PathVariable String code, @RequestBody ReferentielTitreDTO dto) {
        ReferentielTitreDTO updated = service.update(code, dto);
        return updated != null ? new ResponseEntity<>(updated, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> delete(@PathVariable String code) {
        service.delete(code);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
        try {
            service.importFromExcel(file);
            return new ResponseEntity<>("Excel file imported successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to import Excel file: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
