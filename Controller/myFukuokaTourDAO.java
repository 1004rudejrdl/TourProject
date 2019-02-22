package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.myFukuokaTour;




public class myFukuokaTourDAO {
	public static ArrayList<myFukuokaTour> dbArrayMyList = new ArrayList<>();
	public static int insertMyTourList(myFukuokaTour myList){
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
			ps.setString(1, myList.getMyFukuokaTourID());
			ps.setString(2, myList.getMyFukuokaTourName());
			count = ps.executeUpdate();
			if(count == 0) {
				fukuokaController.callAlert("insert query Error : ���������Խ���");
				return count;
			}
		} catch (SQLException e) {
			fukuokaController.callAlert("insert Error : �����ͺ��̽� ���Խ���");
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
	        	 fukuokaController.callAlert("�ڿ��ݱ����:����");
	         }
	      }
		return count;
	}

	public static int deleteMyTourList(String myFukuokaTourID) {
		String deleteMyTour = "delete from myTourList where tourID = ? ";
		Connection connection = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(deleteMyTour);
			ps.setString(1, myFukuokaTourID);
			count = ps.executeUpdate();
			if(count == 0) {
				fukuokaController.callAlert("delete query Error : ���� ������ ����");
				return count;
			}
		} catch (SQLException e) {
			fukuokaController.callAlert("delete Error : �����ͺ��̽� ���� ����");
		} finally {
			try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	        	 fukuokaController.callAlert("�ڿ��ݱ����:����");
	         }
		}
		
		return count;
	}
	
	
	public static ArrayList<myFukuokaTour> getFukuokaData(){
		String selectFukuoka = "select * from mytourlist ";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selectFukuoka);
			rs = ps.executeQuery();
			if(rs == null) {
				fukuokaController.callAlert("select ����:select ������ ����");
				return null;
			}
			dbArrayMyList.clear();
			while (rs.next()) {
				myFukuokaTour myfukuokatour = new myFukuokaTour(rs.getString(1), rs.getString(2));
				dbArrayMyList.add(myfukuokatour);
			}
		} catch(SQLException e) {
			fukuokaController.callAlert("���Խ���:�����ͺ��̽� ���Խ���");
		} finally {
	         try {
	             if (ps != null) {
	                ps.close();
	             }
	             if (connection != null) {
	                connection.close();
	             }
	          } catch (SQLException e) {
	        	  fukuokaController.callAlert("�ڿ��ݱ����:����");
	          }
		}		
		return dbArrayMyList;
	}
}
