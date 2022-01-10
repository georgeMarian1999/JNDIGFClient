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
import java.util.Properties;

@WebServlet("/addMovieLogic")
public class AddServlet extends HttpServlet {

    private Properties JNDIProps;
    private Context context;
    private MovieRepositoryR movieRepository;


    static final String JNDI = "java:global/EJBGF/MovieRepositoryBean!com.repo.MovieRepositoryR";

    public AddServlet() throws NamingException {
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
        try {
            String title = request.getParameter("title");
            int rating = Integer.parseInt(request.getParameter("rating"));
            String genre = request.getParameter("genre");
            if (movieRepository != null) {
                Movie movie = new Movie();
                movie.setTitle(title);
                movie.setRating(rating);
                movie.setGenre(genre);
                movieRepository.save(movie);
            }

            System.out.println("New movie saved");
            request.getRequestDispatcher("/addSuccess").forward(request, response);

        }catch (Exception e){
            request.getRequestDispatcher("/error").forward(request, response);
        }

    }
}
