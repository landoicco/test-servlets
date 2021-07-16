package web;

import java.io.*;
import jakarta.servlet.*;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // Recuperar paametros de la peticipon
		String colaborador = request.getParameter("colaborador");
		String password = request.getParameter("password");
		
		PrintWriter out = response.getWriter();
		
		if(hasAccess(colaborador, password))
		{
			request.setAttribute("wrongData", "false");
			out.println("Welcome");
		}
		else{
			request.setAttribute("wrongData", "true");
			RequestDispatcher rq = request.getRequestDispatcher("IndexJSP");
			rq.forward(request, response);
			out.println("bye");
		}
		
    }
	
	private boolean hasAccess(String colaborador, String password)
	{
		// Pasar parametros a mayusculas
		String newColab = colaborador.toUpperCase();
		
		// Comprobar acceso
		String[] colaboradores = {"CARLOS", "GABRIEL", "JAIR", "LANDO", "ALBERTO", "OSCAR"};
		for(String c : colaboradores)
		{
			if(newColab.equals(c) && "TCS".equals(password))
			{
				return true;
			}
		}
		return false;
	}
}