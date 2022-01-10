package com.servletsGF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addMovie")
public class AddMovieFormServlet extends HttpServlet {
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
                "    <title>Aiemdibi</title>\n" +
                "    <style>\n" +
                "        .hidden {\n" +
                "            display: none;\n" +
                "        }\n" +
                "        .form-wrapper {\n" +
                "            display: flex;\n" +
                "            flex-direction: column;\n" +
                "            justify-content: space-evenly;\n" +
                "            align-items: center;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                "        input[type=\"text\"] {\n" +
                "            padding-top: 3px;\n" +
                "            padding-bottom: 3px;\n" +
                "            width: 30%;\n" +
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
                "        .cancel-form {\n" +
                "            position: absolute;\n" +
                "            top: 5%;\n" +
                "            left:2%;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form class=\"form-wrapper\" action=\"dispatcher\" method=\"post\">\n" +
                "    <h1>Add a new movie</h1>\n" +
                "    <label for=\"title\">Title</label>\n" +
                "    <input  name=\"title\" id=\"title\" type=\"text\"/><br/>\n" +
                "\n" +
                "    <label for=\"rating\">Rating</label>\n" +
                "    <input  name=\"rating\" id=\"rating\" type=\"text\"/><br/>\n" +
                "\n" +
                "    <label for=\"genre\">Genre</label>\n" +
                "    <input  name=\"genre\" id=\"genre\" type=\"text\"/><br/>\n" +
                "\n" +
                "\n" +
                "        <input class=\"button\" type=\"submit\" value=\"Add Movie\"/>\n" +
                "</form>\n" +
                "<form class=\"cancel-form\" action=\"dispatcher\" method=\"post\">\n" +
                "    <input type=\"text\" name=\"destination\" value=\"home\" class=\"hidden\"/>\n" +
                "    <input type=\"submit\" value=\"Cancel\" class=\"button\"/>\n" +
                "</form>\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        out.flush();
    }
}
