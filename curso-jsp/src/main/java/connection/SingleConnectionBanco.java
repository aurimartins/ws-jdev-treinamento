package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgres://localhost:5433/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() { /* Quano tiver uma instancia vai conectar*/
		conectar();
	}
	
	private static void conectar() {
		
		try {
			if(connection == null) {
				Class.forName("org.postgres.Driver"); /*Carregar o driver de conex�o do banco*/
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); /*Para n�o efetuar altera��es no banco sem nosso comando*/
			}
		}catch(Exception e) {
			e.printStackTrace(); /* Mostrar qualquer erro no momento de conectar*/
		}
	}
}
