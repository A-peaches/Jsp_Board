package net.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.action.Action;
import net.basket.action.ActionForward;
import net.basket.db.BasketDAO;
import net.login.db.LoginDAO;

public class MemberDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		LoginDAO loginDAO = new LoginDAO();
		
		int result = 0;
		String user_id = request.getParameter("user_id");
		

		result = loginDAO.memberDelete(user_id);
		

		
		if(result > 0 ) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
	   		out.println("alert('삭제가 완료되었습니다!')");
	   		out.println("location.href='./MemberListAction.lo';");
	   		out.println("</script>");
	   		out.close();
	   		
	   		System.out.println("회원 삭제 성공!");
	   		
		} else {
			
			System.out.println("회원 삭제 실패!");
			return null;
		}
		
		forward.setRedirect(false);
		forward.setPath("/MemberListAction.lo");
		return forward;
	}

}
