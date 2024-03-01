<%@ page import="org.nttdata.ecommvc.view.ProductForm" %>
<%@ page import="org.nttdata.ecommvc.model.Product" %>
<%@ page import="org.nttdata.ecommvc.view.CategoryForm" %>
<%@ page import="org.nttdata.ecommvc.model.Category" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Gestion des Produits</title>
</head>
<body>
<%  ProductForm pf = (ProductForm) session.getAttribute("productForm");%>
<%  CategoryForm cf = (CategoryForm) session.getAttribute("categoryForm");%>
<form method='post' action='product'>
    <table class="table table-striped" align='center'>
        <tr>
            <td>Chercher une produit:<input type='text' name='keyword'
                                              value='<%=(pf != null) ? pf.getKeyword() : ""%>'></td>
            <td><input type='submit' name='searchProduct' value='Chercher'></td>
        </tr>
    </table>
</form>
<form method='post' action='product'>
    <table class="table table-hover" align='center'>
        <thead class="thead-dark">
        <tr>
            <th>Id </th>
            <th>Description</th>
            <th>Prix</th>
            <th>Quantité</th>
            <th>Selectionné</th>
            <th>Catégorie</th>
            <th>Image</th>
        </tr>
        </thead>
        <tr>
            <td></td>
            <td><input type='text' name='description'></td>
            <td><input type='text' name='price'></td>
            <td><input type='text' name='quantity'></td>
            <td><input type='checkbox' name='selected'></td>
            <td>
                <select name='idCategory'>
                    <% if (cf != null) {
                        for (Category cat: cf.getCategories()) {
                    %>
                    <option value='<%=cat.getId()%>' <%=(pf != null && pf.getIdCategory() == cat.getId()) ? "selected" : ""%>><%=cat.getName()%></option>
                    <%
                            }
                        }
                    %>
                </select>
            </td>
            <td><input type='text' name='image'></td>
            <td><input type='submit' name='addProduct' value='Ajouter'></td>
        </tr>
        <% if (pf != null) {
            for (Product product: pf.getProducts()) {
        %>
        <tr>
            <td><%=product.getId()%></td>
            <td><%=product.getDescription()%></td>
            <td><%=product.getPrice()%></td>
            <td><%=product.getQuantity()%></td>
            <td><%=product.isSelected()%></td>
            <td><%=product.getIdCategory()%></td>
            <td><%=product.getImage()%></td>
            <td><a href='product?idProduct=<%=product.getId()%>'>Supprimer</a>
        </tr>
        <%
                }
            }
        %>
    </table>
</form>
</body>
</html>
