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
			tokyoController.callAlert("DataBase connect failure : �����ͺ��̽� ���ῡ ������ �߻��Ͽ����ϴ�.\n���˹ٶ��ϴ�.");
			return null;
		}
		return con;		
	}
}
