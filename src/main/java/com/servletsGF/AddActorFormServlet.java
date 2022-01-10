package com.servletsGF;


import com.model.Movie;
import com.repo.MovieRepositoryR;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@WebServlet("/addActor")
public class AddActorFormServlet extends HttpServlet {

    private Properties JNDIProps;
    private Context context;
    private MovieRepositoryR movieRepository;


    static final String JNDI = "java:global/EJBGF/MovieRepositoryBean!com.repo.MovieRepositoryR";

    public AddActorFormServlet() throws NamingException {
        JNDIProps = new Properties();
        JNDIProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put("org.omg.CORBA.ORBInitialHost", "localhost");
        JNDIProps.put("org.omg.CORBA.ORBInitialPort", "3700");
        context = new InitialContext(JNDIProps);
        movieRepository = (MovieRepositoryR) context.lookup(JNDI);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List<Movie> movies = new ArrayList<>();
        if (movieRepository != null) {
            movies = movieRepository.getAll();
        }
        StringBuilder options = new StringBuilder();
        for (Movie mov: movies
             ) {
            options.append("<option value=\"").append(mov.getId()).append("\">").append(mov.getTitle()).append("</option>\n");
        }
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
                "        .gender-wrapper {\n" +
                "            display: flex;\n" +
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
                "        select {\n" +
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
                "    <label for=\"name\">Name</label>\n" +
                "    <input  name=\"name\" id=\"name\" type=\"text\"/><br/>\n" +
                "\n" +
                "    <label for=\"age\">Age</label>\n" +
                "    <input  name=\"age\" id=\"age\" type=\"text\"/><br/>\n" +
                "\n" +
                "    <label for=\"movieID\">Movie</label>\n" +
                "    <select name=\"movieID\" id=\"movieID\">\n" +
                options+
                "    </select>\n" +
                "\n" +
                "    <p>Choose the gender</p>\n" +
                "    <div class=\"gender-wrapper\">\n" +
                "        <label for=\"male\">Male</label>\n" +
                "        <input  name=\"gender\" id=\"male\" value=\"Male\" type=\"radio\"/>\n" +
                "        <label for=\"male\">Female</label>\n" +
                "        <input  name=\"gender\" id=\"female\" value=\"Female\" type=\"radio\"/>\n" +
                "    </div>\n" +
                "\n" +
                "\n" +
                "    <br/>\n" +
                "\n" +
                "    <input class=\"button\" type=\"submit\" value=\"Add Actor to movie\"/>\n" +
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
