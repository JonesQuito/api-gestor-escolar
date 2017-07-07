package apiRest.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection getConnection(){
		String port = System.getProperty("mysqlport");
		port = port == null ? "3306" : port;
		try{
			//return DriverManager.getConnection("jdbc:mysql://db4free.net:" + port + "/banco1_ufg", "jones_quito", "123456");
			return DriverManager.getConnection("jdbc:mysql://localhost:" + port + "/banco1_ufg", "root", "123456");
		}catch(SQLException e){
			throw new RuntimeException("Erro na conexão: " + e);
		}
	}

}
