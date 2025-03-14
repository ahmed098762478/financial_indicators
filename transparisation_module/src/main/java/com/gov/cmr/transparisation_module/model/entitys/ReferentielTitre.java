package com.gov.cmr.transparisation_module.model.entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="referentiel_titre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferentielTitre {

    private static final long serialVersionUID = 1L;

    @Id
    private String code;

    private String base_Calcul;

    private String classe;

    private String code_ISIN;

    private String courb_Taux;

    @Temporal(TemporalType.DATE)
    private Date date_Echeance;

    @Temporal(TemporalType.DATE)
    private Date date_Emission;

    @Temporal(TemporalType.DATE)
    private Date date_Jouissance;

    @Temporal(TemporalType.DATE)
    private Date date_MAJ;

    private String depositaire;

    private String description;

    private String devise_Cotation;

    private Integer division;

    private String flag_Actif;

    private String forme_detention;

    private String garantie;

    private String groupe_1;

    private String groupe_2;

    private String groupe_3;

    private String lib_Court;

    private String marche;

    private String methode_Coupon;

    private String methode_Valo;

    private Integer nombre_Titre_Emis;

    private Integer nominal;

    private String periodicite_Coupon;

    private String periodicite_Rembou;

    private String place_Cotation;

    private Float prime_Rembou;

    private Float prix_Emission;

    private Integer quotite;

    private String secteur_Economique;

    private Integer spread_Emission;

    private String tiers_garant;

    private Integer titre_pere;

    private String type_Cotation;

    private String type_Precision;

    private String type_Spread_Emission;

    private String type_Taux;

    private Float valeur_Taux;


    @ManyToOne
    @JoinColumn(name="categorie")
    private Categorie categorieBean;

}
