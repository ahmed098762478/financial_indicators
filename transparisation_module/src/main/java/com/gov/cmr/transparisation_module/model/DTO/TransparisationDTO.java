package com.gov.cmr.transparisation_module.model.DTO;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransparisationDTO {
    private String titre;
    private LocalDate dateImage;
    private LocalDate dateImageFin;
    private String codeIsin;
    private String description;
    private String categorie;
    private Integer dettePublic;
    private Integer dettePrivee;
    private Integer action;
}
