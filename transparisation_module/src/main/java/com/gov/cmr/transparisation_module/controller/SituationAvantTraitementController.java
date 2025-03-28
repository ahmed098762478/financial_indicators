package com.gov.cmr.transparisation_module.controller;

import com.gov.cmr.transparisation_module.model.DTO.AggregatedAllClassesDTO;
import com.gov.cmr.transparisation_module.model.DTO.SituationAvantGroupedDTO;
import com.gov.cmr.transparisation_module.service.SituationAvantTraitementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/situation-avant-traitement")
public class SituationAvantTraitementController {
    private final SituationAvantTraitementService service;

    public SituationAvantTraitementController(SituationAvantTraitementService service) {
        this.service = service;
    }

    @GetMapping("/aggregated-all-classes")
    public AggregatedAllClassesDTO getAggregatedAllClasses() {
        return service.getAggregatedAllClasses();
    }

}
