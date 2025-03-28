package com.gov.cmr.transparisation_module.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Similarly for ClassIIDTO, ClassIIIDTO, ClassIVDTO...
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassIIIDTO {
    // Each line in Class III
    private String actVC;    // "10,140,229,453.83" (ACT + PART)
    private String actVM;    // "15,249,382,783.12"

    // “OPCVM Actions & Diversifiés” => aggregator for Actions, Diversifié, Diversifié_TR
    private String opcvmActDivVC;
    private String opcvmActDivVM;

    // If you have a distinct row for “FPCT”
    private String fpctVC;
    private String fpctVM;

    // “Actions Déd.”
    private String actionsDedVC;
    private String actionsDedVM;

    // “OMLT_act” (if it exists)
    private String omltActVC;
    private String omltActVM;

    // “Fonds capital risque” => OPCR
    private String fondsCapRisqueVC;
    private String fondsCapRisqueVM;

    // Class totals & ratio
    private String totalClassIIIVC;
    private String totalClassIIIVM;
    private String ratioIII;        // e.g. "23.68%"
}


