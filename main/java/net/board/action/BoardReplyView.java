package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardReplyView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		 
		BoardDAO boardDao=new BoardDAO();
		BoardBean boardData=new BoardBean();
		
		int result = 0;
		
		boardData.setBOARD_NUM(Integer.parseInt(request.getParameter("BOARD_NUM")));
   		boardData.setBOARD_NAME(request.getParameter("BOARD_NAME"));
   		boardData.setBOARD_PASS(request.getParameter("BOARD_PASS"));
   		boardData.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
   		boardData.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
   		boardData.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
   		boardData.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
   		boardData.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
   	
   		result = boardDao.boardReply(boardData);
   		if(result == 0) {
   			System.out.println("답글 등록 실패!");
   			return null;
   		}
		 
   		System.out.println("답글 등록 성공!");
   		
   		forward.setRedirect(true);
   		forward.setPath("./BoardDetailAction.bo?num="+result);
		return forward;
	}

}
