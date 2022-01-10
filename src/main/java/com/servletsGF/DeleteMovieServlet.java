package com.servletsGF;


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

@WebServlet("/deleteMovie")
public class DeleteMovieServlet extends HttpServlet {

    private Properties JNDIProps;
    private Context context;
    private MovieRepositoryR movieRepository;


    static final String JNDI = "java:global/EJBGF/MovieRepositoryBean!com.repo.MovieRepositoryR";

    public DeleteMovieServlet() throws NamingException {
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
            String id = request.getParameter("deleteId");
            int ID = 0;
            if(id != null) {
                ID = Integer.parseInt(id);
            }
            else {
                request.getRequestDispatcher("/error").forward(request, response);
            }
            if (movieRepository != null) {
                movieRepository.deleteById(ID);
                System.out.println("Movie with id "+id+" deleted");
                request.getRequestDispatcher("/").forward(request, response);
            }
            else  {
                request.getRequestDispatcher("/error").forward(request, response);
            }
        }catch (Exception e){
            request.getRequestDispatcher("/").forward(request, response);
        }

    }
}
