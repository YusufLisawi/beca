package org.nttdata.ecommvc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.nttdata.ecommvc.orm.CategoryORM;
import org.nttdata.ecommvc.orm.ProductORM;
import org.nttdata.ecommvc.view.CategoryForm;
import org.nttdata.ecommvc.view.ProductForm;

import java.io.IOException;

@WebServlet(name = "productServlet", value = "/product")
public class ProductServlet extends HttpServlet {
    private ProductORM orm;

    public void init() {
        orm = new ProductORM();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductForm pf = new ProductForm();
        CategoryForm cf = new CategoryForm();
        CategoryORM catOrm = new CategoryORM();
        cf.setCategories(catOrm.listCategories());

        if (request.getParameter("addProduct") != null) {
            String description = request.getParameter("description");
            double price = Double.parseDouble(request.getParameter("price"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            boolean selected = request.getParameter("selected").equals("on");
            System.out.println(selected);
            int idCategory = Integer.parseInt(request.getParameter("idCategory"));
            String image = request.getParameter("image");

            orm.createProduct(description, price, quantity, selected, idCategory, image);
            pf.setProducts(orm.listProducts());
        } else if (request.getParameter("idProduct") != null) {
            pf.setId(Integer.parseInt(request.getParameter("idProduct")));
            orm.deleteProduct(pf.getId());
            pf.setProducts(orm.listProducts());
        } else if (request.getParameter("searchProduct") != null) {
            pf.setKeyword(request.getParameter("keyword"));
            pf.setProducts(orm.listProductsByKeyword(pf.getKeyword()));
        } else {
            pf.setProducts(orm.listProducts());
        }

        HttpSession session = request.getSession();
        session.setAttribute("productForm", pf);
        session.setAttribute("categoryForm", cf);
        response.sendRedirect("admin-product.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}