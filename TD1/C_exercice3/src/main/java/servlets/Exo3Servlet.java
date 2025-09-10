package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class Exo3Servlet extends HttpServlet {
    private String aDeviner=null;
    private StringBuilder devine=null;
    private int nbEssaisRestants;
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
                    setaDeviner(lemot);
                    request.getRequestDispatcher("/WEB-INF/essai.jsp").forward(request,response);
                    break;
                case "essai":
                    test(caractere.charAt(0));
                    request.setAttribute("trouve",devine);
                    request.setAttribute("nbessais",nbEssaisRestants);
                    if(nbEssaisRestants==0 || devine.toString().equals(aDeviner)){
                        if (nbEssaisRestants==0) {
                            request.setAttribute("msg", "Perdu! Le mot était: " + aDeviner);
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


    private void setaDeviner(String aDeviner) {
        this.aDeviner=aDeviner;
        this.devine=new StringBuilder("_".repeat(aDeviner.length()));
        this.nbEssaisRestants=10;
    }

    private boolean test(char carac){
        boolean res=false;
        int last=aDeviner.indexOf(carac,0);
        while (last!=-1) {
            res = true;
            devine.setCharAt(last, carac);
            last=aDeviner.indexOf(carac,last+1);
        }
        if (res==false) {
            nbEssaisRestants--;
        }
        return aDeviner.equals(devine.toString());
    }
}
