package service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.PersonnelDAO;
import model.Administrateur;
import model.Developpeur;
import model.Personnel;

public class TestService {

	public static void main(String[] args) {
		PersonnelDAO personnelService = new PersonneDAOImpl();
		
		List<Personnel> listPersonnels = new ArrayList<Personnel>();
		
		listPersonnels = personnelService.listPersonnel();
		System.out.println("\n********** Liste du personnel( "+listPersonnels.size()+" ) **********");
		listPersonnels.stream()
		.forEach(System.out::println);
		System.out.println("\n********** Fin de la liste **********");
		
		Administrateur admin = new Administrateur();
		admin.setNom("Admin 6");
		admin.setPrenom("Admin 6");
		admin.setDateNaissance(LocalDate.of(1988, 2, 28));
		admin.setFonction("Finance");
		personnelService.addPersonnel(admin);
		
		Developpeur developpeur = new Developpeur();
		developpeur.setNom("Developpeur 6");
		developpeur.setPrenom("Developpeur 6");
		developpeur.setDateNaissance(LocalDate.of(2000, 9, 15));
		developpeur.setLanguage("RPA");
		personnelService.addPersonnel(developpeur);
		
		listPersonnels = personnelService.listPersonnel();
		System.out.println("\n********** Liste du personnel( "+listPersonnels.size()+" ) **********");
		listPersonnels.stream()
		.forEach(System.out::println);
		System.out.println("***************** Fin de la liste *******************");
		
	}

}
