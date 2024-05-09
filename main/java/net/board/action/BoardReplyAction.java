package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardReplyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO boardDao = new BoardDAO();
		BoardBean boardData = new BoardBean();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		boardData = boardDao.getDetail(num);
		// boardNum를 갖고오기 위함?
		
		if(boardData == null) {
			System.out.println("게시글 정보 가져오기 실패!");
			return null;
		}
		
		System.out.println("게시글 정보 가져오기 성공!");
		
		request.setAttribute("boarddata", boardData);
		forward.setRedirect(false);
		forward.setPath("./board/boardReply.jsp");
		
		return forward;
	}

}
