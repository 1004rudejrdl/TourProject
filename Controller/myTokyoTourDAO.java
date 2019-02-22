package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.myTokyoTour;



public class myTokyoTourDAO {
	public static ArrayList<myTokyoTour> dbArrayMyList = new ArrayList<>();
	public static int insertMyTourList(myTokyoTour myList){
		StringBuffer insertMyTour = new StringBuffer();
		insertMyTour.append("insert into myTourList ");
		insertMyTour.append("(tourID, tourName) ");
		insertMyTour.append("values ");
		insertMyTour.append("(?,?) ");
		
		Connection connection = null;
		PreparedStatement ps = null;
		int count = 0;		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(insertMyTour.toString());
			ps.setString(1, myList.getMyTokyoTourID());
			ps.setString(2, myList.getMyTokyoTourName());
			count = ps.executeUpdate();
			if(count == 0) {
				tokyoController.callAlert("insert query Error : ���������Խ���");
				return count;
			}
		} catch (SQLException e) {
			//tokyoController.callAlert("insert Error : �����ͺ��̽� ���Խ���");
			tokyoController.callAlert("����Ʈ �ߺ� : �����Ͻ� ��������Ʈ�� �ߺ��Ǿ����ϴ�.");
		} finally {
	         // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            tokyoController.callAlert("�ڿ��ݱ����:����");
	         }
	      }
		return count;
	}

	public static int deleteMyTourList(String myTokyoTourID) {
		String deleteMyTour = "delete from myTourList where tourID = ? ";
		Connection connection = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(deleteMyTour);
			ps.setString(1, myTokyoTourID);
			count = ps.executeUpdate();
			if(count == 0) {
				tokyoController.callAlert("delete query Error : ���� ������ ����");
				return count;
			}
		} catch (SQLException e) {
			tokyoController.callAlert("delete Error : �����ͺ��̽� ���� ����");
		} finally {
			try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            tokyoController.callAlert("�ڿ��ݱ����:����");
	         }
		}
		
		return count;
	}
	
	public static ArrayList<myTokyoTour> getTokyoData(){
		String selectTokyo = "select * from mytourlist ";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selectTokyo);
			rs = ps.executeQuery();
			if(rs == null) {
				tokyoController.callAlert("select ����:select ������ ����");
				return null;
			}
			dbArrayMyList.clear();
			while (rs.next()) {
				myTokyoTour mytokyotour = new myTokyoTour(rs.getString(1), rs.getString(2));
				dbArrayMyList.add(mytokyotour);
			}
		} catch(SQLException e) {
			tokyoController.callAlert("���Խ���:�����ͺ��̽� ���Խ���");
		} finally {
	         try {
	             if (ps != null) {
	                ps.close();
	             }
	             if (connection != null) {
	                connection.close();
	             }
	          } catch (SQLException e) {
	        	  tokyoController.callAlert("�ڿ��ݱ����:����");
	          }
		}		
		return dbArrayMyList;
	}
}
