package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/ServletLogin.java") /*Mapeamento de URL quem vem da tela*/ /*NÃO ESQUECER A EXTENSÃO DA CLASS .java*/
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public ServletLogin() {
    }

    /* Recebe os dados pela URL em parametros*/
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	/* Recebe os dados enviados por um formulario*/
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("idade"));
		
	}

}
