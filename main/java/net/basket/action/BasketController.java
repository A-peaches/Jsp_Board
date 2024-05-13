package net.basket.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("/BasketController")
public class BasketController extends HttpServlet {
	

	private void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		String RequestURI = request.getRequestURI(); 
		String contextPath = request.getContextPath(); 
		String command = RequestURI.substring(contextPath.length()); //

		System.out.println(command +"로 이동중....");
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/MenuViewAction.ba")) {
			forward = new ActionForward();
			try {
				forward.setRedirect(false);
				forward.setPath("./basket/cookieList.jsp");
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BasketViewAction.ba")) {
			action = new BasketViewAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BasketAddAction.ba")) {
			action = new BasketAddAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BasketChangeAction.ba")) {
			action = new BasketChangeAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BasketRemoveAction.ba")) {
			action = new BasketRemoveAction();
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


