package web.servlet.user;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import user.pojos.*;
import user.utilities.UserLoginHandler;

public class LoginServlet extends HttpServlet {

    String errorMessage;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperar parametros de la peticion
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Create a LoginRequester object
        LoginRequester loginRequester = new LoginRequester(username, password);

        User user = null;

        try {
            user = UserLoginHandler.getUser(loginRequester);
        } catch (IllegalArgumentException ex) {
            errorMessage = ex.getMessage();
        }

        if (user != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect(request.getContextPath() + "/secret/jokes");
            return;
        }

        request.setAttribute("submitStatus", errorMessage);
        request.setAttribute("loginRequester", loginRequester);
        RequestDispatcher rq = request.getRequestDispatcher("/login");
        rq.forward(request, response);

    }
}