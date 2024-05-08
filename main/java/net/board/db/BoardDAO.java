package net.board.db;

import java.sql.*;

import javax.naming.*;
import javax.sql.DataSource;

public class BoardDAO {//게시판 db를 처리하는 클래스
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public BoardDAO() {
		Context init;
		try {
			init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");
			con = ds.getConnection();
		} catch (Exception e) {
			System.out.println("DB연결 실패");
			return;
		}

	}
}
