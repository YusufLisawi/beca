package org.nttdata.ecommvcjstl.controller;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.nttdata.ecommvcjstl.dao.CategorieDAO;
import org.nttdata.ecommvcjstl.model.Categorie;
import org.nttdata.ecommvcjstl.service.CategorieDAOImpl;
import org.nttdata.ecommvcjstl.view.CategorieForm;


@WebServlet("/adminCategorie")
public class CategorieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CategorieServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Créer le bean qui va stocker les données de la requête
				CategorieForm cf=new CategorieForm();
		//Faire appel au modèle pour ajouter une nouvelle catégorie
				CategorieDAO catal=new CategorieDAOImpl();
				if(request.getParameter("addCat")!=null){
		// Socker les données de la reqête dans le bean créé
					cf.setNomCat(request.getParameter("nomCat"));
					cf.setDescription(request.getParameter("description"));
		//Sauvegarder l'objet dans la base de données
					Categorie newCat = new Categorie();
					newCat.setNom(request.getParameter("nomCat"));
					newCat.setDescription(request.getParameter("description"));
					catal.addCategorie(newCat);
		//Récupérer toutes les catégries et les stocker dans le bean
					cf.setLesCats(catal.listCategories());
				}
				else if(request.getParameter("idCat")!=null){
		// Socker les données de la reqête dans le bean créé
					cf.setIdCat(Long.parseLong(request.getParameter("idCat")));

		//Supprimer l'objet de la base de données
					catal.removeCategorie((int)cf.getIdCat());
					cf.setLesCats(catal.listCategories());
				}
				else if(request.getParameter("chercheCat")!=null){
		// Socker les données de la reqête dans le bean créé
					cf.setMotCle(request.getParameter("motCle"));
		//Récupérer les catégries par mot clé et les stocker dans le bean
					cf.setLesCats(catal.selectCatByKeyword(cf.getMotCle()));
				}
				else{
		//Récupérer toutes les catégries et les stocker dans le bean
					cf.setLesCats(catal.listCategories());
				}

		/* avant de donner la main la page JSP pour afficher
		 * enregitrer le bean dans la requête ou la session courante
		 */
					HttpSession session=request.getSession();
					session.setAttribute("catForm",cf);
		//Faire une redirection vers la vue JSP pour afficher.
					response.sendRedirect("Categories.jsp");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
