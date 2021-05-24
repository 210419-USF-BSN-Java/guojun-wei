package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static Connection connection;
	
	public static Connection getConnectionFromEnv() throws Exception
	{

		String url = System.getenv("AWS_DB_URL");
		String username = System.getenv("AWS_DB_USER");
		String password = System.getenv("AWS_DB_PASS");
		Class.forName("org.postgresql.Driver");
		if(connection == null || connection.isClosed()) {
		connection = DriverManager.getConnection(url, username, password);
		}
		
		return connection;
	}
}
