package com.gov.cmr.transparisation_module.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AggregatedAllClassesDTO {

    private ClassIDTO classI;   // e.g. BDT, VJG, OPCI-Etat, ...
    private ClassIIDTO classII; // e.g. CD, OC, ONC, ...
    private ClassIIIDTO classIII;
    private ClassIVDTO classIV;

    // If you also want the grandTotal or other lines outside classes, add them here
    private String grandTotalVC;
    private String grandTotalVM;
}


