package web.servlet;

import database.user.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import local.user.RegisterRequester;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    UserDAO dbGate;
    int age;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Recuperar parametros del RegisterJSP
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String ageString = request.getParameter("age");
        String password = request.getParameter("password");

        RegisterRequester registerRequester = new RegisterRequester(firstName, lastName, password, ageString);

        if (validNewUser(registerRequester)) {

            //Abrir acceso a DB
            if (dbGate == null) {
                dbGate = new UserJDBC();
            }

            //Crear objeto UserDTO
            UserDTO newUser = new UserDTO(firstName, lastName, password, age);
            // INSERT a la base de datos
            dbGate.insert(newUser);

            //Crear HttpSession
            HttpSession session = request.getSession();
            session.setAttribute("user", newUser);

            //Redireccionar a jokes ya con session creada
            response.sendRedirect(request.getContextPath() + "/secret/jokes");
            return;

        }

        request.setAttribute("wrongData", "true");
        RequestDispatcher rq = request.getRequestDispatcher("/register");
        rq.forward(request, response);
    }

    private boolean validNewUser(RegisterRequester requester) {

        // Verificar que los datos ingresados sean válidos
        if ("".equals(requester.getFirstName()) || "".equals(requester.getLastName()) ||
                "".equals(requester.getAge()) || "".equals(requester.getPassword())) {
            return false;
        }
        try {
            age = Integer.parseInt(requester.getAge());
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;

    }
}
