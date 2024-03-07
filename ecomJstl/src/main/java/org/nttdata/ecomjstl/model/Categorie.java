package org.nttdata.ecomjstl.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the categorie database table.
 */
@Entity
@Table(name = "categorie")
@NamedQuery(name = "Categorie.findAll", query = "SELECT c FROM Categorie c")
public class Categorie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private String nom;

    //bi-directional many-to-one association to Produit
    @OneToMany(mappedBy = "categorie")
    private List<Produit> produits;

    public Categorie() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Produit> getProduits() {
        return this.produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }

    public Produit addProduit(Produit produit) {
        getProduits().add(produit);
        produit.setCategorie(this);

        return produit;
    }

    public Produit removeProduit(Produit produit) {
        getProduits().remove(produit);
        produit.setCategorie(null);

        return produit;
    }

    @Override
    public String toString() {
        return id + " => " + nom + " : " + description;
    }

}