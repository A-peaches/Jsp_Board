package net.login.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.basket.action.Action;
import net.basket.action.ActionForward;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	

	private void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		String RequestURI = request.getRequestURI(); 
		String contextPath = request.getContextPath(); 
		String command = RequestURI.substring(contextPath.length()); //

		System.out.println(command +"로 이동중....");
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/MemberJoinView.lo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./login/joinForm.jsp");
		} else if(command.equals("/Login.lo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./login/loginForm.jsp");
		} else if(command.equals("/Main.lo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./login/main.jsp");
		} else if(command.equals("/MemberJoinAction.lo")) {
			action = new MemberJoinAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberLoginAction.lo")) {
			action = new MemberLoginAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MyPage.lo")) {
			action = new MyInfoAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberListAction.lo")) {
			action = new MemberListAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/MemberList.lo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./login/member_list.jsp");
		} else if(command.equals("/MemberDelete.lo")) {
			action = new MemberDeleteAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward.isRedirect()) {
			response.sendRedirect(forward.getPath()); //리다이렉트 방식일때
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
			dispatcher.forward(request, response); //forward방식일때 
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}


