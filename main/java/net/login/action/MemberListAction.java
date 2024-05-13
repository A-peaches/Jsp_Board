package net.login.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.action.Action;
import net.basket.action.ActionForward;
import net.login.db.LoginBean;
import net.login.db.LoginDAO;

public class MemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginDAO loginDao = new LoginDAO();
		ActionForward forward = new ActionForward();
		ArrayList<LoginBean> memberList = new ArrayList<LoginBean>();
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		boolean isadmin = loginDao.adminCheck(user_id);
		
		if(!isadmin) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
	   		out.println("alert('관리자만 접근이 가능합니다!')");
	   		out.println("location.href='./Main.lo';");
	   		out.println("</script>");
	   		out.close();
	   		return null;
		}
		memberList =  loginDao.getMemberList();
		
		session.setAttribute("isadmin", isadmin);
		request.setAttribute("memberList", memberList);
		
		forward.setRedirect(false);
		forward.setPath("/MemberList.lo");
		
		return forward;
	}

}
