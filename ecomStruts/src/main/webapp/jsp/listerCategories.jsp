<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
<%--         pageEncoding="UTF-8" %>--%>
<%--<%@ taglib prefix="s" uri="/struts-tags" %>--%>
<%--<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>--%>
<%--<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <s:head/>--%>
<%--    <sx:head/>--%>
<%--    <sb:head/>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <title>Liste des categories</title>--%>
<%--    <script>--%>
<%--        function show_searched() {--%>
<%--            dojo.event.topic.publish("show_searched");--%>
<%--        }--%>
<%--    </script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div style="display: flex; align-items: center; justify-content: center">--%>
<%--    <s:form id="listCategories_form" name="listCategories_form" theme="simple">--%>
<%--        <s:textfield label="Choisir une Categorie" id="motCle" name="motCle" onchange="javascript:show_searched();return false;"/>--%>

<%--        <div>--%>
<%--            <h2>Liste des Categories</h2>--%>
<%--            <s:url var="CategoriesByKeyword" action="searchCategories"/>--%>
<%--            <sx:div href="%{CategoriesByKeyword}" listenTopics="show_searched" theme="ajax" formId="listCategories_form">--%>
<%--                <s:if test="%{listCategories.size()>0}">--%>
<%--                    <table class="table table-striped table-bordered table-hover table-sm">--%>
<%--                        <tr>--%>
<%--                            <td>Identifiant</td>--%>
<%--                            <td>Nom</td>--%>
<%--                            <td>Description</td>--%>
<%--                            <td>Action</td>--%>
<%--                        </tr>--%>
<%--                        <s:iterator value="listCategories">--%>
<%--                            <tr>--%>
<%--                                <td><s:property value="id"/></td>--%>
<%--                                <td><s:property value="nom"/></td>--%>
<%--                                <td><s:property value="description"/></td>--%>
<%--                                <td>--%>
<%--                                    <s:url var="deleteUrl" action="deleteCategory">--%>
<%--                                        <s:param name="id" value="%{id}"/>--%>
<%--                                    </s:url>--%>
<%--                                    <s:a href="%{deleteUrl}">Delete</s:a>--%>
<%--                                </td>--%>
<%--                            </tr>--%>
<%--                        </s:iterator>--%>
<%--                    </table>--%>
<%--                </s:if>--%>
<%--                <s:else>--%>
<%--                    Aucune Categorie dans la liste--%>
<%--                </s:else>--%>
<%--            </sx:div>--%>
<%--        </div>--%>
<%--    </s:form>--%>
<%--    <a href="saisirCategorie.action">Ajouter une Categorie</a><br/>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>


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
            <s:form action="saisirCategorie" cssClass="form-inline my-2 my-lg-0">

                <s:submit value="Add New Categorie" cssClass="btn btn-outline-success my-2 my-sm-0" />
            </s:form>
            <h2>Liste des Categories</h2>
            <s:url var="CategoriesByKeyword" action="searchCategories"/>
            <sx:div href="%{CategoriesByKeyword}" listenTopics="show_searched" theme="ajax" formId="listCategories_form">
                <s:if test="%{listCategories.size()>0}">
                    <table class="table table-striped table-hover table-sm">
                        <tr>
                            <td>Identifiant</td>
                            <td>Nom</td>
                            <td>Description</td>
                            <td>Action</td>
                        </tr>
                        <s:iterator value="listCategories">
                            <tr>
                                <td><s:property value="id"/></td>
                                <td><s:property value="nom"/></td>
                                <td><s:property value="description"/></td>
                                <td>
                                    <s:url var="deleteUrl" action="deleteCategory">
                                        <s:param name="id" value="%{id}"/>
                                    </s:url>
                                    <s:a href="%{deleteUrl}" cssClass="btn btn-danger">Delete</s:a>
                                </td>
                            </tr>
                        </s:iterator>
                    </table>
                </s:if>
                <s:else>
                    Aucune Categorie dans la liste
                </s:else>
            </sx:div>
        </div>
    </div>
</div>
</body>
</html>