package com.gov.cmr.transparisation_module.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// Example sub-DTO for Class I
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassIDTO {
    private String bdtVC;               // "13,512,002,176.09"
    private String bdtVM;
    private String vjgVC;
    private String vjgVM;
    private String opciEtatVC;         // OPCI_Publique + OPCI_TR
    private String opciEtatVM;
    private String omltPursVC;
    private String omltPursVM;
    private String operationEncoursVC; // If you track it
    private String operationEncoursVM;
    private String omltPbVC;           // If you have "OMLT_PB"
    private String omltPbVM;

    private String totalClassIVC;
    private String totalClassIVM;
    private String ratioI;             // e.g. "44.22%"
}
