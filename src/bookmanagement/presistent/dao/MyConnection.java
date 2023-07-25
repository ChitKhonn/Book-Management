package bookmanagement.presistent.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	static Connection con = null;
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","asd123!@#");
			System.out.println("Connecting....");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver class not found!!");
		} catch (SQLException e) {
			System.out.println("Database connection not found!!");
		}
		return con;
	}
}
