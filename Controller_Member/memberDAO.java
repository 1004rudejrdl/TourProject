package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.memberShip;


public class memberDAO {
	public static ArrayList<memberShip> dbArrayListMember = new ArrayList<>();
	public static int insertMemberData(memberShip member) {
		StringBuffer insertMemberShip = new StringBuffer();
		insertMemberShip.append("insert into memberlist ");
		insertMemberShip.append("(memberID, memberPassWord, memberName, memberGender, memberPhone, memberEmail) ");
		insertMemberShip.append("values ");
		insertMemberShip.append("(?,?,?,?,?,?) ");
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(insertMemberShip.toString());
			ps.setString(1, member.getMemberID());
			ps.setString(2, member.getMemberPassWord());
			ps.setString(3, member.getMemberName());
			ps.setString(4, member.getMemberGender());
			ps.setString(5, member.getMemberPhone());
			ps.setString(6, member.getMemberEmail());
			
			count = ps.executeUpdate();
			if(count == 0) {
				memberController.callAlert("insert query Error : ���������Խ���");
				return count;
			}
		} catch (SQLException e) {
			memberController.callAlert("insert Error : �����ͺ��̽� ���Խ���");
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(connection !=null) {
					connection.close();
				}
			}catch (SQLException e) {
				memberController.callAlert("�ڿ��ݱ� ���� : ����");
			}
		}
		return count;
	}
	
	
	public static void checkMemberID(memberShip member) {
		Connection con;
		try {
			con = DBUtility.getConnection();
			PreparedStatement ps = null;
			String checkID= "select memberID from memberlist where memberID=? ";
			ps=con.prepareStatement(checkID);
			ps.setString(1, member.getMemberID());
			ResultSet rs = ps.executeQuery();
			if(member.getMemberID().equals("")) {
				memberController.callAlert("�ߺ�üũ�� ���̵� �Է� : ���̵� �Է��ϼ���.");						
			}else if(rs.next()) {
				memberController.callAlert("���̵� �ߺ� : ���̵� �ߺ��Ǿ����ϴ�\n�ٸ� ���̵� �Է��ϼ���.");
			}else {
				memberController.callAlert("���̵� ���� ���� : ��밡���� ���̵��Դϴ�.");
			}
			
		}catch(Exception e) {}
	}
	
	public static ArrayList<memberShip> getMemberTotalData(){
		String selectMemberAll = "select * from memberlist ";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtility.getConnection();
			ps = connection.prepareStatement(selectMemberAll);
			rs = ps.executeQuery();
			if(rs==null) {
				superController.callAlert("select Error : select ������ ����");
				return null;
			}
			dbArrayListMember.clear();
			while(rs.next()) {
				memberShip member = new memberShip(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				dbArrayListMember.add(member);
			}
		}catch (SQLException e) {
	         superController.callAlert("���Խ���:�����ͺ��̽� ���Խ���");
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
	            superController.callAlert("�ڿ��ݱ����:����");
	         }
	      }
		
		return dbArrayListMember;
	}
	
	public static int updateMemberData(memberShip member, memberShip selectedSuper) {
		StringBuffer updateMember = new StringBuffer();
		updateMember.append("update memberlist set ");
		updateMember.append("memberPassWord=?, memberName=?, memberGender=?, memberPhone=?, memberEmail=? where memberID=? ");
		Connection connection = null;
		PreparedStatement ps = null;
		int count=0;
		try {
			connection = DBUtility.getConnection();
			ps=connection.prepareStatement(updateMember.toString());
			ps.setString(1, member.getMemberPassWord());
			ps.setString(2, member.getMemberName());
			ps.setString(3, member.getMemberGender());
			ps.setString(4, member.getMemberPhone());
			ps.setString(5, member.getMemberEmail());
			ps.setString(6, member.getMemberID());
			
			count = ps.executeUpdate();
		 if (count == 0) {
	            superController.callAlert("update ��������:update������ ����");
	            return count;
	         }
		}catch(Exception e) {
			superController.callAlert("Update Error :  �����ͺ��̽� ������Ʈ����");
			System.out.println(e.getMessage());
		}finally {
	         // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            superController.callAlert("�ڿ��ݱ����:����");

	         }
	      }
		
		return count;
	}
	
	public static int deleteMemberData(String memberID) {
		String deleteMember = "delete from memberlist where memberID=? ";
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = DBUtility.getConnection();
			ps = con.prepareStatement(deleteMember);
			ps.setString(1, memberID);
			count = ps.executeUpdate();
			 if (count == 0) {
	           superController.callAlert("delete ����:delete ������ ����");
	           return count;
	         }			
		}catch(SQLException e) {
			superController.callAlert("delete Error : �����ͺ��̽� ��������");
		}finally {
	         // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (con != null) {
	               con.close();
	            }
	         } catch (SQLException e) {
	            superController.callAlert("�ڿ��ݱ����:����");

	         }
	      }
		
		return count;
	}
}



















