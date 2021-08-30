package web.servlet.user;

import database.user.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import local.user.RegisterRequester;
import local.user.User;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    UserDAO dbGate;
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

        boolean isAValidUser = false;
        try {
            isAValidUser = Validator.isValidNewUser(registerRequester);
        } catch (IllegalArgumentException ex) {
            errorMessage = ex.getMessage();
        }

        if (isAValidUser) {

            //Abrir acceso a DB
            if (dbGate == null) {
                dbGate = new UserJDBC();
            }

            //Crear objeto User
            User newUser = new User(username, firstName, lastName, password,
                    Integer.parseInt(ageString));
            // INSERT a DB
            dbGate.insert(newUser);

            //Get newUser from DB with id_user
            User user = (getUser(newUser) != null) ? getUser(newUser) : newUser;

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

    private User getUser(User user) {

        // Comprobar que existe el usuario solicitado
        for (User u : dbGate.select()) {
            if (u.getUsername().equals(user.getUsername()) &&
                    u.getPassword().equals(user.getPassword())) {
                return u;
            }
        }
        return null;
    }
}
