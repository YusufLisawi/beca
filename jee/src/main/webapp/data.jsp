<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal Data</title>
</head>
<body>
    <h1>Personal Data</h1>
    <%= "Title : " + request.getParameter("title") %> <br>
    <%= "First Name : " + request.getParameter("firstname") %> <br>
    <%= "Last Name : " + request.getParameter("lastname") %> <br>
    <%= "Age : " + Integer.parseInt(request.getParameter("age")) %>
</body>
</html>
