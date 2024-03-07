<%@ include file="WEB-INF/jspf/header.jspf" %>
<h1>
    <fmt:message key="produit.titre.liste"/>
</h1>

<jsp:useBean id="now" class="java.util.Date"/>
<h2>
    <fmt:formatDate type="both" dateStyle="long" timeStyle="long"
                    value="${now}"/>
</h2>

<form method='post' action='adminProduit'>
    <table border='1' style="text-align:right">
        <tr>
            <td><fmt:message key="produit.search"/>:<input type='text' name='motCle'
                                                           value='${prodForm.motCle}'></td>
            <td><input type='submit' name='chercheProd' value='Chercher'></td>
        </tr>
    </table>
</form>
<form method='post' action='adminProduit'>
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr>
            <th>Id Produit</th>
            <th>Designation</th>
            <th>Prix</th>
            <th>Quantite</th>
            <th>Categorie</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td></td>
            <td><input type='text' name='designation'></td>
            <td><input type='text' name='prix'></td>
            <td><input type='text' name='quantite'></td>
            <td>
                <select name="categoryId">
                    <c:forEach items="${catForm.lesCats}" var="cat">
                        <option value="${cat.id}">${cat.nom}</option>
                    </c:forEach>
                </select>
            </td>
            <td><input type='submit' name='addProd' value='<fmt:message key="produit.titre.add" />'></td>
        </tr>
        <c:if test="${not empty prodForm}">
            <c:forEach items="${prodForm.lesProds}" var="prod">
                <tr>
                    <td>${prod.id}</td>
                    <td>${prod.designation}</td>
                    <td>${prod.prix}</td>
                    <td>${prod.quantite}</td>
                    <td>${prod.categorie.nom}</td>
                    <c:url value="adminProduit" var="myURL">
                        <c:param name="idProd" value="${prod.id}"/>
                    </c:url>
                    <td><a href='<c:out value="${myURL}"/>'>Supprimer</a></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>
<script>
    $(document).ready(function () {
        document.title = <fmt:message key="produit.titre.page" />;
        console.log("hello ", document.title)
    });
</script>
<%@ include file="WEB-INF/jspf/footer.jspf" %>
