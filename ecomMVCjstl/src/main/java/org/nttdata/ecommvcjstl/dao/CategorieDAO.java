package org.nttdata.ecommvcjstl.dao;

import java.util.List;

import org.nttdata.ecommvcjstl.model.Categorie;

public interface CategorieDAO {
	public void addCategorie(Categorie categorie);
	public void updateCategorie(Categorie categorie);
	public List<Categorie> listCategories();
	public List<Categorie> selectCatByKeyword(String keyWord);
	public Categorie getCategorieById(int id);
	public void removeCategorie(int id);
}
