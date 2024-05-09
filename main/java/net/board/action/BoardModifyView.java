package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardModifyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		BoardDAO boardDao = new BoardDAO();
		ActionForward forward = new ActionForward();
		BoardBean boardData = new BoardBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		boardData = boardDao.getDetail(num);
		
		if(boardData==null){
	   		System.out.println("게시글 불러오기 실패!");
	   		return null;
	   	}
		
		System.out.println("게시글 불러오기 성공!");
		
		request.setAttribute("boarddata", boardData);
		forward.setRedirect(false);
		forward.setPath("./board/boardModify.jsp");
		return forward;
	}

}
