package net.board.action;

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
@WebServlet("/BoardFrontController")
public class BoardFrontController extends HttpServlet {
	

	private void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		String RequestURI = request.getRequestURI(); 
		//  예를 들어, 요청 URI가 http://example.com/app/some/path인 경우, /app/some/path가 반환.
		String contextPath = request.getContextPath(); 
		//  만약 애플리케이션이 http://example.com/app/에서 실행되고 있다면, /app이 반환. 루트경로 가져옴.
		String command = RequestURI.substring(contextPath.length()); //
		// 전체 요청 URI에서 컨텍스트 경로를 제거하여 실제 요청에 대한 경로만 추출
		// 이를 위해 substring() 메서드를 사용하고, contextPath.length()를 시작 인덱스로 전달
		// 예를 들어, /app/some/path에서 /app을 제거하면 /some/path가 command 변수에 저장. 
		//이 방식은 URI 라우팅에서 매우 유용하게 사용.
		System.out.println(command +"로 이동중....");
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/BoardList.bo")) {
			action = new BoardListAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardWrite.bo")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/boardWrite.jsp");
		} else if(command.equals("/BoardAddAction.bo")) {
			action = new BoardAddAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardDetailAction.bo")) {
			action = new BoardDetailAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDelete.bo")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./board/boardDelete.jsp");
		} else if(command.equals("/BoardDeleteAction.bo")) {
			action = new BoardDeleteAction();
			try {
				forward = action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if(command.equals("/BoardModify.bo")) {
			 action = new BoardModifyView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		} else if (command.equals("/BoardModifyAction.bo")) {
			action = new BoardModifyAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}  else if (command.equals("/BoardReplyAction.bo")) {
			action = new BoardReplyAction();
			try {
				forward=action.execute(request, response);
			}catch(Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/BoardReplyView.bo")) {
			action = new BoardReplyView();
			try {
				forward=action.execute(request, response);
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


