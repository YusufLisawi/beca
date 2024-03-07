package org.nttdata.ecomstruts.dao;

import org.nttdata.ecomstruts.model.Categorie;

import java.util.List;


public interface CategorieDAO {
    public void addCategorie(Categorie categorie);

    public void updateCategorie(Categorie categorie);

    public List<Categorie> listCategories();

    public List<Categorie> selectCatByKeyword(String keyWord);

    public Categorie getCategorieById(int id);

    public void removeCategorie(int id);
}
