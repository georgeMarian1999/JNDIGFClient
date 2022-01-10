package com.servletsGF;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/status")
public class StatusServlet extends HttpServlet {
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
                "    <title>Status</title>\n" +
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
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div class=\"page\">\n" +
                "    <h1>Status: Application is running on localhost</h1>\n" +
                "    <form action=\"dispatcher\" method=\"post\">\n" +
                "        <input type=\"text\" name=\"destination\" value=\"home\" class=\"hidden-input\"/>\n" +
                "        <input type=\"submit\" value=\"Go back home\" class=\"button\"/>\n" +
                "    </form>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "</body>\n" +
                "</html>");
        out.flush();
    }
}
