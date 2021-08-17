package web.servlet;

import database.user.UserDAO;
import database.user.UserDTO;
import database.user.UserJDBC;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class TestingDBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDAO user = new UserJDBC();

//        try {
//            user.insert(new UserDTO("pepito", "juarez", "asd", 56));
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }

        PrintWriter out = resp.getWriter();
//        try {
//            for(UserDTO u : user.select()){
//                out.println(u.getFirstName());
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

        for (UserDTO u : user.select()) {
            out.println(u.getFirstName());

        }

//        user.delete(new UserDTO("pepito", "juarez", "asd", 56));

//        for (UserDTO u : user.select()) {
//            out.println(u.getFirstName());
//
//        }
    }
}
