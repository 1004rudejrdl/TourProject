package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.mySapporoTour;




public class mySapporoTourDAO {
	public static ArrayList<mySapporoTour> dbArrayMyList = new ArrayList<>();
	public static int insertMyTourList(mySapporoTour myList){
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
			ps.setString(1, myList.getMySapporoTourID());
			ps.setString(2, myList.getMySapporoTourName());
			count = ps.executeUpdate();
			if(count == 0) {
				osakaController.callAlert("insert query Error : ���������Խ���");
				return count;
			}
		} catch (SQLException e) {
			osakaController.callAlert("insert Error : �����ͺ��̽� ���Խ���");
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
	        	 osakaController.callAlert("�ڿ��ݱ����:����");
	         }
	      }
		return count;
	}

	public static int deleteMyTourList(String mySapporoTourID) {
		String deleteMyTour = "delete from myTourList where tourID = ? ";
		Connection connection = null;
		PreparedStatement ps = null;
		int count = 0;
		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(deleteMyTour);
			ps.setString(1, mySapporoTourID);
			count = ps.executeUpdate();
			if(count == 0) {
				osakaController.callAlert("delete query Error : ���� ������ ����");
				return count;
			}
		} catch (SQLException e) {
			osakaController.callAlert("delete Error : �����ͺ��̽� ���� ����");
		} finally {
			try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	        	 osakaController.callAlert("�ڿ��ݱ����:����");
	         }
		}
		
		return count;
	}
	
	
	public static ArrayList<mySapporoTour> getSapporoData(){
		String selectSapporo = "select * from mytourlist ";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selectSapporo);
			rs = ps.executeQuery();
			if(rs == null) {
				osakaController.callAlert("select ����:select ������ ����");
				return null;
			}
			dbArrayMyList.clear();
			while (rs.next()) {
				mySapporoTour mysapporotour = new mySapporoTour(rs.getString(1), rs.getString(2));
				dbArrayMyList.add(mysapporotour);
			}
		} catch(SQLException e) {
			osakaController.callAlert("���Խ���:�����ͺ��̽� ���Խ���");
		} finally {
	         try {
	             if (ps != null) {
	                ps.close();
	             }
	             if (connection != null) {
	                connection.close();
	             }
	          } catch (SQLException e) {
	             osakaController.callAlert("�ڿ��ݱ����:����");
	          }
		}		
		return dbArrayMyList;
	}
}
