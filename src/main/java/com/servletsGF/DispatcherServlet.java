package com.servletsGF;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/dispatcher")
public class DispatcherServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination = request.getParameter("destination");

        String title = request.getParameter("title");
        String genre = request.getParameter("genre");
        String deleteMovieID = request.getParameter("deleteMovieId");
        String movieID = request.getParameter("movieID");
        String deleteActorID = request.getParameter("deleteActorId");

        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String gender = request.getParameter("gender");


        RequestDispatcher rdAddMovie = request.getRequestDispatcher("/addMovie");
        RequestDispatcher rdAddActor = request.getRequestDispatcher("/addActor");
        RequestDispatcher rdAddMovieLogic = request.getRequestDispatcher("/addMovieLogic");
        RequestDispatcher rdAddActorLogic = request.getRequestDispatcher("/addActorLogic");
        RequestDispatcher rdMovies = request.getRequestDispatcher("/movies");
        RequestDispatcher rdStatus = request.getRequestDispatcher("/status");
        RequestDispatcher rdDeleteMovie = request.getRequestDispatcher("/deleteMovie");
        RequestDispatcher rdDeleteActor = request.getRequestDispatcher("/deleteActor");
        RequestDispatcher rdActors = request.getRequestDispatcher("/actors");

        if (destination == null && (title == null || genre == null ) && deleteMovieID == null && deleteActorID == null && movieID == null) {
            String location = request.getRequestURL().toString().split("/")[3];
            response.sendRedirect("/"+location+"/");
        }
        else if (destination == null) {
            if (movieID != null && name == null) {
                System.out.println("Forwarding from dispatcher with request /actors");
                rdActors.forward(request, response);
            }
            else if (title != null) {
                System.out.println("Forwarding from dispatcher with request /addMovieLogic");
                rdAddMovieLogic.forward(request, response);
            }
            else if (name != null) {
                System.out.println("Forwarding from dispatcher with request /addActorLogic");
                rdAddActorLogic.forward(request, response);
            }
            else if(deleteMovieID != null){
                System.out.println("Fowarding from dispatcher with request /deleteMovie");
                rdDeleteMovie.forward(request, response);
            }
            else {
                System.out.println("Fowarding from dispatcher with request /deleteMovie");
                rdDeleteActor.forward(request, response);
            }
        }
        else {
            switch (destination) {
                case "st":
                    System.out.println("Forwarding from servlet with destination " + destination);
                    rdStatus.forward(request, response);
                    break;
                case "mv":
                    System.out.println("Forwarding from servlet with destination " + destination);
                    rdMovies.forward(request, response);
                    break;
                case "addMovie":
                    System.out.println("Forwarding from servlet with destination " + destination);
                    rdAddMovie.forward(request, response);
                    break;
                case "addActor":
                    System.out.println("Forwarding from servlet with destination " + destination);
                    rdAddActor.forward(request, response);
                    break;
                case "home":
                    String location = request.getRequestURL().toString().split("/")[3];
                    response.sendRedirect("/"+location+"/");
                    break;
            }
        }

    }
}
