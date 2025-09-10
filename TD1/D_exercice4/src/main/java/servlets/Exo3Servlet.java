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
        if (caractere==null || caractere.length()!=1) {
            caractere=" ";
        }
        String todo = request.getParameter("TODO");
        if(todo==null){
            request.getRequestDispatcher("/WEB-INF/pendu.jsp").forward(request,response);
        }
        else {
            switch (todo) {
                case "pendu":
                    String lemot=request.getParameter("lemot");
                    if (lemot==null || lemot.isBlank()) {
                        request.setAttribute("msg", "Veuillez entrer un mot non vide");
                        request.getRequestDispatcher("/WEB-INF/pendu.jsp").forward(request,response);
                        return;
                    }
                    facade.setaDeviner(lemot);
                    request.getRequestDispatcher("/WEB-INF/essai.jsp").forward(request,response);
                    break;
                case "essai":
                    facade.test(caractere.charAt(0));
                    request.setAttribute("trouve",facade.getDevine());
                    request.setAttribute("nbessais",facade.getNbEssaisRestants());
                    if(facade.getNbEssaisRestants()==0 || facade.getDevine().toString().equals(facade.getADeviner())){
                        if (facade.getNbEssaisRestants()==0) {
                            request.setAttribute("msg", "Perdu! Le mot était: " + facade.getADeviner());
                        }
                        else {
                            request.setAttribute("msg", "Gagné!");
                        }
                        request.getRequestDispatcher("/WEB-INF/pendu.jsp").forward(request,response);
                    }
                    else {
                        request.getRequestDispatcher("/WEB-INF/essai.jsp").forward(request,response);
                    }
                    break;
            }
        }
    }



}
