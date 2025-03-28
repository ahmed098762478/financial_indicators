package com.gov.cmr.transparisation_module.model.DTO;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituationAvantGroupedDTO {

    // Example: "13,512,002,176.09"
    private String opciEtatVC;
    private String opciEtatVM;

    private String bdtVC;
    private String bdtVM;

    private String vjgVC;
    private String vjgVM;

    private String omltPursVC;
    private String omltPursVM;

    private String actVC;
    private String actVM;

    private String opcvmActionsDiversifiesVC;
    private String opcvmActionsDiversifiesVM;

    private String fondsCapitalRisqueVC;
    private String fondsCapitalRisqueVM;

    private String opciPriveVC;
    private String opciPriveVM;

    private String fondsInvestissementVC;
    private String fondsInvestissementVM;

    // ...Add more if you have additional final lines (“opération encours” etc.)
}
