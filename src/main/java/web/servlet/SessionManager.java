package web.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SessionManager extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Object name = request.getAttribute("colaborador");
        Object hasAccess = request.getAttribute("hasAccess");

        HttpSession session = request.getSession();
        session.setAttribute("name", name);
        session.setAttribute("hasAccess", hasAccess);

//        PrintWriter out = response.getWriter();
//        out.println("Soy el SessionManager");
        RequestDispatcher rq = request.getRequestDispatcher("/welcome.html");
        rq.forward(request,response);

    }
}
