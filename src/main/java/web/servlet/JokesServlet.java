package web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import local.jokes.Joke;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JokesServlet extends HttpServlet {

    Map jokeBucket;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (jokeBucket == null) {
            jokeBucket = createJokes();
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String jokeResponse = getJoke(req.getParameter("joke"));
        resp.getWriter().write(jokeResponse);

    }

    private Map createJokes() {

        final Joke jokeone = new Joke(1, "Whats the object-oriented way to become wealthy?",
                "Inheritance");
        final Joke joketwo = new Joke(2, "What did the Java code say to the C code?",
                "You've got no class");
        final Joke jokethree = new Joke(3, "What do cats and programmers have in common?",
                "When either one is unusually happy and excited, an appropriate question would be, " +
                        "did you find a bug?");
        final Joke jokefour = new Joke(4, "What is the most used language in programming?",
                "Profanity");

        return new HashMap<Integer, Joke>()
        {{
            put(jokeone.getId(), jokeone);
            put(joketwo.getId(), joketwo);
            put(jokethree.getId(), jokethree);
            put(jokefour.getId(), jokefour);
        }};

    }

    private String getJoke(String joke) {

        int requestedJoke = 0;

        switch (joke) {
            case "jokeone":
                requestedJoke = 1;
                break;
            case "joketwo":
                requestedJoke = 2;
                break;
            case "jokethree":
                requestedJoke = 3;
                break;
            case "jokefour":
                requestedJoke = 4;
                break;
        }

        String question = ((Joke) jokeBucket.get(requestedJoke)).getQuestion();
        String answer = ((Joke) jokeBucket.get(requestedJoke)).getAnswer();

        return  "{\n" +
                "  \"Question\": " + "\"" + question + "\"" + ",\n" +
                "  \"Answer\" : " + "\"" + answer + "\"" + "\n" +
                "}";
    }
}
