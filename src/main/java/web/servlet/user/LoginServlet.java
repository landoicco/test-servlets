package web.servlet.user;

import java.io.IOException;

import database.user.*;
import local.user.LoginRequester;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import local.user.User;

public class LoginServlet extends HttpServlet {

    UserDAO dbGate;
    String errorMessage;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (dbGate == null) {
            dbGate = new UserJDBC();
        }

        // Recuperar parametros de la peticion
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Create a LoginRequester object
        LoginRequester loginRequester = new LoginRequester(username, password);

        boolean existsUser = false;

        try {
            existsUser = Validator.existsUser(loginRequester);
        } catch (IllegalArgumentException ex) {
            errorMessage = ex.getMessage();
        }

        if (existsUser) {

            User requestedUser = getUser(loginRequester);

            HttpSession session = request.getSession();
            session.setAttribute("user", requestedUser);

            response.sendRedirect(request.getContextPath() + "/secret/jokes");
            return;
        }

        request.setAttribute("submitStatus", errorMessage);
        request.setAttribute("loginRequester", loginRequester);
        RequestDispatcher rq = request.getRequestDispatcher("/login");
        rq.forward(request, response);

    }

    private User getUser(LoginRequester loginRequester) {

        // Devolver usuario de DB
        for (User u : dbGate.select()) {
            if (u.getUsername().equals(loginRequester.getUsername()) &&
                    u.getPassword().equals(loginRequester.getPassword())) {
                return u;
            }
        }
        return null;
    }
}