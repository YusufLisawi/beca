package org.nttdata.ecommvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.nttdata.ecommvc.orm.CategoryORM;
import org.nttdata.ecommvc.view.CategoryForm;

import java.io.IOException;

@WebServlet(name = "categoryServlet", value = "/category")
public class CategoryServlet extends HttpServlet {
    private CategoryORM orm;

    public void init() {
        orm = new CategoryORM();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryForm cf = new CategoryForm();
        if (request.getParameter("addCat") != null) {
            cf.setName(request.getParameter("nomCat"));
            cf.setDescription(request.getParameter("description"));
            orm.createCategory(cf.getName(), cf.getDescription());
            cf.setCategories(orm.listCategories());
        } else if (request.getParameter("idCat") != null) {
            cf.setId(Integer.parseInt(request.getParameter("idCat")));
            orm.deleteCategory(cf.getId());
            cf.setCategories(orm.listCategories());
        } else if (request.getParameter("chercheCat") != null) {
            cf.setKeyword(request.getParameter("motCle"));
            cf.setCategories(orm.listCategoriesByKeyword(cf.getKeyword()));
        } else {
            cf.setCategories(orm.listCategories());
        }

        HttpSession session = request.getSession();
        session.setAttribute("catForm", cf);
        response.sendRedirect("admin-category.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}