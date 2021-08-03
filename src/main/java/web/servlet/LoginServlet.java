package web.servlet;

import java.io.IOException;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import local.user.*;

public class LoginServlet extends HttpServlet {

    User[] devTeam;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (devTeam == null) {

            devTeam = getDevTeam();

        }

        // Recuperar parametros de la peticion
        String colaborador = request.getParameter("colaborador");
        String password = request.getParameter("password");

        // Create a LoginRequester object
        LoginRequester loginRequester = new LoginRequester(colaborador, password);

        User newUser = getUser(loginRequester);

        if (newUser != null) {

            HttpSession session = request.getSession();
            session.setAttribute("user", newUser);

            response.sendRedirect(request.getContextPath() + "/secret/home");
            return;
        }

        request.setAttribute("wrongData", "true");
        RequestDispatcher rq = request.getRequestDispatcher("/login");
        rq.forward(request, response);

    }

    private User[] getDevTeam() {

        User userCarlos = new User("Carlos", "carlosTCS", 40);
        User userGabriel = new User("Gabriel", "gabrielTCS", 40);
        User userJair = new User("Jair", "jairTCS", 40);
        User userOscar = new User("Oscar", "oscarTCS", 40);
        User userAlberto = new User("Alberto", "albertoTCS", 40);
        User userLando = new User("Lando", "landoTCS", 40);

        User[] devs = {userAlberto, userCarlos, userLando, userGabriel, userJair, userOscar};

        return devs;

    }

    private User getUser(LoginRequester loginRequester) {

        String newColab = loginRequester.getName();

        // Comprobar que existe el usuario solicitado
        for (User d : devTeam) {
            if (newColab.equals(d.getName()) &&
                    d.getPassword().equals(loginRequester.getPassword())) {
                return d;
            }
        }
        return null;
    }
}