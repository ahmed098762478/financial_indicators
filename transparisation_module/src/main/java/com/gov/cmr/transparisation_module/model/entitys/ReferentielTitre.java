package com.gov.cmr.transparisation_module.model.entitys;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "referentiel_titre")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReferentielTitre {
    public void setCode(String code) {
        this.code = code;
    }

    public void setCodeIsin(String codeIsin) {
        this.codeIsin = codeIsin;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLibCourt(String libCourt) {
        this.libCourt = libCourt;
    }

    public void setFlagActif(String flagActif) {
        this.flagActif = flagActif;
    }

    public void setTitrePere(String titrePere) {
        this.titrePere = titrePere;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setEmetteur(String emetteur) {
        this.emetteur = emetteur;
    }

    public void setFormeDetention(String formeDetention) {
        this.formeDetention = formeDetention;
    }

    public void setSecteurEconomique(String secteurEconomique) {
        this.secteurEconomique = secteurEconomique;
    }

    public void setNombreTitreEmis(Long nombreTitreEmis) {
        this.nombreTitreEmis = nombreTitreEmis;
    }

    public void setNominal(BigDecimal nominal) {
        this.nominal = nominal;
    }

    public void setTypeSpreadEmission(String typeSpreadEmission) {
        this.typeSpreadEmission = typeSpreadEmission;
    }

    public void setSpreadEmission(BigDecimal spreadEmission) {
        this.spreadEmission = spreadEmission;
    }

    public void setPrixEmission(BigDecimal prixEmission) {
        this.prixEmission = prixEmission;
    }

    public void setPrimeRembou(BigDecimal primeRembou) {
        this.primeRembou = primeRembou;
    }

    public void setQuotite(BigDecimal quotite) {
        this.quotite = quotite;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public void setTypeTaux(String typeTaux) {
        this.typeTaux = typeTaux;
    }

    public void setValeurTaux(BigDecimal valeurTaux) {
        this.valeurTaux = valeurTaux;
    }

    public void setMethodeCoupon(String methodeCoupon) {
        this.methodeCoupon = methodeCoupon;
    }

    public void setPeriodiciteCoupon(String periodiciteCoupon) {
        this.periodiciteCoupon = periodiciteCoupon;
    }

    public void setPeriodiciteRembou(String periodiciteRembou) {
        this.periodiciteRembou = periodiciteRembou;
    }

    public void setBaseCalcul(String baseCalcul) {
        this.baseCalcul = baseCalcul;
    }

    public void setTypePrecision(String typePrecision) {
        this.typePrecision = typePrecision;
    }

    public void setDateEmission(LocalDate dateEmission) {
        this.dateEmission = dateEmission;
    }

    public void setDateJouissance(LocalDate dateJouissance) {
        this.dateJouissance = dateJouissance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public void setDateMaj(LocalDate dateMaj) {
        this.dateMaj = dateMaj;
    }

    public void setGarantie(String garantie) {
        this.garantie = garantie;
    }

    public void setTiersGarant(String tiersGarant) {
        this.tiersGarant = tiersGarant;
    }

    public void setCourbeTaux(String courbeTaux) {
        this.courbeTaux = courbeTaux;
    }

    public void setMethodeValo(String methodeValo) {
        this.methodeValo = methodeValo;
    }

    public void setTypeCotation(String typeCotation) {
        this.typeCotation = typeCotation;
    }

    public void setPlaceCotation(String placeCotation) {
        this.placeCotation = placeCotation;
    }

    public void setMarche(String marche) {
        this.marche = marche;
    }

    public void setGroupe1(String groupe1) {
        this.groupe1 = groupe1;
    }

    public void setGroupe2(String groupe2) {
        this.groupe2 = groupe2;
    }

    public void setGroupe3(String groupe3) {
        this.groupe3 = groupe3;
    }

    public void setDepositaire(String depositaire) {
        this.depositaire = depositaire;
    }

    public void setDeviseCotation(String deviseCotation) {
        this.deviseCotation = deviseCotation;
    }

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

    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "code_isin")
    private String codeIsin;

    @Column(name = "description")
    private String description;

    @Column(name = "lib_court")
    private String libCourt;

    @Column(name = "flag_actif")
    private String flagActif;

    @Column(name = "titre_pere")
    private String titrePere;
    // add to fp
    @Column(name = "classe")
    private String classe;

    @Column(name = "categorie")
    private String categorie;

    @Column(name = "emetteur")
    private String emetteur;
    // here
    @Column(name = "forme_detention")
    private String formeDetention;

    @Column(name = "secteur_economique")
    private String secteurEconomique;

    @Column(name = "nombre_titre_emis")
    private Long nombreTitreEmis;

    @Column(name = "nominal")
    private BigDecimal nominal;

    @Column(name = "type_spread_emission")
    private String typeSpreadEmission;

    @Column(name = "spread_emission")
    private BigDecimal spreadEmission;

    @Column(name = "prix_emission")
    private BigDecimal prixEmission;

    @Column(name = "prime_rembou")
    private BigDecimal primeRembou;

    @Column(name = "quotite")
    private BigDecimal quotite;

    @Column(name = "division")
    private String division;

    @Column(name = "type_taux")
    private String typeTaux;

    @Column(name = "valeur_taux")
    private BigDecimal valeurTaux;

    @Column(name = "methode_coupon")
    private String methodeCoupon;

    @Column(name = "periodicite_coupon")
    private String periodiciteCoupon;

    @Column(name = "periodicite_rembou")
    private String periodiciteRembou;

    @Column(name = "base_calcul")
    private String baseCalcul;

    @Column(name = "type_precision")
    private String typePrecision;

    @Column(name = "date_emission")
    private LocalDate dateEmission;

    @Column(name = "date_jouissance")
    private LocalDate dateJouissance;

    @Column(name = "date_echeance")
    private LocalDate dateEcheance;

    @Column(name = "date_maj")
    private LocalDate dateMaj;

    @Column(name = "garantie")
    private String garantie;

    @Column(name = "tiers_garant")
    private String tiersGarant;

    @Column(name = "courbe_taux")
    private String courbeTaux;

    @Column(name = "methode_valo")
    private String methodeValo;

    @Column(name = "type_cotation")
    private String typeCotation;

    @Column(name = "place_cotation")
    private String placeCotation;

    @Column(name = "marche")
    private String marche;

    @Column(name = "groupe_1")
    private String groupe1;

    @Column(name = "groupe_2")
    private String groupe2;

    @Column(name = "groupe_3")
    private String groupe3;

    @Column(name = "depositaire")
    private String depositaire;

    @Column(name = "devise_cotation")
    private String deviseCotation;
}
