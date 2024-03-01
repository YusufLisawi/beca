<%@ page import="org.nttdata.jee.db.Person" %>

<jsp:useBean id="person" class="org.nttdata.jee.db.Person">
    <jsp:setProperty name="person" property="*" />
    <label>First Name:</label><br>
    <jsp:getProperty name="person" property="firstName" /><br>
    <label>Last Name:</label><br>
    <jsp:getProperty name="person" property="lastName" /><br>
    <label>Password:</label><br>
    <jsp:getProperty name="person" property="password" /><br>
</jsp:useBean>


<% if (person.save()) { %>
<p>Person saved successfully!</p>
<% } else { %>
<p>Person already exists!</p>
<% } %>