<%@ page import="org.nttdata.jeedb.db.ListMessages" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Messages</title>
</head>
<body>
<h1>Messages</h1>
<%
    ResultSet rs = null;
    try {
        ListMessages listMessages = new ListMessages(1); // replace 1 with the actual personId
        rs = listMessages.getMessages();
%>
<table border="1">
    <tr>
        <th>Message Subject</th>
        <th>Message Text</th>
        <!-- Add more columns as per your table structure -->
    </tr>
    <%
        while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("subject") %></td>
        <td><%= rs.getString("text") %></td>
        <!-- Fetch more columns as per your table structure -->
    </tr>
    <%
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    %>
</table>
</body>
</html>