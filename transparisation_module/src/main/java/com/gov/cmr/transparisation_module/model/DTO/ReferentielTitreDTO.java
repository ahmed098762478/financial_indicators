package com.gov.cmr.transparisation_module.model.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferentielTitreDTO {
    private String code;
    private String codeIsin;
    private String description;
    private String libCourt;
    private String flagActif;
    private String titrePere;
    private String classe;
    private String categorie;
    private String emetteur;
    private String formeDetention;
    private String secteurEconomique;
    private Long nombreTitreEmis;
    private BigDecimal nominal;
    private String typeSpreadEmission;
    private BigDecimal spreadEmission;
    private BigDecimal prixEmission;
    private BigDecimal primeRembou;
    private BigDecimal quotite;
    private String division;
    private String typeTaux;
    private BigDecimal valeurTaux;
    private String methodeCoupon;
    private String periodiciteCoupon;
    private String periodiciteRembou;
    private String baseCalcul;
    private String typePrecision;
    private LocalDate dateEmission;
    private LocalDate dateJouissance;
    private LocalDate dateEcheance;
    private LocalDate dateMaj;
    private String garantie;
    private String tiersGarant;
    private String courbeTaux;
    private String methodeValo;
    private String typeCotation;
    private String placeCotation;
    private String marche;
    private String groupe1;
    private String groupe2;
    private String groupe3;
    private String depositaire;
    private String deviseCotation;
}
