package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}

	public ModelLogin gravarUsuario(ModelLogin objeto) throws Exception {
		
		if(objeto.isNovo()) {/*Grava um novo*/

		String sql = "INSERT INTO public.model_login(login, senha, nome, email) VALUES (?, ?, ?, ?);";
		PreparedStatement preparedSql = connection.prepareStatement(sql);

		preparedSql.setString(1, objeto.getLogin());
		preparedSql.setString(2, objeto.getSenha());
		preparedSql.setString(3, objeto.getNome());
		preparedSql.setString(4, objeto.getEmail());

		preparedSql.execute();
		connection.commit();
		
		}else {
			String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? WHERE id = "+objeto.getId()+";";
			PreparedStatement preparedSql = connection.prepareStatement(sql);

			preparedSql.setString(1, objeto.getLogin());
			preparedSql.setString(2, objeto.getSenha());
			preparedSql.setString(3, objeto.getNome());
			preparedSql.setString(4, objeto.getEmail());
			
			preparedSql.executeUpdate();
			connection.commit();
			
		}
		return this.consultaUsuario(objeto.getLogin());// consulta usuario pelo login;
	}

	public ModelLogin consultaUsuario(String login) throws Exception {

		ModelLogin modelLogin = new ModelLogin();

		String sql = "select * from model_login where upper (login) = upper ('" + login + "')";

		PreparedStatement statement = connection.prepareStatement(sql); // preparando SQL

		ResultSet resultado = statement.executeQuery();// executa a SQL

		while (resultado.next()) { /* Se tem resultado */

			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
		}

		return modelLogin;
	}

	public boolean validarLogin(String login) throws Exception {

		String sql = "select count (1) > 0 as existe from model_login where upper (login) = upper ('" + login + "')";

		PreparedStatement statement = connection.prepareStatement(sql); // preparando SQL

		ResultSet resultado = statement.executeQuery();// executa a SQL
		
		resultado.next(); /*Pra ele entrar nos resultados do SQL*/
		return resultado.getBoolean("existe");
	}
	
	public void deletarUser(String idUser) throws Exception{
		String sql = "delete from model_login where id = ? ;";
		
		PreparedStatement prepareSql = connection.prepareStatement(sql);
		
		prepareSql.setLong(1, Long.parseLong(idUser));
		
		prepareSql.executeUpdate();
		
		connection.commit();
		
	}

}