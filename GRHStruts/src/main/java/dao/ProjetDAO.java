package dao;

import java.util.List;

import model.Projet;

public interface ProjetDAO {
	public void addProjet(Projet projet);
	public void updateProjet(Projet projet);
	public List<Projet> listProjet();
	public List<Projet> getProjetByKeyword(String keyWord);
	public List<Projet> getProjetByType(String type);
	public Projet getProjetById(long id);
	public void removeProjet(long id);
}
