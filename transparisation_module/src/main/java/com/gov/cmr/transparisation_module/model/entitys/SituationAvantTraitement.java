package com.gov.cmr.transparisation_module.model.entitys;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "situation_avant_traitement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SituationAvantTraitement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_situation")
    private Integer idSituation;

    @Column(name = "is_situation_avant")
    private Boolean isSituationAvant;

    @Column(name = "PTF")
    private String ptf;

    @Column(name = "date_en_cours")
    private LocalDate dateEnCours;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "valeur_marche")
    private Double valeurMarche;

    @Column(name = "valeur_comptable")
    private Double valeurComptable;
}
