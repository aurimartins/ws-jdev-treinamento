package servlets;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig //anotação usada para fazer upload de fotos

@WebServlet(urlPatterns = {"/ServletUsuarioController.java"})
public class ServletUsuarioController extends ServletGenericUtil {
	
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
					
					List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
					request.setAttribute("modelLogins", modelLogins);
					
					request.setAttribute("msg", "Excluído com sucesso!");
					request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);// Aqui redireciona pra página de usuario;
					
				}
				else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {//Aqui deleta o usuario com AJAX;
					
					String idUser = request.getParameter("id");
					
					daoUsuarioRepository.deletarUser(idUser);
	
					response.getWriter().write("Excluído com sucesso!"); //Forma de escrever a resposta no caso do uso do Delete com AJAX 
					
				} 
				else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {// Aqui busca o usuario com AJAX;
					 
					 String nomeBusca = request.getParameter("nomeBusca");
					 
					 List<ModelLogin> dadosJsonUser =  daoUsuarioRepository.consultaUsuarioList(nomeBusca,super.getUserLogado(request));
					 
					 ObjectMapper mapper = new ObjectMapper();
					 
					 String json = mapper.writeValueAsString(dadosJsonUser);
					 
					 response.getWriter().write(json);
					 
				 }
				else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {// Aqui busca o usuario com AJAX;
					
					String idUser = request.getParameter("id");
					
					ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(idUser,super.getUserLogado(request));
					
					List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
					request.setAttribute("modelLogins", modelLogins);
					
					request.setAttribute("msg",	"Usuário em edição! ");
					request.setAttribute("modelLogin", modelLogin);
					request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);//Redirecionamento para a página de Cadastro de Usuários
					
					
				}
				else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {// Aqui busca o usuario com AJAX;
					
					List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
					
					request.setAttribute("msg",	"Usuário carregado! ");
					request.setAttribute("modelLogins", modelLogins);
					request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);//Redirecionamento para a página de Cadastro de Usuários
					
					
				}
				else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {// Aqui busca o usuario com AJAX;
					
					String idUser = request.getParameter("id");
					ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(idUser, super.getUserLogado(request));

						if(modelLogin.getFotouser() != null && !modelLogin.getFotouser().isEmpty()) {
							
							response.setHeader("Content-Disposition", "attachment;filename=arquivo." + modelLogin.getExtensaofotouser());
							response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotouser().split("\\,")[1]));
						}
				}
				else{		
					List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
					request.setAttribute("modelLogins", modelLogins);
					
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
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");
			String cep = request.getParameter("cep");
			String logradouro = request.getParameter("logradouro");
			String bairro = request.getParameter("bairro");
			String localidade = request.getParameter("localidade");
			String uf = request.getParameter("uf");
			String numero = request.getParameter("numero");
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);
			modelLogin.setCep(cep);
			modelLogin.setLogradouro(logradouro);
			modelLogin.setBairro(bairro);
			modelLogin.setLocalidade(localidade);
			modelLogin.setUf(uf);
			modelLogin.setNumero(numero);
			
			if(ServletFileUpload.isMultipartContent(request)) {
				
				/*Converte imagem em byte para salvar no banco de dados*/
				Part part = request.getPart("filefoto"); //Pega foto da tela
				
				if(part.getSize() > 0) {
					byte[] foto = IOUtils.toByteArray(part.getInputStream()); //convert imagem para byte
					String imagemBase64 = "data:image/" + part.getContentType().split("\\/")[1] + ";base64," + new Base64().encodeBase64String(foto);
					
					modelLogin.setFotouser(imagemBase64); //setando extensão
					modelLogin.setExtensaofotouser(part.getContentType().split("\\/")[1]); //setando imagem
				}
			}
			
			if(daoUsuarioRepository.validarLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				msg = "Login existente! Informe um novo Login. ";
			}else {
				if(modelLogin.isNovo()) {
					msg = "Gravado com sucesso!";
				}else {
					msg = "Atualizado com sucesso!";
				}
				modelLogin =  daoUsuarioRepository.gravarUsuario(modelLogin,super.getUserLogado(request));
			}
			
			List<ModelLogin> modelLogins = daoUsuarioRepository.consultaUsuarioList(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
			
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
