package SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL_connection {
	private static String url = "jdbc:sqlserver://DESKTOP-38DTV58:1433;databaseName=employee;encrypt=true;trustServerCertificate=true";
	private static String user = "sa";
	private static String password = "admin";
	
	public static Connection connection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
}
