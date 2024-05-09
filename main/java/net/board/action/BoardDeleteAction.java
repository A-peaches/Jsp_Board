package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

public class BoardDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDao = new BoardDAO();
		
		boolean result = false;
		boolean usercheck = false;
		int num = Integer.parseInt(request.getParameter("num"));
		
		ActionForward forward = new ActionForward();
		
		//비밀번호 체크
		usercheck = boardDao.isBoardWriter(num, request.getParameter("BOARD_PASS"));

		
		if(usercheck == false) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
	   		out.println("alert('비밀번호가 일치하지 않습니다!')");
	   		out.println("location.href='./BoardList.bo';");
	   		out.println("</script>");
	   		out.close();
	   		return null;
		}
		
		//데이터 삭제 정상처리 검증
		result = boardDao.boardDelete(num);
		if(result==false) {
			System.out.println("게시글 삭제 실패!");
			return null;
		}
		
		System.out.println("게시글 삭제 성공!");
		forward.setRedirect(true);
		forward.setPath("./BoardList.bo");
		return forward;
	}

}
