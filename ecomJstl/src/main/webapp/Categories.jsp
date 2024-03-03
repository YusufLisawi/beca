<%@ include file="WEB-INF/jspf/header.jspf"%>
<h1>
    <fmt:message key="categorie.titre.liste" />
</h1>

<%--  <c:set var = "now" value = "<%= new java.util.Date()%>" /> --%>
<jsp:useBean id="now" class="java.util.Date" />
<h2>
    <fmt:formatDate type="both" dateStyle="long" timeStyle="long"
                    value="${now}" />
</h2>

<form method='post' action='adminCategorie'>
    <table border='1' style="text-align:right">
        <tr>
            <td><fmt:message key="categorie.search" />:<input type='text' name='motCle'
                                              value='${catForm.motCle}'></td>
            <td><input type='submit' name='chercheCat' value='Chercher'></td>
        </tr>
    </table>
</form>
<form method='post' action='adminCategorie'>
    <table class="table table-striped table-hover">
        <thead class="thead-dark">
        <tr>
            <th>Id Categorie</th>
            <th>Nom Categorie</th>
            <th>Description</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td></td>
            <td><input type='text' name='nomCat'></td>
            <td><input type='text' name='description' size='40'></td>
            <td><input type='submit' name='addCat' value='<fmt:message key="categorie.titre.add" />'></td>
        </tr>
        <c:if test="${not empty catForm}">

            <c:forEach items="${catForm.lesCats}" var="cat">
                <tr>
                    <td>${cat.id}</td>
                    <td>${cat.nom}</td>
                    <td>${cat.description}</td>
                    <c:url value="adminCategorie" var="myURL">
                        <c:param name="idCat" value="${cat.id}" />
                    </c:url>
                    <td><a href='<c:out value="${myURL}"/>'>Supprimer</a></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>
<%@ include file="WEB-INF/jspf/footer.jspf"%>

