<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<!DOCTYPE html>
<html>
<head>
    <s:head/>
    <sx:head/>
    <sb:head/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><s:text name = "produit.titre.liste" /></title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <s:form action="saisirProduit" cssClass="form-inline my-2 my-lg-0">

                <s:submit value="Add New Product" cssClass="btn btn-outline-success my-2 my-sm-0" />
            </s:form>
            <h2>Liste les produits</h2>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Designation</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="listProduits">
                    <tr>
                        <td><s:property value="id" /></td>
                        <td><s:property value="designation" /></td>
                        <td><s:property value="prix" /></td>
                        <td><s:property value="quantite" /></td>
                        <td>
                            <s:url var="deleteUrl" action="deleteProduct">
                                <s:param name="id" value="%{id}"/>
                            </s:url>
                            <s:a href="%{deleteUrl}" cssClass="btn btn-danger">Delete</s:a>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>