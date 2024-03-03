<%@ page import="org.nttdata.jee.db.ListMessages" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page errorPage="WEB-INF/error.jsp" %>
<%@ include file="WEB-INF/jspf/nav.jspf" %>
<%
    ResultSet rs = null;
    try {
        ListMessages listMessages = new ListMessages(1);
        rs = listMessages.getMessages();
%>
<table border="1">
    <tr>
        <th>Message Subject</th>
        <th>Message Text</th>
    </tr>
    <%
        while (rs.next()) {
    %>
    <tr>
        <td><%= rs.getString("subject") %></td>
        <td><%= rs.getString("text") %></td>
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
<%@ include file="WEB-INF/jspf/footer.jspf" %>
