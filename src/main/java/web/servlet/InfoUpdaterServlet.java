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

        //Recuperar informacion de HttpSession
        HttpSession session = request.getSession();
        UserDTO oldUser = (UserDTO) session.getAttribute("user");

        UserDTO updatedUser = getUpdatedUser(oldUser, updatedData);

        if (updatedUser != null) {
            if (dbGate == null) {
                dbGate = new UserJDBC();
            }

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

    private UserDTO getUpdatedUser(UserDTO oldUser, RegisterRequester updatedData) {

        //Verificar que al menos hay un valor por actualizar
        if ("".equals(updatedData.getFirstName()) && "".equals(updatedData.getLastName()) &&
                "".equals(updatedData.getPassword()) && "".equals(updatedData.getAge())) {
            return null;
        }

        // Ver que campos fueron actualizados!
        String updatedFirstName = "".equals(updatedData.getFirstName()) ?
                oldUser.getFirstName() : updatedData.getFirstName();
        String updatedLastName = "".equals(updatedData.getLastName()) ?
                oldUser.getLastName() : updatedData.getLastName();
        String updatedPassword = "".equals(updatedData.getPassword()) ?
                oldUser.getPassword() : updatedData.getPassword();

        //Verificar que age sea un entero válido
        int updatedAge = oldUser.getAge();
        if (!("".equals(updatedData.getAge()))) {
            try {
                updatedAge = Integer.parseInt(updatedData.getAge());
            } catch (NumberFormatException ex) {
                return null;
            }
        }

        return new UserDTO(oldUser.getId_user(), updatedFirstName, updatedLastName, updatedPassword, updatedAge);
    }
}
