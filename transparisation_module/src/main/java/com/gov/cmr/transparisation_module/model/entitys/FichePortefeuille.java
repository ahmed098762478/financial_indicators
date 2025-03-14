package com.gov.cmr.transparisation_module.model.entitys;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Fiche_Portefeuille")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FichePortefeuille {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer ID_Fiche_Portefeuille;

    private Integer actif;

    private String classe;

    private String code;

    private Float convexite;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Temporal(TemporalType.DATE)
    private Date date_Reference;

    private String depositaire;

    private String description;

    private String devise;

    private Float duration;

    private Float emprunt;

    private String fpt;

    private BigDecimal PDR_Total_NET;

    private Float PDR_Unit_NET;

    private BigDecimal PMV_Nette;

    private Float pourc_Classe_Actif;

    private Float pourc_emet_actif_net;

    private Float pourc_emet_total_titre;

    private Float pourc_Total_Titre;

    private Float pret;

    private Float sensibilite;

    private Float taux_Courbe;

    private Integer taux_de_change;

    private BigDecimal total_Valo;

    private Integer valo_N_1;

    private BigDecimal valo_Total_CV;

    private Float valo_Unit_CV;

    private Float valo_Unitaire;

    private Integer variation_Valo;
}
