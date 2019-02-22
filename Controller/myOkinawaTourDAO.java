package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.myOkinawaTour;




public class myOkinawaTourDAO {
	public static ArrayList<myOkinawaTour> dbArrayMyList = new ArrayList<>();
	public static int insertMyTourList(myOkinawaTour myList){
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
			ps.setString(1, myList.getMyOkinawaTourID());
			ps.setString(2, myList.getMyOkinawaTourName());
			count = ps.executeUpdate();
			if(count == 0) {
				okinawaController.callAlert("insert query Error : ���������Խ���");
				return count;
			}
		} catch (SQLException e) {
			okinawaController.callAlert("insert Error : �����ͺ��̽� ���Խ���");
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
	        	 okinawaController.callAlert("�ڿ��ݱ����:����");
	         }
	      }
		return count;
	}

	public static int deleteMyTourList(String myOkinawaTourID) {
		String deleteMyTour = "delete from myTourList where tourID = ? ";
		Connection connection = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(deleteMyTour);
			ps.setString(1, myOkinawaTourID);
			count = ps.executeUpdate();
			if(count == 0) {
				okinawaController.callAlert("delete query Error : ���� ������ ����");
				return count;
			}
		} catch (SQLException e) {
			okinawaController.callAlert("delete Error : �����ͺ��̽� ���� ����");
		} finally {
			try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	        	 okinawaController.callAlert("�ڿ��ݱ����:����");
	         }
		}
		
		return count;
	}
	
	
	public static ArrayList<myOkinawaTour> getOkinawaData(){
		String selectOkinawa = "select * from mytourlist ";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selectOkinawa);
			rs = ps.executeQuery();
			if(rs == null) {
				okinawaController.callAlert("select ����:select ������ ����");
				return null;
			}
			dbArrayMyList.clear();
			while (rs.next()) {
				myOkinawaTour myokinawatour = new myOkinawaTour(rs.getString(1), rs.getString(2));
				dbArrayMyList.add(myokinawatour);
			}
		} catch(SQLException e) {
			okinawaController.callAlert("���Խ���:�����ͺ��̽� ���Խ���");
		} finally {
	         try {
	             if (ps != null) {
	                ps.close();
	             }
	             if (connection != null) {
	                connection.close();
	             }
	          } catch (SQLException e) {
	        	  okinawaController.callAlert("�ڿ��ݱ����:����");
	          }
		}		
		return dbArrayMyList;
	}
}
