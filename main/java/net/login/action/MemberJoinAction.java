package net.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.basket.action.Action;
import net.basket.action.ActionForward;
import net.login.db.LoginBean;
import net.login.db.LoginDAO;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginDAO loginDao = new LoginDAO();
		LoginBean joinData = new LoginBean();
		ActionForward forward = new ActionForward();
		boolean result = false ;
		
		request.setCharacterEncoding("UTF-8"); 
		
		joinData.setUser_id(request.getParameter("user_id"));
		joinData.setUser_pw(request.getParameter("user_pw"));
		joinData.setUser_email(request.getParameter("user_email"));
		joinData.setUser_name(request.getParameter("user_name"));
		
		String birth = request.getParameter("year")+"년 "+request.getParameter("month")+"월 "+
					   request.getParameter("day")+"일 ";
		System.out.println(birth);
		joinData.setBirth(birth);
		joinData.setHobby(request.getParameter("hobby"));
		joinData.setInfo(request.getParameter("info"));
		
		result = loginDao.addMember(joinData);
		
		if(result == false) {
			System.out.println("회원가입 실패!");
			return null;
		}
		System.out.println("회원가입 성공!");
		
		forward.setRedirect(true);
		forward.setPath("./Login.lo");
		
		return forward;
	}

}
