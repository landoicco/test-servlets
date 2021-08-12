package web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import local.jokes.Joke;

import java.io.IOException;

public class JokesServlet extends HttpServlet {

    String jokeResponse;
    Joke[] jokeBucket;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (jokeBucket == null) {
            jokeBucket = createJokes();
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        getJoke(req.getParameter("joke"));

        //resp.getWriter().append(jokeResponse);
        resp.getWriter().write(jokeResponse);

    }

    private Joke[] createJokes() {

        Joke jokeone = new Joke(1, "Whats the object-oriented way to become wealthy?",
                "Inheritance");
        Joke joketwo = new Joke(2, "What did the Java code say to the C code?",
                "You've got no class");
        Joke jokethree = new Joke(3, "What do cats and programmers have in common?",
                "When either one is unusually happy and excited, an appropriate question would be, " +
                        "did you find a bug?");
        Joke jokefour = new Joke(4, "What is the most used language in programming?",
                "Profanity");

        return new Joke[]{jokeone, joketwo, jokethree, jokefour};

    }

    private void getJoke(String joke) {
        switch (joke) {
            case "jokeone":
                jokeResponse = "{\n" +
                        "  \"Question\": " + "\"" + jokeBucket[0].getQuestion() + "\"" + ",\n" +
                        "  \"Answer\" : " + "\"" + jokeBucket[0].getAnswer() + "\"" + "\n" +
                        "}";
                break;
            case "joketwo":
                jokeResponse = "{\n" +
                        "  \"Question\": " + "\"" + jokeBucket[1].getQuestion() + "\"" + ",\n" +
                        "  \"Answer\" : " + "\"" + jokeBucket[1].getAnswer() + "\"" + "\n" +
                        "}";
                break;
            case "jokethree":
                jokeResponse = "{\n" +
                        "  \"Question\": " + "\"" + jokeBucket[2].getQuestion() + "\"" + ",\n" +
                        "  \"Answer\" : " + "\"" + jokeBucket[2].getAnswer() + "\"" + "\n" +
                        "}";
                break;
            case "jokefour":
                jokeResponse = "{\n" +
                        "  \"Question\": " + "\"" + jokeBucket[3].getQuestion() + "\"" + ",\n" +
                        "  \"Answer\" : " + "\"" + jokeBucket[3].getAnswer() + "\"" + "\n" +
                        "}";
                break;
        }
    }
}
