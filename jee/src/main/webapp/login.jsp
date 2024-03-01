<%@ include file="WEB-INF/jspf/nav.jspf" %>

<form action="savePerson.jsp" method="post">
    <label for="firstname">First Name:</label><br>
    <input type="text" id="firstname" name="firstName" required><br>
    <label for="lastname">Last Name:</label><br>
    <input type="text" id="lastname" name="lastName" required><br>
    <label for="password">Password:</label><br>
    <input type="password" id="password" name="password" required><br>
    <input type="submit" value="Submit">
</form>

<%@ include file="WEB-INF/jspf/footer.jspf"%>