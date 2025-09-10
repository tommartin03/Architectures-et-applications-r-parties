package servlets;

import facades.Facade;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class Exo3Servlet extends HttpServlet {

    private final Facade facade=Facade.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWhatever(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doWhatever(request,response);
    }

    private void doWhatever(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String caractere = request.getParameter("lecaractere");
        String todo = request.getParameter("TODO");
        if(todo==null){
            request.getRequestDispatcher("/WEB-INF/ajout.jsp").forward(request,response);
        }
        else {
            switch (todo) {
                case "ajoutmot":
                    String lemot=request.getParameter("lemot");
                    if (lemot==null || lemot.isBlank()) {
                        request.setAttribute("msg", "Veuillez entrer un mot non vide");
                        request.getRequestDispatcher("/WEB-INF/ajout.jsp").forward(request,response);
                        return;
                    }
                    facade.setMots(lemot);
                    request.getRequestDispatcher("/WEB-INF/ajout.jsp").forward(request,response);
                    break;
                case "mot":
                    request.setAttribute("lesmots",facade.getMot());
                    request.getRequestDispatcher("/WEB-INF/mots.jsp").forward(request,response);
                    break;
                case "mots":
                    request.setAttribute("lesmots",facade.getMots());
                    request.getRequestDispatcher("/WEB-INF/mots.jsp").forward(request,response);
                    break;
            }
        }
    }



}
