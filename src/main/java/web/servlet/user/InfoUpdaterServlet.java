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

public class InfoUpdaterServlet extends HttpServlet {

    UserDAO dbGate;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Recuperar parametros del RegisterJSP
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String ageString = request.getParameter("age");
        String password = request.getParameter("password");

        RegisterRequester updatedData = new RegisterRequester(firstName, lastName, password, ageString);

        if (isValidData(updatedData)) {
            if (dbGate == null) {
                dbGate = new UserJDBC();
            }

            //Recuperar informacion de HttpSession
            HttpSession session = request.getSession();
            User oldUser = (User) session.getAttribute("user");

            User updatedUser = getUpdatedUser(oldUser, updatedData);

            //Actualizar usuario en DB
            dbGate.update(updatedUser);

            //Actualiar usuario en HttpSession
            session.setAttribute("user", updatedUser);

            //Redireccionar a jokes ya con usuario actualizado en HttpSession
            response.sendRedirect(request.getContextPath() + "/secret/jokes");

            return;
        }

        request.setAttribute("wrongData", "true");
        RequestDispatcher rq = request.getRequestDispatcher("/secret/update");
        rq.forward(request, response);

    }

    private User getUpdatedUser(User oldUser, RegisterRequester updatedData) {


        // Ver que campos fueron actualizados!
        String updatedFirstName = "".equals(updatedData.getFirstName()) ?
                oldUser.getFirstName() : updatedData.getFirstName();
        String updatedLastName = "".equals(updatedData.getLastName()) ?
                oldUser.getLastName() : updatedData.getLastName();
        String updatedPassword = "".equals(updatedData.getPassword()) ?
                oldUser.getPassword() : updatedData.getPassword();
        int updatedAge = "".equals(updatedData.getAge()) ?
                oldUser.getAge() : Integer.parseInt(updatedData.getAge());

        return new User(oldUser.getId_user(), updatedFirstName,
                updatedLastName, updatedPassword, updatedAge);
    }

    private boolean isValidData(RegisterRequester updatedData) {

        //Verificar que al menos hay un valor por actualizar
        if ("".equals(updatedData.getFirstName()) && "".equals(updatedData.getLastName()) &&
                "".equals(updatedData.getPassword()) && "".equals(updatedData.getAge())) {
            return false;
        }
        //Verificar que la edad ingresada si sea entero
        int age;
        try {
            age = Integer.parseInt(updatedData.getAge());
        } catch (NumberFormatException ex) {
            return false;
        }
        return age >= 1 && age <= 99;
    }
}

