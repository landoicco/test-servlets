package web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class JokesServlet extends HttpServlet {

    String answer;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/plain");
        resp.setCharacterEncoding("UTF-8");

        getJokeAnswer(req.getParameter("joke"));

        resp.getWriter().write(answer);

    }

    private void getJokeAnswer(String joke)
    {
        switch (joke){
            case "jokeone":
                answer = "A= Inheritance";
                break;
            case "joketwo":
                answer = "A= You've got no class";
                break;
            case "jokethree":
                answer = "A= When either one is unusually happy and excited, an appropriate question would be,\n" +
                        "      \"did you find a bug?";
                break;
            case "jokefour":
                answer = "A= Profanity";
                break;
        }
    }
}
