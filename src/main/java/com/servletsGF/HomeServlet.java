package com.servletsGF;



import javax.servlet.ServletException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/")
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Movie site</title>\n" +
                "    <style>\n" +
                "        .hidden-input {\n" +
                "            display: none;\n" +
                "        }\n" +
                "        body {\n" +
                "            height: 100vh;\n" +
                "        }\n" +
                "        .page {\n" +
                "            display: flex;\n" +
                "            justify-content: space-evenly;\n" +
                "            align-items: center;\n" +
                "            height: 40%;\n" +
                "            flex-direction: column;\n" +
                "        }\n" +
                "        .button-wrapper {\n" +
                "            display: flex;\n" +
                "            margin-top: 20px;\n" +
                "            justify-content: space-between;\n" +
                "            align-items:center;\n" +
                "            width: 60%;\n" +
                "        }\n" +
                "        .button {\n" +
                "            background: #45a3ff;\n" +
                "            border-radius: 7px;\n" +
                "            padding: 10px;\n" +
                "            border-width: 0;\n" +
                "            width: 150px;\n" +
                "            color: white;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"page\">\n" +
                "    <h1>Hello there!</h1>\n" +
                "    <h2>Welcome to the movie database! </h2>\n" +
                "    <h2>You can view all the movies or add a movie!</h2>\n" +
                "    <div class=\"button-wrapper\">\n" +
                "        <form action=\"dispatcher\" method=\"post\">\n" +
                "            <input type=\"text\" name=\"destination\" value=\"mv\" class=\"hidden-input\"/>\n" +
                "            <input class=\"button\" type=\"submit\" value=\"Movie list\"/>\n" +
                "        </form>\n" +
                "        <form action=\"dispatcher\" method=\"post\">\n" +
                "            <input type=\"text\" name=\"destination\" value=\"addMovie\" class=\"hidden-input\"/>\n" +
                "            <input class=\"button\" type=\"submit\" value=\"Add movie\"/>\n" +
                "        </form>\n" +
                "       <form action=\"dispatcher\" method=\"post\">\n" +
                "            <input type=\"text\" name=\"destination\" value=\"addActor\" class=\"hidden-input\"/>\n" +
                "            <input class=\"button\" type=\"submit\" value=\"Add actor\"/>\n" +
                "        </form>\n" +
                "        <form action=\"dispatcher\" method=\"post\">\n" +
                "            <input type=\"text\" name=\"destination\" value=\"st\" class=\"hidden-input\"/>\n" +
                "            <input class=\"button\" type=\"submit\" value=\"Status of the app\"/>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        out.flush();
    }
}
