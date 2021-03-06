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

public class InfoUpdaterServlet extends HttpServlet {

    String errorMessage;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recuperar parametros del RegisterJSP
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String ageString = request.getParameter("age");
        String password = request.getParameter("password");

        RegisterRequester updatedData = new RegisterRequester(username, firstName,
                lastName, password, ageString);

        //Recuperar informacion de HttpSession
        HttpSession session = request.getSession();
        User oldUser = (User) session.getAttribute("user");

        User updatedUser = null;
        try {
            updatedUser = UserRegistrationHandler.getUpdatedUser(oldUser, updatedData);
        } catch (IllegalArgumentException ex) {
            errorMessage = ex.getMessage();
        }

        if (updatedUser != null) {

            //Actualizar usuario en HttpSession
            session.setAttribute("user", updatedUser);

            //Redireccionar a jokes ya con usuario actualizado en HttpSession
            response.sendRedirect(request.getContextPath() + "/secret/jokes");

            return;
        }

        request.setAttribute("submitStatus", errorMessage);
        RequestDispatcher rq = request.getRequestDispatcher("/secret/update");
        rq.forward(request, response);

    }
}

