package org.nttdata.ecomjstl.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.nttdata.ecomjstl.dao.ProduitDAO;
import org.nttdata.ecomjstl.dao.CategorieDAO;
import org.nttdata.ecomjstl.service.ProduitDAOImpl;
import org.nttdata.ecomjstl.service.CategorieDAOImpl;
import org.nttdata.ecomjstl.model.Categorie;
import org.nttdata.ecomjstl.model.Produit;
import org.nttdata.ecomjstl.view.ProduitForm;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.io.IOException;

@WebServlet("/adminProduit")
@MultipartConfig
public class ProduitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String UPLOAD_DIR = "uploads";

    public ProduitServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProduitForm pf = new ProduitForm();
        ProduitDAO prodDao = new ProduitDAOImpl();
        CategorieDAO catDao = new CategorieDAOImpl();

        // Fetching all categories and setting them to the CategorieForm
        List<Categorie> categories = catDao.listCategories();
        pf.setLesCats(categories);
        pf.setLesProds(prodDao.listProduits());
        if (request.getParameter("idProd") != null) {
            prodDao.removeProduit(Integer.parseInt(request.getParameter("idProd")));
            pf.setLesProds(prodDao.listProduits());
        } else if (request.getParameter("chercheProd") != null) {
            pf.setMotCle(request.getParameter("motCle"));
            pf.setCatKey(request.getParameter("catKey"));
            pf.setLesProds(prodDao.selectProduitsByKeyword(pf.getMotCle(), pf.getCatKey()));
        }

        HttpSession session = request.getSession();
        session.setAttribute("prodForm", pf);
        response.sendRedirect("Produits.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProduitForm pf = new ProduitForm();
        ProduitDAO prodDao = new ProduitDAOImpl();
        CategorieDAO catDao = new CategorieDAOImpl();

        List<Categorie> categories = catDao.listCategories();
        pf.setLesCats(categories);

        if (request.getParameter("addProd") != null) {
            Produit newProd = new Produit();
            newProd.setDesignation(request.getParameter("designation"));
            newProd.setPrix(Float.parseFloat(request.getParameter("prix")));
            newProd.setQuantite(Integer.parseInt(request.getParameter("quantite")));
            newProd.setSdr(1);
            int categoryId = Integer.parseInt(request.getParameter("categoryId"));
            Categorie categorie = catDao.getCategorieById(categoryId);
            newProd.setCategorie(categorie);

            String applicationPath = request.getServletContext().getRealPath("");
            System.out.println(applicationPath);
            File uploadsDir = new File(applicationPath + File.separator + UPLOAD_DIR);
            if (!uploadsDir.exists()) {
                uploadsDir.mkdir();
            }

            Part filePart = request.getPart("image");
            String fileName = filePart.getSubmittedFileName();
            File file = new File(uploadsDir, fileName);
            try (InputStream input = filePart.getInputStream()) {
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
            newProd.setImage(UPLOAD_DIR + "/" + fileName);
            prodDao.addProduit(newProd);
            pf.setLesProds(prodDao.listProduits());
        }else if (request.getParameter("chercheProd") != null) {
            pf.setMotCle(request.getParameter("motCle"));
            pf.setCatKey(request.getParameter("catKey"));
            pf.setLesProds(prodDao.selectProduitsByKeyword(pf.getMotCle(), pf.getCatKey()));
        } else {
            pf.setLesProds(prodDao.listProduits());
        }

        HttpSession session = request.getSession();
        session.setAttribute("prodForm", pf);
        response.sendRedirect("Produits.jsp");
    }
}
