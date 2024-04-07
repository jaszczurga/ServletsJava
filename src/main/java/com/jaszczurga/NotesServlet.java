package com.jaszczurga;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class NotesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getRequestURI().equalsIgnoreCase("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>Hello World</h1>\n" +
                            "<p>This is my very first, embedded Tomcat, HTML Page!</p>\n" +
                            "</body>\n" +
                            "</html>");
        }
        else if (request.getRequestURI().equalsIgnoreCase("/notes")) {
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().print("[]");
        }
    }

}
