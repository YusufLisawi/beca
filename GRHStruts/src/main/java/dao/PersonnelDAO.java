package dao;

import java.util.List;

import model.Personnel;

public interface PersonnelDAO {
	public void addPersonnel(Personnel personnel);
	public void updatePersonnel(Personnel personnel);
	public List<Personnel> listPersonnel();
	public List<Personnel> getPersonnelByKeyword(String keyWord);
	public List<Personnel> getPersonnelByType(TypePersonnel type);
	public Personnel getPersonnelById(long id);
	public void removePersonnel(long id);
}