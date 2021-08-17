package web.servlet;

import java.io.IOException;

import database.user.*;
import local.user.LoginRequester;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    UserDAO dbGate;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (dbGate == null) {
            dbGate = new UserJDBC();
        }

        // Recuperar parametros de la peticion
        String colaborador = request.getParameter("colaborador");
        String password = request.getParameter("password");

        // Create a LoginRequester object
        LoginRequester loginRequester = new LoginRequester(colaborador, password);

        UserDTO requestedUser = getUser(loginRequester);

        if (requestedUser != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user", requestedUser);

            response.sendRedirect(request.getContextPath() + "/secret/jokes");
            return;
        }

        request.setAttribute("wrongData", "true");
        RequestDispatcher rq = request.getRequestDispatcher("/login");
        rq.forward(request, response);

    }

    private UserDTO getUser(LoginRequester loginRequester) {

        // Comprobar que existe el usuario solicitado
        for (UserDTO u : dbGate.select()) {
            if (u.getFirstName().equals(loginRequester.getName()) &&
                    u.getPassword().equals(loginRequester.getPassword())) {
                return u;
            }
        }
        return null;
    }
}