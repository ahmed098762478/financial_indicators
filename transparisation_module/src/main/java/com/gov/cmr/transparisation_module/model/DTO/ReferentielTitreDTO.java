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

    public String getCode() {
        return code;
    }

    public String getCodeIsin() {
        return codeIsin;
    }

    public String getDescription() {
        return description;
    }

    public String getLibCourt() {
        return libCourt;
    }

    public String getFlagActif() {
        return flagActif;
    }

    public String getTitrePere() {
        return titrePere;
    }

    public String getClasse() {
        return classe;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getEmetteur() {
        return emetteur;
    }

    public String getFormeDetention() {
        return formeDetention;
    }

    public String getSecteurEconomique() {
        return secteurEconomique;
    }

    public Long getNombreTitreEmis() {
        return nombreTitreEmis;
    }

    public BigDecimal getNominal() {
        return nominal;
    }

    public String getTypeSpreadEmission() {
        return typeSpreadEmission;
    }

    public BigDecimal getSpreadEmission() {
        return spreadEmission;
    }

    public BigDecimal getPrixEmission() {
        return prixEmission;
    }

    public BigDecimal getPrimeRembou() {
        return primeRembou;
    }

    public BigDecimal getQuotite() {
        return quotite;
    }

    public String getDivision() {
        return division;
    }

    public String getTypeTaux() {
        return typeTaux;
    }

    public BigDecimal getValeurTaux() {
        return valeurTaux;
    }

    public String getMethodeCoupon() {
        return methodeCoupon;
    }

    public String getPeriodiciteCoupon() {
        return periodiciteCoupon;
    }

    public String getPeriodiciteRembou() {
        return periodiciteRembou;
    }

    public String getBaseCalcul() {
        return baseCalcul;
    }

    public String getTypePrecision() {
        return typePrecision;
    }

    public LocalDate getDateEmission() {
        return dateEmission;
    }

    public LocalDate getDateJouissance() {
        return dateJouissance;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public LocalDate getDateMaj() {
        return dateMaj;
    }

    public String getGarantie() {
        return garantie;
    }

    public String getTiersGarant() {
        return tiersGarant;
    }

    public String getCourbeTaux() {
        return courbeTaux;
    }

    public String getMethodeValo() {
        return methodeValo;
    }

    public String getTypeCotation() {
        return typeCotation;
    }

    public String getPlaceCotation() {
        return placeCotation;
    }

    public String getMarche() {
        return marche;
    }

    public String getGroupe1() {
        return groupe1;
    }

    public String getGroupe2() {
        return groupe2;
    }

    public String getGroupe3() {
        return groupe3;
    }

    public String getDepositaire() {
        return depositaire;
    }

    public String getDeviseCotation() {
        return deviseCotation;
    }

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
