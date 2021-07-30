package web.servlet;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperar parametros de la peticion
        String colaborador = request.getParameter("colaborador");
        String password = request.getParameter("password");

        if (hasAccess(colaborador, password)) {
            request.setAttribute("hasAccess", "true");
            request.setAttribute("colaborador", colaborador);
            RequestDispatcher rq = request.getRequestDispatcher("/web/sessionmanager.html");
            rq.include(request, response);
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }
        request.setAttribute("wrongData", "true");
        RequestDispatcher rq = request.getRequestDispatcher("/login.html");
        rq.forward(request, response);
    }

    private boolean hasAccess(String colaborador, String password) {

        // Pasar parametros a mayusculas
        String newColab = colaborador.toUpperCase();

        // Comprobar acceso
        String[] colaboradores = {"CARLOS", "GABRIEL", "JAIR", "LANDO", "ALBERTO", "OSCAR"};
        for (String c : colaboradores) {
            if (newColab.equals(c) && "TCS".equals(password)) {
                return true;
            }
        }
        return false;
    }
}