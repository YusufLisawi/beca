package org.nttdata.ecomjstl.dao;

import org.nttdata.ecomjstl.model.Produit;

import java.util.List;


public interface ProduitDAO {
	public void addProduit(Produit produit);
	public void updateProduit(Produit produit);
	public List<Produit> listProduits();
	public Produit getProduitById(int id);
	public void removeProduit(int id);
}
