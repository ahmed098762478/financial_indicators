package com.gov.cmr.transparisation_module.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassIVDTO {
    // Each line in Class IV
    private String opciPriveVC;    // "219,999,863.72" (OPCIRFA)
    private String opciPriveVM;    // "217,807,787.14"

    private String opciPbTrVC;     // If you have "OPCI PB_TR"
    private String opciPbTrVM;

    // “Fonds d'investissement” => FondsInv
    private String fondsInvVC;
    private String fondsInvVM;

    // Class total & ratio
    private String totalClassIVC;
    private String totalClassIVM;
    private String ratioIV;        // e.g. "1.52%"
}