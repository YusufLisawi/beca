package org.nttdata.jee;

import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "form", value = "/form")
public class Form extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String title = request.getParameter("title");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        int age = Integer.parseInt(request.getParameter("age"));

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html><body>");
        out.println("<h1>Personal Data</h1>");
        out.println("<p>Title: " + title + "</p>");
        out.println("<p>First Name: " + firstName + "</p>");
        out.println("<p>Last Name: " + lastName + "</p>");
        out.println("<p>Age: " + age + "</p>");
        out.println("</body></html>");
    }
}

