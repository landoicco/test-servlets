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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperar parametros del RegisterJSP
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String ageString = request.getParameter("age");
        String password = request.getParameter("password");

        RegisterRequester registerRequester = new RegisterRequester(firstName, lastName, password, ageString);

        if (isValidData(registerRequester)) {

            //Abrir acceso a DB
            if (dbGate == null) {
                dbGate = new UserJDBC();
            }

            //Crear objeto User
            User newUser = new User(firstName, lastName, password,
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

        request.setAttribute("wrongData", "true");
        RequestDispatcher rq = request.getRequestDispatcher("/register");
        rq.forward(request, response);
    }

    private boolean isValidData(RegisterRequester requester) {

        // Verificar que los datos ingresados sean válidos
        if ("".equals(requester.getFirstName()) || "".equals(requester.getLastName()) ||
                "".equals(requester.getAge()) || "".equals(requester.getPassword())) {
            return false;
        }
        //Verificar que la edad ingresada si sea entero
        int age;
        try {
            age = Integer.parseInt(requester.getAge());
        } catch (NumberFormatException ex) {
            return false;
        }
        return age >= 1 && age <= 99;
    }

    private User getUser(User user) {

        // Comprobar que existe el usuario solicitado
        for (User u : dbGate.select()) {
            if (u.getFirstName().equals(user.getFirstName()) &&
                    u.getPassword().equals(user.getPassword())) {
                return u;
            }
        }
        return null;
    }
}
