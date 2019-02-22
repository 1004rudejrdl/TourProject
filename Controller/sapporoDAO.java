package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.sapporo;


public class sapporoDAO {
	public static ArrayList<sapporo> dbArrayList = new ArrayList<>();
	public static ArrayList<sapporo> getsaPPoroData(){
		String selectsapporo="select tourID, tourName, tourClass, tourCharge from tourlist where tourID like '8111sapporo%' ";
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selectsapporo);
			rs=ps.executeQuery();
			if(rs==null) {
				sapporoController.callAlert("select ����:select ������ ����");
				return null;
			}
			while(rs.next()) {
				sapporo saPPoro = new sapporo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				dbArrayList.add(saPPoro);
			}
		} catch (SQLException e) {
			sapporoController.callAlert("insert error : �����ͺ��̽� ���� ����");
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch (SQLException e) {
				sapporoController.callAlert("�ڿ��ݱ���� : ����");
			}
		}
		return dbArrayList;
	}
}
