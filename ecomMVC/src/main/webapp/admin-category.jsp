<%@ page import="org.nttdata.ecommvc.view.CategoryForm" %>
<%@ page import="org.nttdata.ecommvc.model.Category" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Gestion des Catégories</title>
</head>
<body>
<%  CategoryForm cf = (CategoryForm) session.getAttribute("catForm");%>
<form method='post' action='category'>
    <table class="table table-striped" align='center'>
        <tr>
            <td>Chercher une catégorie:<input type='text' name='motCle'
                                              value='<%=(cf != null) ? cf.getKeyword() : ""%>'></td>
            <td><input type='submit' name='chercheCat' value='Chercher'></td>
        </tr>
    </table>
</form>
<form method='post' action='category'>
    <table class="table table-hover" align='center'>
        <thead class="thead-dark">
        <tr>
            <th>Id Catégorie</th><th>Nom Catégorie</th><th>Description</th>
        </tr>
        </thead>
        <tr>
            <td></td>
            <td><input type='text' name='nomCat'></td>
            <td><input type='text' name='description' size='40'></td>
            <td><input type='submit' name='addCat' value='Ajouter'></td>
        </tr>
        <% if (cf != null) {
            for (Category cat: cf.getCategories()) {
        %>
        <tr>
            <td><%=cat.getId()%></td>
            <td><%=cat.getDescription()%></td>
            <td><a href='category?idCat=<%=cat.getId()%>'>Supprimer</a>
        </tr>
        <%
                }
            }
        %>
    </table>
</form>
</body>
</html>
