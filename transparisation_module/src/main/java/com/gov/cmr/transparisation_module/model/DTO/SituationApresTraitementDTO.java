// SituationApresTraitementDTO.java
package com.gov.cmr.transparisation_module.model.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituationApresTraitementDTO {
    private Integer idSituation;
    private String categorie;
    private Double valeurComptable;
    private Double valeurMarche;
    private LocalDate dateCreation;
}