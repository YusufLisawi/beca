package org.nttdata.ecomjstl.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nttdata.ecomjstl.dao.ProduitDAO;
import org.nttdata.ecomjstl.dao.CategorieDAO;
import org.nttdata.ecomjstl.service.ProduitDAOImpl;
import org.nttdata.ecomjstl.service.CategorieDAOImpl;
import org.nttdata.ecomjstl.model.Categorie;
import org.nttdata.ecomjstl.model.Produit;
import org.nttdata.ecomjstl.view.CategorieForm;
import org.nttdata.ecomjstl.view.ProduitForm;

import java.util.List;
import java.io.IOException;

@WebServlet("/adminProduit")
public class ProduitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProduitServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProduitForm pf = new ProduitForm();
        ProduitDAO prodDao = new ProduitDAOImpl();
        CategorieDAO catDao = new CategorieDAOImpl();

        // Fetching all categories and setting them to the CategorieForm
        List<Categorie> categories = catDao.listCategories();
        CategorieForm cf = new CategorieForm();
        cf.setLesCats(categories);

        if (request.getParameter("addProd") != null) {
            Produit newProd = new Produit();
            newProd.setDesignation(request.getParameter("designation"));
            newProd.setPrix(Float.parseFloat(request.getParameter("prix")));
            newProd.setQuantite(Integer.parseInt(request.getParameter("quantite")));
            newProd.setSdr(1);
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Categorie categorie = catDao.getCategorieById(categoryId);
            newProd.setCategorie(categorie);
            prodDao.addProduit(newProd);
            pf.setLesProds(prodDao.listProduits());
        } else if (request.getParameter("idProd") != null) {
            prodDao.removeProduit(Integer.parseInt(request.getParameter("idProd")));
            pf.setLesProds(prodDao.listProduits());
        } else if (request.getParameter("chercheProd") != null) {
            pf.setMotCle(request.getParameter("motCle"));
            pf.setLesProds(prodDao.selectProduitsByKeyword(pf.getMotCle()));
        } else {
            pf.setLesProds(prodDao.listProduits());
        }

        HttpSession session = request.getSession();
        session.setAttribute("prodForm", pf);
        session.setAttribute("catForm", cf); // Adding CategorieForm to session
        response.sendRedirect("Produits.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
