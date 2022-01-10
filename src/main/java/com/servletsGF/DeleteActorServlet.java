package com.servletsGF;




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

@WebServlet("/deleteActor")
public class DeleteActorServlet extends HttpServlet {

    private Properties JNDIProps;
    private Context context;
    private ActorRepositoryR actorRepository;

    static final String ACTORS_JNDI = "java:global/EJBGF/ActorsRepositoryBean!com.repo.ActorRepositoryR";

    public DeleteActorServlet() throws NamingException {
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
            String id = request.getParameter("deleteActorId");
            int ID = 0;
            if(id != null) {
                ID = Integer.parseInt(id);
            }
            else {
                request.getRequestDispatcher("/error").forward(request, response);
            }
            if (actorRepository != null) {
                actorRepository.deleteById(ID);
                System.out.println("Actor with id "+id+" deleted");
            }
            else {
                request.getRequestDispatcher("/error").forward(request, response);
            }

            request.getRequestDispatcher("/").forward(request, response);

        }catch (Exception e){
            request.getRequestDispatcher("/").forward(request, response);
        }

    }
}
