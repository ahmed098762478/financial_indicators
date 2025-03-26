package com.gov.cmr.transparisation_module.model.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituationAvantTraitementDTO {

    private Integer idSituation;
    private Boolean isSituationAvant;
    private String ptf;
    private LocalDate dateEnCours;
    private String categorie;
    private Double valeurMarche;
    private Double valeurComptable;
}
