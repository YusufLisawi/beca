<%@ include file="WEB-INF/jspf/header.jspf" %>
<h1>
    <fmt:message key="produit.titre.liste"/>
</h1>

<jsp:useBean id="now" class="java.util.Date"/>
<h2>
<<<<<<< HEAD
    <fmt:formatDate type="both" dateStyle="long" timeStyle="long"
                    value="${now}"/>
=======
    <fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${now}" />
>>>>>>> c8a99885a1f92e32087653c09f3fda8cf5eab926
</h2>

<form method='post' action='adminProduit'>
    <table border='1' style="text-align:right">
        <tr>
<<<<<<< HEAD
            <td><fmt:message key="produit.search"/>:<input type='text' name='motCle'
                                                           value='${prodForm.motCle}'></td>
=======
            <td><fmt:message key="produit.search" />:
                <input type='text' name='motCle' value='${prodForm.motCle}'>
                <select name="catKey">
                    <option value="" selected>---</option>
                    <c:forEach items="${prodForm.lesCats}" var="cat">
                            <option value="${cat.id}" ${prodForm.catKey == cat.id ? 'selected' : ''}>${cat.nom}</option>
                    </c:forEach>
                </select>
            </td>
>>>>>>> c8a99885a1f92e32087653c09f3fda8cf5eab926
            <td><input type='submit' name='chercheProd' value='Chercher'></td>
        </tr>
    </table>
</form>
<form method='post' action='adminProduit' enctype="multipart/form-data">
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr>
            <th>Id Produit</th>
            <th>Image</th>
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
            <td><input type='file' name='image' accept="image/*" required></td>
            <td><input type='text' name='designation' required></td>
            <td><input type='number' name='prix' required></td>
            <td><input type='number' name='quantite' required></td>
            <td>
                <select name="categoryId">
                    <c:forEach items="${prodForm.lesCats}" var="cat">
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
                    <td><img src="${prod.image}" width="100" height="100"></td>
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
<<<<<<< HEAD
    $(document).ready(function () {
        document.title = <fmt:message key="produit.titre.page" />;
        console.log("hello ", document.title)
    });
=======
   document.title = "<fmt:message key="produit.titre.page" />";
   console.log("hello " , document.title)
>>>>>>> c8a99885a1f92e32087653c09f3fda8cf5eab926
</script>
<%@ include file="WEB-INF/jspf/footer.jspf" %>
