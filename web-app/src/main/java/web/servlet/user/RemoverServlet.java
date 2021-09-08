package web.servlet.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.pojos.User;
import user.utilities.UserRegistrationHandler;

import java.io.IOException;

public class RemoverServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Recuperar user de HttpSession
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        //Eliminar usuario de DB
        UserRegistrationHandler.deleteUserRegister(user);

        //Invalidar HttpSession
        session.invalidate();

        //Redireccionar a LandingPage
        response.sendRedirect(request.getContextPath() + "/");

    }
}
