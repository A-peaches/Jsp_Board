package net.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.action.Action;
import net.basket.action.ActionForward;
import net.login.db.LoginBean;
import net.login.db.LoginDAO;

public class MyInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginDAO loginDao = new LoginDAO();
		LoginBean loginData = new LoginBean();
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		loginData = loginDao.getUserInfo(user_id);
		
		if(loginData == null) {
			System.out.println("데이터 불러오기 실패!");
			return null;
		}
		
		request.setAttribute("loginData", loginData);
		forward.setRedirect(false);
		forward.setPath("./login/myPage.jsp");
		
		return forward;
	}

}
