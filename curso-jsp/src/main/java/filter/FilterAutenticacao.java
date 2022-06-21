package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter("/FilterAutenticacao") 
/* Intercepta todas as requisicoes que vierem do projeto ou mapeamento 
 * Obs: o Index n�o pode passar por esse Filter, pois � uma tela de login do sistema. Por isso, 
 * vamos come�ar o filtro da Principal. 
 * */
public class FilterAutenticacao extends HttpFilter {

	public FilterAutenticacao() {
	}
	
	/*Encerra os processos quando o servidor � parado;*/
	public void destroy() {
	}

	/*Intercepta as requisi��es e as respostas no sistema;
	 * Tudo o que fizer no sistema vai fazer por aqui;
	 * Valida��o de autentica��o;  
	 * Validar e fazer redirecionamento de p�ginas;
	 * Dar commit e rolback de transa��es do banco;
	 * */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath(); //Url que est� sendo acessada.
		
		/*Valida se est� logado sen�o redireciona para a tela de login*/
		if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("principal/ServletLogin")){
			
					RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
					request.setAttribute("msg","Por favor realize o login!");
					redireciona.forward(request, response);
					return; /*Para a execu��o e redireciona para o login*/
		}else {
			chain.doFilter(request, response);
		}
	}

	/*Inicia a conex�o com o banco;*/
	public void init(FilterConfig fConfig) throws ServletException {

	}

}
