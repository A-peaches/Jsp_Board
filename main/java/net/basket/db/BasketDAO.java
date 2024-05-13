package net.basket.db;

import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;
import java.util.*;

public class BasketDAO {//게시판 db를 처리하는 클래스
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	public BasketDAO() {
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

	public ArrayList<BasketBean> basketInsert(String user_id, int ameCnt, int doubleCnt, int otmealCnt) {
		//0이아닌 아이들 처리
		ArrayList<BasketBean> basketList = new ArrayList<BasketBean>();
		BasketBean cookies;
		int result = 0;
		String sql = "insert into basket (user_id, cookie_name, cookie_img, cookie_qnt, cookie_price) values "
				+ "(?,?,?,?,?)";
	
		String[] cookieNames = {"American Cookie",  "Double Choco Cookie", "Otmeal Cookie"};
		int[] basketState = new int[3];
		

		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
		
			//insert 시작 
				if(ameCnt != 0) {
					
					
					cookies = new BasketBean();
					cookies.setUser_id(user_id);
					cookies.setCookie_name("American Cookie");
					cookies.setCookie_img("./img/cookie1.png");
					cookies.setCookie_qnt(ameCnt);
					cookies.setCookie_price(3000);
					
					basketList.add(cookies);
					
					pstmt.setString(1, cookies.getUser_id());
					pstmt.setString(2, cookies.getCookie_name());
					pstmt.setString(3, cookies.getCookie_img());
					pstmt.setInt(4, cookies.getCookie_qnt());
					pstmt.setInt(5, cookies.getCookie_price());
					
					result = pstmt.executeUpdate();
					if(result == 0) return null;
					
				} 
				
				
				if (doubleCnt != 0) {
					cookies = new BasketBean();
					cookies.setUser_id(user_id);
					cookies.setCookie_name("Double Choco Cookie");
					cookies.setCookie_img("./img/cookie2.png");
					cookies.setCookie_qnt(doubleCnt);
					cookies.setCookie_price(2800);
					
					basketList.add(cookies);
					
					pstmt.setString(1, cookies.getUser_id());
					pstmt.setString(2, cookies.getCookie_name());
					pstmt.setString(3, cookies.getCookie_img());
					pstmt.setInt(4, cookies.getCookie_qnt());
					pstmt.setInt(5, cookies.getCookie_price());
					
					result = pstmt.executeUpdate();
					if(result == 0) return null;
				}
				
				if (otmealCnt != 0) {
					cookies = new BasketBean();
					cookies.setUser_id(user_id);
					cookies.setCookie_name("Otmeal Cookie");
					cookies.setCookie_img("./img/cookie3.png");
					cookies.setCookie_qnt(otmealCnt);
					cookies.setCookie_price(2600);
					
					basketList.add(cookies);
					
					pstmt.setString(1, cookies.getUser_id());
					pstmt.setString(2, cookies.getCookie_name());
					pstmt.setString(3, cookies.getCookie_img());
					pstmt.setInt(4, cookies.getCookie_qnt());
					pstmt.setInt(5, cookies.getCookie_price());
					
					result = pstmt.executeUpdate();
					if(result == 0) return null;
				}
				
				
			
			return basketList;
			
		} catch (SQLException e) {
			System.out.println("BasketInsert 에러 : "+ e);
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

		return null;
	}

	public ArrayList<BasketBean> getBasket(String user_id) {
		String sql = "select * from basket where user_id = ?";
		ArrayList<BasketBean> basketList = new ArrayList<BasketBean>();
		BasketBean cookies;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				cookies = new BasketBean();
				cookies.setUser_id(rs.getString("user_id"));
				cookies.setCookie_name(rs.getString("cookie_name"));
				cookies.setCookie_img(rs.getString("cookie_img"));
				cookies.setCookie_qnt(rs.getInt("cookie_qnt"));
				cookies.setCookie_price(rs.getInt("cookie_price"));
				
				basketList.add(cookies);
			}
			
			return basketList;
		} catch (SQLException e) {
			System.out.println("getBasketList 에러 : " + e);
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

		return basketList;
	}

	public int basketRemove(String user_id, String cookie_name) {
		String sql = "delete from basket where user_id =? AND cookie_name=?";
		int result = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, cookie_name);
			
			result = pstmt.executeUpdate();

			return result;
			
		} catch (SQLException e) {
			System.out.println("BasketRemove 에러! : " + e);
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

	public void removeAll(String user_id) {
		String sql = "delete from basket where user_id =?";
		int result = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			result = pstmt.executeUpdate();
			
			return;
			
		} catch (SQLException e) {
			System.out.println("BasketRemove 에러! : " + e);
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
		
	}
	
}