package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.fiNal;

public class finalDAO {
	public static ArrayList<fiNal> dbArrayList = new ArrayList<>();
	public static ArrayList<Integer> dbArrayFinalList = new ArrayList<>();
	
	public static ArrayList<fiNal> getFinalListData(){
		String selectFinal = "select * from tourlist inner join mytourlist on tourlist.tourID = mytourlist.tourID ";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selectFinal);
			rs = ps.executeQuery();
			if(rs == null) {
				finalController.callAlert("select ����:select ������ ����");
				return null;
			}
			dbArrayList.clear();
			while(rs.next()) {
				fiNal final1 = new fiNal(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				dbArrayList.add(final1);
			}			
		} catch(SQLException e) {
			finalController.callAlert("���Խ���:�����ͺ��̽� ���Խ���");
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch (SQLException e) {
				finalController.callAlert("�ڿ��ݱ����:����");
			}
		}
		
		return dbArrayList;
	}
	
	public static ArrayList<Integer> getFinalListSumData(){
		String selectFinal = "select sum(tourCharge) from tourlist inner join mytourlist on tourlist.tourID = mytourlist.tourID ";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selectFinal);
			rs = ps.executeQuery();
			if(rs == null) {
				finalController.callAlert("select ����:select ������ ����");
				return null;
			}
			dbArrayFinalList.clear();
			while(rs.next()) {
				dbArrayFinalList.add(rs.getInt(1));
			}			
		} catch(SQLException e) {
			finalController.callAlert("���Խ���:�����ͺ��̽� ���Խ���");
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(connection != null) {
					connection.close();
				}
			}catch (SQLException e) {
				finalController.callAlert("�ڿ��ݱ����:����");
			}
		}
		
		return dbArrayFinalList;
	}
}
