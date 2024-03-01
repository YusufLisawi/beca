package org.nttdata;

import org.nttdata.dao.CategorieDAO;
import org.nttdata.dao.ProduitDAO;
import org.nttdata.model.Categorie;
import org.nttdata.model.Produit;
import org.nttdata.service.CategorieDAOImpl;
import org.nttdata.service.ProduitDAOImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        CategorieDAO catDAO = new CategorieDAOImpl();
        List<Categorie> lesCateg;
        Categorie uneCategorie;
        lesCateg = catDAO.listCategories();
        System.out.println("List of all Categories (_"+lesCateg.size()+"_)");
        lesCateg.stream().forEach(e -> System.out.println(e));

        uneCategorie = new Categorie();
        uneCategorie.setNom("Nouvelle Categorie");
        uneCategorie.setDescription("Nouvelle Categorie");
        catDAO.addCategorie(uneCategorie);

        uneCategorie = new Categorie();
        uneCategorie.setNom("Cat2");
        uneCategorie.setDescription("Nouvelle2Categorie");
        catDAO.addCategorie(uneCategorie);

        lesCateg= catDAO.listCategories();
        System.out.println("List of all Categories (_"+lesCateg.size()+"_)");
        lesCateg.stream().forEach(e -> System.out.println(e));

        ProduitDAO prodDAO = new ProduitDAOImpl();
        List<Produit> lesProd;
        Produit unProduit;

        lesProd = prodDAO.listProduits();
        System.out.println("List of all Products (_" + lesProd.size() + "_)");
        lesProd.stream().forEach(e -> System.out.println(e));

        unProduit = new Produit();
        unProduit.setDesignation("New Product");
        unProduit.setPrix(100.0f);
        unProduit.setQuantite(10);
        unProduit.setCategorie(lesCateg.get(0));
        prodDAO.addProduit(unProduit);

        unProduit = new Produit();
        unProduit.setDesignation("Prod2");
        unProduit.setPrix(200.0f);
        unProduit.setQuantite(20);
        unProduit.setCategorie(lesCateg.get(1));
        prodDAO.addProduit(unProduit);

        lesProd = prodDAO.listProduits();
        System.out.println("List of all Products (_" + lesProd.size() + "_)");
        lesProd.stream().forEach(e -> System.out.println(e));


    }
}