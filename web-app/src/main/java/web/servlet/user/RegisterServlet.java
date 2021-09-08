package web.servlet.user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.pojos.*;
import user.utilities.UserRegistrationHandler;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    String errorMessage;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperar parametros del RegisterJSP
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String ageString = request.getParameter("age");
        String password = request.getParameter("password");

        RegisterRequester registerRequester = new RegisterRequester(username, firstName, lastName, password, ageString);

        User user = null;
        try {
            user = UserRegistrationHandler.getRegisteredUser(registerRequester);
        } catch (IllegalArgumentException ex) {
            errorMessage = ex.getMessage();
        }

        if (user != null) {

            //Crear HttpSession
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            //Redireccionar a jokes ya con session creada
            response.sendRedirect(request.getContextPath() + "/secret/jokes");
            return;

        }

        request.setAttribute("submitStatus", errorMessage);
        request.setAttribute("registerRequester", registerRequester);
        RequestDispatcher rq = request.getRequestDispatcher("/register");
        rq.forward(request, response);
    }
}
