package com.servletsGF;




import com.model.Actor;
import com.model.Movie;
import com.repo.ActorRepositoryR;
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
import java.util.List;
import java.util.Properties;

@WebServlet("/actors")
public class ActorsServlet extends HttpServlet {

    private Properties JNDIProps;
    private Context context;
    private MovieRepositoryR movieRepository;
    private ActorRepositoryR actorRepository;

    static final String MOVIES_JNDI = "java:global/EJBGF/MovieRepositoryBean!com.repo.MovieRepositoryR";
    static final String ACTORS_JNDI = "java:global/EJBGF/ActorsRepositoryBean!com.repo.ActorRepositoryR";

    public ActorsServlet() throws NamingException {
        JNDIProps = new Properties();
        JNDIProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put("org.omg.CORBA.ORBInitialHost", "localhost");
        JNDIProps.put("org.omg.CORBA.ORBInitialPort", "3700");
        context = new InitialContext(JNDIProps);
        actorRepository = (ActorRepositoryR) context.lookup(ACTORS_JNDI);
        movieRepository = (MovieRepositoryR) context.lookup(MOVIES_JNDI);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (request.getParameter("movieID") == null) {
            request.getRequestDispatcher("/").forward(request, response);
        }
        int movieID = Integer.parseInt(request.getParameter("movieID"));
        Movie movie = new Movie();
        List<Actor> actors = null;
        if(movieRepository != null && actorRepository != null) {
            movie = movieRepository.findById(movieID);
            actors = actorRepository.getActorByMovieID(movieID);
        }

        out.println("<!DOCTYPE html>");
        out.println("");
        out.println("<html lang=\"en\">");
        out.println("<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Actors</title>");
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
               "        <h2>Actors for Movie:"+movie.getTitle()+"</h2>\n" +
               "            <form method=\"post\" action=\"dispatcher\">\n" +
               "                <input class=\"home-input\" type=\"text\" name=\"destination\" value=\"home\"/>\n" +
               "                <input class=\"home-button\" type=\"submit\" value=\"HOME\"/>\n" +
               "            </form>\n" +
               "   <form method=\"post\" action=\"dispatcher\">\n" +
               "                <input class=\"home-input\" type=\"text\" name=\"destination\" value=\"mv\"/>\n" +
               "                <input class=\"home-button\" type=\"submit\" value=\"Back to movies list\"/>\n" +
               "            </form>\n" +
               "        <table>");
        out.println("<tr>");
        out.println("<td>Name</td>");
        out.println("<td>Age</td>");
        out.println("<td>Gender</td>");
        out.println("<td>Delete action</td>");
        if(actors != null) {
            for (Actor actor : actors
            ) {
                out.println("<tr>");
                out.println("<td>" + actor.getName() + "</td>");
                out.println("<td>" + actor.getAge() + "</td>");
                out.println("<td>" + actor.getGender() + "</td>");
                out.println("<td>\n" +
                        "                    <form action=\"dispatcher\" method=\"post\">\n" +
                        "                        <input type=\"text\" name=\"deleteActorId\" class=\"hidden\" value=\"" + actor.getId() + "\" />\n" +
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
