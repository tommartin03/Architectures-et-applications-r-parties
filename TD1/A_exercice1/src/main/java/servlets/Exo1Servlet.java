package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(urlPatterns = "/")
public class Exo1Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO appeler la jsp de login
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//TODO récupérer le login et le mdp et les comparer avec la paire stockée statiquement; appeler la jsp bonjour en cas de succès et la jsp de login sinon
        String login_admin = "admin";
        String password_admin = "admin";

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (Objects.equals(login_admin,login) && Objects.equals(password_admin,password)) {
            request.setAttribute("login", login);
            request.getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Login ou mot de passe incorrect !");
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
    }
}
