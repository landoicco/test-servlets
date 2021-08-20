package web.servlet;

import local.user.User;
import database.user.UserDAO;
import database.user.UserJDBC;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class TestingDBServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserDAO user = new UserJDBC();

//        try {
//            user.insert(new User("pepito", "juarez", "asd", 56));
//        } catch (SQLException exception) {
//            exception.printStackTrace();
//        }

        PrintWriter out = resp.getWriter();
//        try {
//            for(User u : user.select()){
//                out.println(u.getFirstName());
//            }
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }

        for (User u : user.select()) {
            out.println(u.getFirstName());

        }

//        user.delete(new User("pepito", "juarez", "asd", 56));

//        for (User u : user.select()) {
//            out.println(u.getFirstName());
//
//        }
    }
}
