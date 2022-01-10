package com.servletsGF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "    <style>\n" +
                "        .hidden-input {\n" +
                "            display: none;\n" +
                "        }\n" +
                "        .page {\n" +
                "            display: flex;\n" +
                "            justify-content: space-evenly;\n" +
                "            align-items: center;\n" +
                "            height: 40%;\n" +
                "            flex-direction: column;\n" +
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
                "        .button-wrapper {\n" +
                "            display: flex;\n" +
                "            margin-top: 20px;\n" +
                "            justify-content: space-evenly;\n" +
                "            align-items:center;\n" +
                "            width: 60%;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"page\">\n" +
                "    <h1>There was a server error!</h1>\n" +
                "    <div class=\"button-wrapper\">\n" +
                "        <form action=\"dispatcher\" method=\"post\">\n" +
                "            <input class=\"hidden-input\" type=\"text\" name=\"destination\" value=\"home\" />\n" +
                "            <input class=\"button\" type=\"submit\" value=\"Go back home\"/>\n" +
                "        </form>\n" +
                "        <form action=\"dispatcher\" method=\"post\">\n" +
                "            <input class=\"hidden-input\" type=\"text\" name=\"destination\" value=\"mv\" />\n" +
                "            <input class=\"button\" type=\"submit\" value=\"See the movie list\"/>\n" +
                "        </form>\n" +
                "    </div>\n" +
                "\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>");
        out.flush();
    }
}
