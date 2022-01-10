package com.servletsGF;

import com.model.Movie;
import com.repo.MovieRepository;
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

@WebServlet("/movies")
public class MoviesServlet extends HttpServlet {
    private Properties JNDIProps;
    private Context context;
    private MovieRepository movieRepository;


    static final String JNDI = "java:global/EJBGF/MovieRepositoryBean!com.repo.MovieRepository";

    public MoviesServlet() throws NamingException {
        JNDIProps = new Properties();
        JNDIProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put("org.omg.CORBA.ORBInitialHost", "localhost");
        JNDIProps.put("org.omg.CORBA.ORBInitialPort", "3700");
        context = new InitialContext(JNDIProps);
        movieRepository = (MovieRepository) context.lookup(JNDI);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        List<Movie> movies = new ArrayList<>();
        if (movieRepository != null) {
            movies = movieRepository.getAll();
            System.out.println(movies);
        }

        out.println("<!DOCTYPE html>");
        out.println("");
        out.println("<html lang=\"en\">");
        out.println("<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Movies</title>");
        out.println("<style>" +
                "table {\n" +
                "            font-family: arial, sans-serif;\n" +
                "            border-collapse: collapse;\n" +
                "            width: 100%;\n" +
                "        }\n" +
                ".button {\n" +
                        "            background: #e14e4e;\n" +
                        "            border-radius: 7px;\n" +
                        "            padding: 10px;\n" +
                        "            border-width: 0;\n" +
                        "            width: 130px;\n" +
                        "            color: white;\n" +
                        "            cursor: pointer;\n" +
                        "        }"+
                ".list-button {\n" +
                "            background: #00FF00;\n" +
                "            border-radius: 7px;\n" +
                "            padding: 10px;\n" +
                "            border-width: 0;\n" +
                "            width: 130px;\n" +
                "            color: white;\n" +
                "            cursor: pointer;\n" +
                "        }"+
                "\n" +
                "        .hidden { display: none;}   "+
                "        td, th {\n" +
                "            border: 1px solid #dddddd;\n" +
                "            text-align: left;\n" +
                "            padding: 8px;\n" +
                "        }\n" +
                "\n" +
                "        tr:nth-child(even) {\n" +
                "            background-color: #45a3ff;\n" +
                "        }\n" +
                "        .home-button {\n" +
                "            \n" +
                "            padding: 10px;\n" +
                "            cursor: pointer;\n" +
                "            border-width: 0;\n" +
                "            width: 150px;\n   "+
                "            color:white;\n     "+
                "            border-radius: 7px;\n" +
                "            background: #45a3ff;\n" +
                "            margin-bottom: 15px;\n" +
                "            text-transform: capitalize;\n" +
                "        }\n" +
                "        .home-input {\n" +
                "            display: none;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>");
       out.println("<div>\n" +
               "        <h2>Movies DataBase</h2>\n" +
               "            <form method=\"post\" action=\"dispatcher\">\n" +
               "                <input class=\"home-input\" type=\"text\" name=\"destination\" value=\"home\"/>\n" +
               "                <input class=\"home-button\" type=\"submit\" value=\"HOME\"/>\n" +
               "            </form>\n" +
               "        <table>");
        out.println("<tr>");
        out.println("<td>ID</td>");
        out.println("<td>Title</td>");
        out.println("<td>Rating</td>");
        out.println("<td>Genre</td>");
        out.println("<td>List of actors </td>");
        out.println("<td>Delete action</td>");
        if(movies != null) {


            for (Movie mov : movies
            ) {
                out.println("<tr>");
                out.println("<td>" + mov.getId() + "</td>");
                out.println("<td>" + mov.getTitle() + "</td>");
                out.println("<td>" + mov.getRating() + "/10</td>");
                out.println("<td>" + mov.getGenre() + "</td>");
                out.println("<td>\n" +
                        "                    <form action=\"dispatcher\" method=\"post\">\n" +
                        "                        <input type=\"text\" name=\"movieID\" class=\"hidden\" value=\"" + mov.getId() + "\" />\n" +
                        "                        <input class=\"list-button\" type=\"submit\" value=\"Show actors\">\n" +
                        "                    </form>\n" +
                        "                </td>");
                out.println("<td>\n" +
                        "<form action=\"dispatcher\" method=\"post\">\n" +
                        "                        <input type=\"text\" name=\"deleteMovieId\" class=\"hidden\" value=\"" + mov.getId() + "\" />\n" +
                        "                        <input class=\"button\" type=\"submit\" value=\"Delete\">\n" +
                        "                    </form>\n" +
                        "                </td>");
                out.println("</tr>");
            }
        }
        out.println("</table>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>");
        out.flush();
    }
}
