package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtility {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost/projectdb", "root", "123456");
		} catch (Exception e) {
			tokyoController.callAlert("DataBase connect failure : 데이터베이스 연결에 문제가 발생하였습니다.\n점검바랍니다.");
			return null;
		}
		return con;		
	}
}
