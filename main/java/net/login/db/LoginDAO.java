package net.login.db;

import java.sql.*;

import javax.naming.*;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.util.*;

public class LoginDAO {//게시판 db를 처리하는 클래스
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DataSource ds;
	public LoginDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");
			
			//con = ds.getConnection();
			System.out.println("DB 연결 성공 !");
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	public boolean addMember(LoginBean joinData) {
		int result = 0;
		
		String sql = "insert into userInfo (user_id, user_name, user_pw, user_email, birth, hobby, info) values"
				+ "(?,?,?,?,?,?,?)";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,joinData.getUser_id());
			pstmt.setString(2, joinData.getUser_name());
			pstmt.setString(3, joinData.getUser_pw());
			pstmt.setString(4, joinData.getUser_email());
			pstmt.setString(5, joinData.getBirth());
			pstmt.setString(6, joinData.getHobby());
			pstmt.setString(7, joinData.getInfo());
			
			result = pstmt.executeUpdate();
			if(result ==0) return false;
			
			return true;
		} catch (SQLException e) {
			System.out.println("addMember 에러 : "+ e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}

	
		return false;
	}
	public boolean loginCheck(String user_id, String user_pw) {
		String sql = "Select user_id, isadmin from userInfo where user_id = ? AND user_pw = ?";
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pw);
			
			rs = pstmt.executeQuery();
			rs.next();
		
			if(rs.getString(1).equals(user_id)) {
				return true;
			}
			
			
		} catch(SQLException ex){
			System.out.println("loginCheck 에러 : "+ex);
		}finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}

		return false;
	}
	public LoginBean getUserInfo(String user_id) {
		String sql = "select * from userInfo where user_id = ?";
		LoginBean loginData = new LoginBean();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				loginData.setUser_id(rs.getString("user_id"));
				loginData.setUser_pw(rs.getString("user_pw"));
				loginData.setUser_email(rs.getString("user_email"));
				loginData.setUser_name(rs.getString("user_name"));
				loginData.setBirth(rs.getString("birth"));
				loginData.setHobby(rs.getString("hobby"));
				loginData.setInfo(rs.getString("info"));
				loginData.setIsadmin(rs.getBoolean("isadmin"));
			} else {
				System.out.println("회원 정보 불러오기 실패!");
				return null;
			}
			
			return loginData;
			
		} catch(SQLException ex){
			System.out.println("loginCheck 에러 : "+ex);
		}finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}
		return null;
	}
	public boolean adminCheck(String user_id) {
		String sql = "select isadmin from userInfo where user_id = ?";
		LoginBean loginData = new LoginBean();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				return rs.getBoolean(1);
			} else {
				System.out.println("관리자 여부 불러오기 실패!");
				return false;
			}
			
			
		} catch(SQLException ex){
			System.out.println("loginCheck 에러 : "+ex);
		}finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}
		return false;
	}
	
	public ArrayList<LoginBean> getMemberList() {
		String sql = "select * from userInfo where isadmin=0";
		ArrayList<LoginBean> list = new ArrayList<LoginBean>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
	
			while(rs.next()) {
				LoginBean loginData = new LoginBean();
				loginData.setUser_id(rs.getString("user_id"));
				loginData.setUser_pw(rs.getString("user_pw"));
				loginData.setUser_email(rs.getString("user_email"));
				loginData.setUser_name(rs.getString("user_name"));
				loginData.setBirth(rs.getString("birth"));
				loginData.setHobby(rs.getString("hobby"));
				loginData.setInfo(rs.getString("info"));
				loginData.setIsadmin(rs.getBoolean("isadmin"));
				list.add(loginData);
			}
			
			return list;
			
		} catch (SQLException ex) {
			System.out.println("loginCheck 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {

				}
		}

		return null;
	}
	public int memberDelete(String user_id) {
		String sql = "delete from userInfo where user_id =?";
		int result = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			result = pstmt.executeUpdate();

			return result;
			
		} catch (SQLException e) {
			System.out.println("MemberDelete 에러! : " + e);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {

				}
		}
		
		return result;
	}

}
	
