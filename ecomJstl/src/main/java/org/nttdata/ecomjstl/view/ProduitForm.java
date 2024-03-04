package org.nttdata.ecomjstl.view;

import org.nttdata.ecomjstl.model.Categorie;
import org.nttdata.ecomjstl.model.Produit;

import java.util.List;

public class ProduitForm {
    private String motCle = "";
    private String catKey = "";
    private int idProd;
    private String designation;
    private float prix;
    private int quantite;
    private int sdr;
    private Categorie idCat;
    private List<Produit> lesProds;

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public List<Produit> getLesProds() {
        return lesProds;
    }

    public void setLesProds(List<Produit> lesProds) {
        this.lesProds = lesProds;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getSdr() {
        return sdr;
    }

    public void setSdr(int sdr) {
        this.sdr = sdr;
    }

    public Categorie getIdCat() {
        return idCat;
    }

    public void setIdCat(Categorie idCat) {
        this.idCat = idCat;
    }

    public String getCatKey() {
        return catKey;
    }

    public void setCatKey(String catKey) {
        this.catKey = catKey;
    }
}
