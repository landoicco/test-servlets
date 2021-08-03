package web.servlet;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import local.user.LoginRequester;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Recuperar parametros de la peticion
        String colaborador = request.getParameter("colaborador");
        String password = request.getParameter("password");

        // Create a LoginRequester object
        LoginRequester loginRequester = new LoginRequester(colaborador, password);

        if (hasAccess(loginRequester)) {

            HttpSession session = request.getSession();
            session.setAttribute("name", colaborador);
            session.setAttribute("hasAccess", "true");

            response.sendRedirect(request.getContextPath() + "/secret/home");
            return;
        }
        request.setAttribute("wrongData", "true");
        RequestDispatcher rq = request.getRequestDispatcher("/login");
        rq.forward(request, response);
    }

    private boolean hasAccess(LoginRequester loginRequester) {

        // Pasar parametros a mayusculas
        String newColab = loginRequester.getName().toUpperCase();

        // Comprobar acceso
        String[] colaboradores = {"CARLOS", "GABRIEL", "JAIR", "LANDO", "ALBERTO", "OSCAR"};
        for (String c : colaboradores) {
            if (newColab.equals(c) && "TCS".equals(loginRequester.getPassword())) {
                return true;
            }
        }
        return false;
    }
}