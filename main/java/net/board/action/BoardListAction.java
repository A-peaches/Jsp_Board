package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardListAction implements Action{ //비즈니스 로직 클래스

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		BoardDAO boardDao = new BoardDAO();
	} 
	
	
	
}
