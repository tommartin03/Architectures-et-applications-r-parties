package servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import services.Facade;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exercice9Servlet extends HttpServlet {
    @Autowired
    Facade facade;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todo = request.getParameter("TODO");
        System.out.println("TODO = "+todo);
        if ("ChangePassword".equals(todo)) {
            request.getRequestDispatcher("WEB-INF/changePassword.jsp").forward(request,response);
        }
        else {
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todo=request.getParameter("TODO");
        System.out.println("TODO = "+todo);
        if (todo==null) {
            if (request.getSession().getAttribute("courant")!=null) {
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request,response);
            }else {
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
        } else {
            switch (todo) {
                case "Submit" :
                    checkLP(request,response);
                    break;
                case "Logout":
                    logout(request,response);
                    break;
                case "SimpleCheck":
                    simpleCheck(request,response);
                    break;
                case "DoChangePassword":
                    System.out.println("Changement de mot de passe demandé");
                    doChangePassword(request,response);
                    break;
            }
        }

    }



    private void checkLP(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password=request.getParameter("password");
        if (facade.checkLP(login,password)) {
            // on place courant dans le modèle, mais il s'agit d'un attribut de session, il se retrouve ainsi conservé en session
            request.getSession().setAttribute("courant",login);
            request.setAttribute("username",login);
            request.getRequestDispatcher("/WEB-INF/welcome.jsp").forward(request,response);
        } else {
            List<String> errors=new ArrayList<>();
            errors.add("Les informations saisies ne correspondent pas à un utilisateur connu.");
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        }
    }



    public void simpleCheck(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String courant=(String) request.getSession().getAttribute("courant");
        System.out.println(courant);
        request.setAttribute("username",courant);
        request.getRequestDispatcher("/WEB-INF/welcome.jsp").forward(request,response);
    }

    public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
        request.getSession().invalidate();
        request.getSession().removeAttribute("courant");
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }

    private void doChangePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = (String) request.getSession().getAttribute("courant");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        List<String> errors = new ArrayList<>();

        if (login == null) {
            errors.add("Vous devez être connecté pour changer votre mot de passe.");
        } else if (!facade.checkLP(login, oldPassword)) {
            errors.add("L'ancien mot de passe est incorrect.");
        } else if (newPassword == null || newPassword.isEmpty()) {
            errors.add("Le nouveau mot de passe ne peut pas être vide.");
        } else if (!newPassword.equals(confirmPassword)) {
            errors.add("Les deux saisies du nouveau mot de passe ne correspondent pas.");
        }

        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("WEB-INF/changePassword.jsp").forward(request, response);
            return;
        }

        // Mise à jour du mot de passe
        facade.changePassword(login, newPassword);
        System.out.println("Mot de passe changé pour l'utilisateur : " + login);

        // Message de succès
        request.setAttribute("success", "Mot de passe changé avec succès !");

        // Retour sur la page welcome
        request.setAttribute("username", login);
        request.getRequestDispatcher("/WEB-INF/welcome.jsp").forward(request, response);

    }



}
