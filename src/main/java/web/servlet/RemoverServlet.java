package web.servlet;

import database.user.UserDAO;
import database.user.UserDTO;
import database.user.UserJDBC;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RemoverServlet extends HttpServlet {

    UserDAO dbGate;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Recuperar user de HttpSession
        HttpSession session = request.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");

        //Abrir acceso a DB si no esta abierta ya!
        if (dbGate == null) {
            dbGate = new UserJDBC();
        }

        //Eliminar usuario de DB
        dbGate.delete(user);

        //Invalidar HttpSession
        session.invalidate();

        //Redireccionar a LandingPage
        response.sendRedirect(request.getContextPath() + "/");

    }
}
