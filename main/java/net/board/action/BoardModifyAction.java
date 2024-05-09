package net.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		BoardDAO boardDao = new BoardDAO();
		BoardBean boardData = new BoardBean();
		
		ActionForward forward = new ActionForward();
		boolean result = false;
		
		int num = Integer.parseInt(request.getParameter("BOARD_NUM"));
		
		//비밀번호 검증
		boolean usercheck = boardDao.isBoardWriter(num, request.getParameter("BOARD_PASS"));
	
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
		
		try{
			boardData.setBOARD_NUM(num);
			boardData.setBOARD_NAME(request.getParameter("BOARD_NAME"));
			boardData.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
			boardData.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
			
			result = boardDao.boardModify(boardData);
			
			if(result == false) {
				System.out.println("게시글 수정 실패!");
				return null;
			}
			
			System.out.println("게시글 수정 성공!");
			
			forward.setRedirect(true);
			forward.setPath("./BoardDetailAction.bo?num="+num);
			return forward;
			
			}catch(Exception ex){
				ex.printStackTrace();	
				return null;
			}
		 
		 	
	}
}
