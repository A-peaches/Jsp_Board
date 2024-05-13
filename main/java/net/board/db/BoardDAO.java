package net.board.db;

import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;
import java.util.*;

public class BoardDAO {//게시판 db를 처리하는 클래스
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	DataSource ds;
	public BoardDAO() {
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
	
	// 글의 개수 구하기
			public int getListCount() {
				int x = 0;

				try {
					con = ds.getConnection();
					
					pstmt = con.prepareStatement("select count(*) from board");
					rs = pstmt.executeQuery();

					if (rs.next()) {
						x = rs.getInt(1);
					}
				} catch (Exception ex) {
					System.out.println("getListCount 에러: " + ex);
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
				return x;
			}

			// 글 목록보기
			public List getBoardList(int page, int limit) {
				String board_list_sql = "select * from " + "(select row_number() over() rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"
						+ "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"
						+ "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "
						+ "(select * from board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc) AS s)AS u " + "where rnum>=? and rnum<=?";		// 오라클->코끼리 서브쿼리 부분에 별칭 추가

				List list = new ArrayList();

				int startrow = (page - 1) * 10 + 1; // 읽기 시작할 row번호
				int endrow = startrow + limit - 1; // 읽기 마지막 row번호.
				try {
					
					con = ds.getConnection();
					
					pstmt = con.prepareStatement(board_list_sql);
					pstmt.setInt(1, startrow);
					pstmt.setInt(2, endrow);
					rs = pstmt.executeQuery();

					while (rs.next()) {
						BoardBean board = new BoardBean();
						board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
						board.setBOARD_NAME(rs.getString("BOARD_NAME"));
						board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
						board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
						board.setBOARD_FILE(rs.getString("BOARD_FILE"));
						board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
						board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
						board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
						board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
						board.setBOARD_DATE(rs.getDate(11));
						list.add(board);
					}

					return list;
				} catch (Exception ex) {
					System.out.println("getBoardList 에러 : " + ex);
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
				return list;			// 오라클->코끼리 list로 바꿔줘야 함
			}
			
			//글 작성
			public boolean boardInsert(BoardBean board) {
				int num = 0;
				String sql="";
				
				int result = 0;
			
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement("select max(board_num) from board");
					rs = pstmt.executeQuery(); //게시글 번호 가장 최근꺼 가지고오기
			
					if(rs.next()) {
						num = rs.getInt(1)+1; // +1해준후 새 게시글번호 저장.
					}else {
						num = 1; // 게시글이 한개도 없을경우 1로 저장.
					}
					
					sql="insert into board(BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
							+ "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"
							+ "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE) values "
							+ "(?,?,?,?,?,?,?,?,?,?,now())";
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.setString(2, board.getBOARD_NAME());
					pstmt.setString(3, board.getBOARD_PASS());
					pstmt.setString(4, board.getBOARD_SUBJECT());
					pstmt.setString(5, board.getBOARD_CONTENT());
					pstmt.setString(6, board.getBOARD_FILE());
					pstmt.setInt(7,num);
					pstmt.setInt(8,0);
					pstmt.setInt(9,0);
					pstmt.setInt(10,0);
					
					result=pstmt.executeUpdate();
					if(result == 0) return false; // insert 실패시
					
					return true; //성공시
				} catch (SQLException e) {
					System.out.println("boardInsert 에러 : "+ e);
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
		
			
				return true;
			}

			
			//게시글 조회시 조회수 증가 
			public void setReadCountUpdate(int num) {
				String sql="update board set BOARD_READCOUNT = BOARD_READCOUNT+1 where BOARD_NUM = ?";
				try{
					con = ds.getConnection();
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
				}catch(SQLException ex){
					System.out.println("setReadCountUpdate 에러 : "+ex);
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
			}

			//특정 게시글 불러오기 
			public BoardBean getDetail(int num) {
				BoardBean board = null;
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(
							"select * from board where BOARD_NUM = ?");
					pstmt.setInt(1, num);
					
					rs= pstmt.executeQuery();
					
					if(rs.next()) { // 게시판정보 불러와서 빈즈에담기
						board = new BoardBean();
						board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
						board.setBOARD_NAME(rs.getString("BOARD_NAME"));
						board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
						board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
						board.setBOARD_FILE(rs.getString("BOARD_FILE"));
						board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
						board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
						board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
						board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
						board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
						
					}
					return board;
				} catch (SQLException e) {
					System.out.println("getDetail 에러 :" +e);
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

			public boolean boardDelete(int num) {

				String sql = "delete from board where BOARD_NUM = ?";
				
				int result = 0;
				
				try {
					con = ds.getConnection();
					pstmt=con.prepareStatement(sql);
					pstmt.setInt(1, num);
					result = pstmt.executeUpdate();
					if(result==0) return false;
					
					return true;
				} catch (SQLException e) {
					System.out.println("boardDelete 에러 : "+e);
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

			public boolean isBoardWriter(int num, String pass) {
				
				String sql = "select BOARD_PASS from board where BOARD_NUM = ?";
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					rs.next();
					
					if(pass.equals(rs.getString("BOARD_PASS"))) {
						return true;
					}
				} catch(SQLException ex){
					System.out.println("isBoardWriter 에러 : "+ex);
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

			public boolean boardModify(BoardBean boardData) {
				String sql ="update board set BOARD_NAME=?,BOARD_SUBJECT=?,BOARD_CONTENT=? where BOARD_NUM=?";
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1,boardData.getBOARD_NAME());
					pstmt.setString(2,boardData.getBOARD_SUBJECT());
					pstmt.setString(3,boardData.getBOARD_CONTENT());
					pstmt.setInt(4,boardData.getBOARD_NUM());
					
					pstmt.executeUpdate();
					return true;
					
				} catch(Exception ex){
					System.out.println("boardModify 에러 : " + ex);
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

			public int boardReply(BoardBean board) {
				
				String board_max_sql="select max(board_num) from board";
				String sql="";
				int num =0;
				int result = 0;
				
				int re_ref = board.getBOARD_RE_REF();
				int re_lev = board.getBOARD_RE_LEV();
				int re_seq = board.getBOARD_RE_SEQ();
				
				
				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(board_max_sql);
					rs = pstmt.executeQuery();
					if(rs.next())num=rs.getInt(1)+1;
					else num = 1;
					
					sql = "update board set BOARD_RE_SEQ = BOARD_RE_SEQ+1 where BOARD_RE_REF=? "
							+ "and BOARD_RE_SEQ>?";
					//답글 개수가 증가하여 시퀀스 증가 ?
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1,re_ref);
					pstmt.setInt(2,re_seq);
					result=pstmt.executeUpdate();
					
					re_seq = re_seq + 1;
					re_lev = re_lev+1;
					
					sql="insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
					+"BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,"
					+"BOARD_READCOUNT,BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,now())";
					
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, num);
					pstmt.setString(2, board.getBOARD_NAME());
					pstmt.setString(3, board.getBOARD_PASS());
					pstmt.setString(4, board.getBOARD_SUBJECT());
					pstmt.setString(5, board.getBOARD_CONTENT());
					pstmt.setString(6, ""); //답장에는 파일을 업로드하지 않음.
					pstmt.setInt(7, re_ref);
					pstmt.setInt(8, re_lev);
					pstmt.setInt(9, re_seq);
					pstmt.setInt(10, 0);
					pstmt.executeUpdate();
					
					return num;
					
				}  catch (SQLException ex) {
					System.out.println("답글 등록 에러! : " + ex);
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
				return 0;
	
			}
}
