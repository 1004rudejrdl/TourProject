package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.fukuoka;


public class fukuokaDAO {
	public static ArrayList<fukuoka> dbArrayList = new ArrayList<>();
	public static ArrayList<fukuoka> getfukuOkaData(){
		String selectfukuOka="select tourID, tourName, tourClass, tourCharge from tourlist where tourID like '8192fukuoka%' ";
		Connection connection=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selectfukuOka);
			rs=ps.executeQuery();
			if(rs==null) {
				fukuokaController.callAlert("select 실패:select 쿼리문 실패");
				return null;
			}
			while(rs.next()) {
				fukuoka fukuOka = new fukuoka(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				dbArrayList.add(fukuOka);
			}
		} catch (SQLException e) {
			fukuokaController.callAlert("insert error : 데이터베이스 삽입 실패");
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch (SQLException e) {
				fukuokaController.callAlert("자원닫기실패 : 실패");
			}
		}
		return dbArrayList;
	}
}
