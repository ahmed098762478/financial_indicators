package com.gov.cmr.transparisation_module.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassIIDTO {
    // Each line in Class II
    private String cdVC;         // "200,000,000.00"
    private String cdVM;         // "206,787,460.00"
    private String ocVC;         // "7,405,672.88"
    private String ocVM;         // "7,585,897.61"
    private String oncVC;        // "700,160,134.83"
    private String oncVM;        // "755,512,073.88"
    private String monetaireVC;  // "781,059,812.73"
    private String monetaireVM;  // "782,017,114.95"
    private String omltVC;       // "8,493,974,470.55" 
    private String omltVM;       // "8,903,396,494.61" 
    private String omltPrVC;     // "???" (if you track OMLT_PR) 
    private String omltPrVM;
    private String omltDedVC;    // "8,001,860,345.73" (OMLT Déd.)
    private String omltDedVM;

    // Class totals & ratio
    private String totalClassIIVC;
    private String totalClassIIVM;
    private String ratioII;         // e.g. "18.76%"
}
