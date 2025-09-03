package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/")
public class Exo2Servlet extends HttpServlet {
    private final String[] options={"Beau","Couvert","Pluie","Neige"};

    private final Map<String, Integer> counters = new HashMap<>();

    public Exo2Servlet() {
        for (String opt : options) {
            counters.put(opt, 0);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        toJsp(request,response, null);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selected = request.getParameter("meteo");

        if (selected != null && counters.containsKey(selected)) {
            counters.put(selected, counters.get(selected) + 1);
        }


        toJsp(request,response,selected );
    }

    private void toJsp(HttpServletRequest request, HttpServletResponse response, String selected)
            throws ServletException, IOException {

        request.setAttribute("options", options);
        request.setAttribute("counters", counters);
        request.setAttribute("selected", selected);

        request.getRequestDispatcher("WEB-INF/meteo.jsp").forward(request, response);
    }
}
