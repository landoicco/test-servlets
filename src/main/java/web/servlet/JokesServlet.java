package web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;

public class JokesServlet extends HttpServlet {

    String jokeResponse;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        getJokeAnswer(req.getParameter("joke"));

        //resp.getWriter().append(jokeResponse);
        resp.getWriter().write(jokeResponse);

    }

    private void getJokeAnswer(String joke) {
        switch (joke) {
            case "jokeone":
                jokeResponse = "{\n" +
                        "  \"Question\": \"Whats the object-oriented way to become wealthy?\",\n" +
                        "  \"Answer\" : \"Inheritance\"\n" +
                        "}";
                break;
            case "joketwo":
                jokeResponse = "{\n" +
                        "  \"Question\": \"What did the Java code say to the C code?\",\n" +
                        "  \"Answer\" : \"You've got no class\"\n" +
                        "}";
                break;
            case "jokethree":
                jokeResponse = "{\n" +
                        "  \"Question\": \"What do cats and programmers have in common?\",\n" +
                        "  \"Answer\" : \"When either one is unusually happy and excited, an appropriate question would be," +
                        "  did you find a bug?\"\n" +
                        "}";
                break;
            case "jokefour":
                jokeResponse = "{\n" +
                        "  \"Question\": \"What is the most used language in programming?\",\n" +
                        "  \"Answer\" : \"Profanity\"\n" +
                        "}";
                break;
        }
    }
}
