package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.osaka;


public class osakaDAO {
	public static ArrayList<osaka> dbArrayList = new ArrayList<>();
	public static ArrayList<osaka> gettoKyoData(){
		String selectosaKa="select tourID, tourName, tourClass, tourCharge from tourlist where tourID like '8106osaka%' ";
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selectosaKa);
			rs=ps.executeQuery();
			if(rs==null) {
				osakaController.callAlert("select ����:select ������ ����");
				return null;
			}
			while(rs.next()) {
				osaka osAka = new osaka(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				dbArrayList.add(osAka);
			}
		} catch (SQLException e) {
			osakaController.callAlert("insert error : �����ͺ��̽� ���� ����");
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch (SQLException e) {
				osakaController.callAlert("�ڿ��ݱ���� : ����");
			}
		}
		return dbArrayList;
	}
}
