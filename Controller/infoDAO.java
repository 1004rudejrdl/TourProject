package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.inforTour;

public class infoDAO {
	public static ArrayList<inforTour> dbArrayList = new ArrayList<>();
	public static ArrayList<inforTour> getInfoData(String selectedID){
		String selecttoKyo="select tourID, tourName, tourAddr, tourWay, tourExplain, tourImage from inforTour where tourID like '" +selectedID+"'";
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
			dbArrayList.clear();
			while(rs.next()) {
				inforTour info = new inforTour(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				dbArrayList.add(info);
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
