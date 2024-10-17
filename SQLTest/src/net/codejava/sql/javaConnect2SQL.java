package net.codejava.sql;

import java.sql.*;

public class javaConnect2SQL {
	public static void main(String[] args) {
		String url = "jdbc:sqlserver://DESKTOP-38DTV58:1433;databaseName=students;encrypt=true;trustServerCertificate=true";
		String user, password;
		user = "sa";
		password = "thaitinh";
		
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			System.out.println("Successfully connected.");
			
			Statement stt = connection.createStatement();
			
			ResultSet rs = stt.executeQuery("select * from students_info");
			
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getInt(3));
			}
			
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Oops, there's an error!");
			e.printStackTrace();
		}
	}
}
