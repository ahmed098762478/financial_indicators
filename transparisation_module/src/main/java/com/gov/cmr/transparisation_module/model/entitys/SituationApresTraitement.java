// SituationApresTraitement.java
package com.gov.cmr.transparisation_module.model.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "situation_apres_traitement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituationApresTraitement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_situation")
    private Integer idSituation;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "valeur_comptable")
    private Double valeurComptable;

    @Column(name = "valeur_marche")
    private Double valeurMarche;

    @Column(name = "date_creation")
    private LocalDate dateCreation;
}

