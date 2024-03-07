package org.nttdata.ecomjstl.view;

import org.nttdata.ecomjstl.model.Categorie;

import java.util.List;

public class CategorieForm {
    private long idCat;
    private String motCle = "";
    private String nomCat;
    private String description;
    private List<Categorie> lesCats;

    public String getMotCle() {
        return motCle;
    }

    public void setMotCle(String motCle) {
        this.motCle = motCle;
    }

    public long getIdCat() {
        return idCat;
    }

    public void setIdCat(long idCat) {
        this.idCat = idCat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Categorie> getLesCats() {
        return lesCats;
    }

    public void setLesCats(List<Categorie> lesCats) {
        this.lesCats = lesCats;
    }

    public String getNomCat() {
        return nomCat;
    }

    public void setNomCat(String nomCat) {
        this.nomCat = nomCat;
    }

}
