package net.login.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.action.Action;
import net.basket.action.ActionForward;
import net.login.db.LoginDAO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginDAO loginDao = new LoginDAO();
		ActionForward forward = new ActionForward();
		boolean result = false;
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		result = loginDao.loginCheck(user_id,user_pw);
		
		if(!result) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
	   		out.println("alert('ID 또는 PW가 일치하지 않습니다!')");
	   		out.println("location.href='./loginForm.lo';");
	   		out.println("</script>");
	   		out.close();
	   		return null;
		}
		
		System.out.println("로그인 성공!");
		
		HttpSession session = request.getSession();
		session.setAttribute("user_id",user_id);
		forward.setRedirect(true);
		forward.setPath("./Main.lo");
		return forward;
	}

}
