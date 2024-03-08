package org.nttdata.ecomstruts.actions;

import org.nttdata.ecomstruts.dao.ProduitDAO;
import org.nttdata.ecomstruts.model.Produit;
import org.nttdata.ecomstruts.service.ProduitDAOImpl;

import java.util.List;

public class ProduitAction {
    private int id;

    private String designation;

    private float prix;

    private int quantite;

    private List<Produit> listProduits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<Produit> getListProduits() {
        return listProduits;
    }

    public void setListProduits(List<Produit> listProduits) {
        this.listProduits = listProduits;
    }

    public String lister() {
        ProduitDAO prodDAO = new ProduitDAOImpl();
        listProduits = prodDAO.listProduits();
        return "success";
    }

    public String saisir() {
        return "success";
    }

    public String enregistrer() {
        ProduitDAO prodDAO = new ProduitDAOImpl();
        Produit newProd = new Produit();
        newProd.setDesignation(designation);
        newProd.setPrix(prix);
        newProd.setQuantite(quantite);
        newProd.setSdr(0);
        prodDAO.addProduit(newProd);
        listProduits = prodDAO.listProduits();
        return "success";
    }

    public String deleteProduct() {
        ProduitDAO prodDAO = new ProduitDAOImpl();
        prodDAO.removeProduit(id);
        listProduits = prodDAO.listProduits();
        return "success";
    }
}
