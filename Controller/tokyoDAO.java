package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.toKyo;


public class tokyoDAO {
	public static ArrayList<toKyo> dbArrayList = new ArrayList<>();
	public static ArrayList<toKyo> gettoKyoData(){
		String selecttoKyo="select tourID, tourName, tourClass, tourCharge from tourlist where tourID like '8103tokyo%' ";
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selecttoKyo);
			rs=ps.executeQuery();
			if(rs==null) {
				tokyoController.callAlert("select ����:select ������ ����");
				return null;
			}
			while(rs.next()) {
				toKyo tokyo = new toKyo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				dbArrayList.add(tokyo);
			}
		} catch (SQLException e) {
			tokyoController.callAlert("insert error : �����ͺ��̽� ���� ����");
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch (SQLException e) {
				tokyoController.callAlert("�ڿ��ݱ���� : ����");
			}
		}
		return dbArrayList;
	}
}
