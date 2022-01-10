package com.servletsGF;




import com.model.Actor;
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
import java.util.Properties;

@WebServlet("/addActorLogic")
public class AddActorServlet extends HttpServlet {

    private Properties JNDIProps;
    private Context context;
    private ActorRepositoryR actorRepository;

    static final String ACTORS_JNDI = "java:global/EJBGF/ActorsRepositoryBean!com.repo.ActorRepositoryR";

    public AddActorServlet() throws NamingException {
        JNDIProps = new Properties();
        JNDIProps.put("java.naming.factory.initial", "com.sun.enterprise.naming.impl.SerialInitContextFactory");
        JNDIProps.put("org.omg.CORBA.ORBInitialHost", "localhost");
        JNDIProps.put("org.omg.CORBA.ORBInitialPort", "3700");
        context = new InitialContext(JNDIProps);
        actorRepository = (ActorRepositoryR) context.lookup(ACTORS_JNDI);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            int movieID = Integer.parseInt(request.getParameter("movieID"));
            int id;
            if (actorRepository != null) {
                Actor actor = new Actor();
                actor.setName(name);
                actor.setAge(age);
                actor.setGender(gender);
                actorRepository.save(actor, movieID);
            }

            request.setAttribute("name", null);
            request.getRequestDispatcher("/actors").forward(request, response);

        }catch (Exception e){
            request.getRequestDispatcher("/error").forward(request, response);
        }

    }
}
