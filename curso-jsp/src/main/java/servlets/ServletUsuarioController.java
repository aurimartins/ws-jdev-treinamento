package servlets;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ModelLogin;

@WebServlet("/ServletUsuarioController.java")
public class ServletUsuarioController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
       
    public ServletUsuarioController() {
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Usado para CONSULTAR e DELETAR */
		
		try {
			String acao = request.getParameter("acao");
			
				if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {//Aqui deleta o usuario;
					
					String idUser = request.getParameter("id");
					
					daoUsuarioRepository.deletarUser(idUser);
					
					request.setAttribute("msg", "Excluído com sucesso!");
					request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);// Aqui redireciona pra página de usuario;
					
				}
				else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {//Aqui deleta o usuario com AJAX;
					
					String idUser = request.getParameter("id");
					
					daoUsuarioRepository.deletarUser(idUser);
	
					//Forma de escrever a resposta no caso do uso do Delete com AJAX
					response.getWriter().write("Excluído com sucesso!"); 
					
				} 
				else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {// Aqui busca o usuario com AJAX;
					 
					 String nomeBusca = request.getParameter("nomeBusca");
					 
					 List<ModelLogin> dadosJsonUser =  daoUsuarioRepository.consultaUsuarioList(nomeBusca);
					 
					 ObjectMapper mapper = new ObjectMapper();
					 
					 String json = mapper.writeValueAsString(dadosJsonUser);
					 
					 response.getWriter().write(json);
					 
				 }
				else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {// Aqui busca o usuario com AJAX;
					String id = request.getParameter("id");
					
					ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(id);
					
					request.setAttribute("msg",	"Usuário em edição! ");
					request.setAttribute("modelLogin", modelLogin);
					request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);//Redirecionamento para a página de Cadastro de Usuários
					
					
				}
				else{					
					request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);// Aqui redireciona pra página de usuario;
				}

		}catch(Exception e) {
				e.printStackTrace();
				RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
				request.setAttribute("msg", e.getMessage());
				redirecionar.forward(request, response);
			}
	
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Usado para GRAVAR/CRIAR e ATUALIZAR/ALTERAR */
		
		try {
			
			String msg = "Operação realizada com sucesso! ";
			String id= request.getParameter("id");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			
			
			if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Login existente! Informe um novo Login. ";
			}else {
				if(modelLogin.isNovo()) {
					msg = "Gravado com sucesso!";
				}else {
					msg = "Atualizado com sucesso!";
				}
				modelLogin =  daoUsuarioRepository.gravarUsuario(modelLogin);
			}
			
			
			request.setAttribute("msg",	msg);
			request.setAttribute("modelLogin", modelLogin);
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);//Redirecionamento para a página de Cadastro de Usuários
			
		}catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
		}

	}

}
