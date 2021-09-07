package web.servlet.jokes;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import local.jokes.Joke;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JokesServlet extends HttpServlet {

    Map<String, Joke> jokeBucket;

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

    private Map<String, Joke> createJokes() {

        final Joke jokeone = new Joke("jokeone", "Whats the object-oriented way to become wealthy?",
                "Inheritance");
        final Joke joketwo = new Joke("joketwo", "What did the Java code say to the C code?",
                "You've got no class");
        final Joke jokethree = new Joke("jokethree", "What do cats and programmers have in common?",
                "When either one is unusually happy and excited, an appropriate question would be, " +
                        "did you find a bug?");
        final Joke jokefour = new Joke("jokefour", "What is the most used language in programming?",
                "Profanity");
        final Joke nulljoke = new Joke("nulljoke", "That joke doesn't exist!", "Nice try, hacker!");

        return new HashMap<String, Joke>() {{
            put(jokeone.getId(), jokeone);
            put(joketwo.getId(), joketwo);
            put(jokethree.getId(), jokethree);
            put(jokefour.getId(), jokefour);
            put(nulljoke.getId(), nulljoke);
        }};

    }

    private String getJoke(String joke) {

        Joke requestedJoke = jokeBucket.get(joke) != null ?
                jokeBucket.get(joke) : jokeBucket.get("nulljoke");

        return "{\n" +
                "  \"Question\": " + "\"" + requestedJoke.getQuestion() + "\"" + ",\n" +
                "  \"Answer\" : " + "\"" + requestedJoke.getAnswer() + "\"" + "\n" +
                "}";
    }
}
