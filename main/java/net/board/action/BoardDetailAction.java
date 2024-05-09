package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		BoardDAO boarddao=new BoardDAO(); //DB연결
	   	BoardBean boardData=new BoardBean(); 
	   	
	   	int num=Integer.parseInt(request.getParameter("num"));
		boarddao.setReadCountUpdate(num);
	   	boardData=boarddao.getDetail(num);
	   	

	   	//데이터가 없을때
	   	if(boardData == null) {
	   		System.out.println("게시글 디테일정보 불러오기 실패!");
	   		return null;
	   	}
	   	
	   	request.setAttribute("boarddata", boardData);
	   	ActionForward forward = new ActionForward();
	   	forward.setRedirect(false);
	   	forward.setPath("./board/boardDetail.jsp");
	   	
	   	return forward;
	}

}
