package net.basket.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketDAO;

public class BasketCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BasketDAO basketDAO = new BasketDAO();	
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		

		basketDAO.removeAll(user_id);
		
		forward.setRedirect(false);
		forward.setPath("./MenuViewAction.ba");
		

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
	   	out.println("alert('결제가 완료되었습니다!')");
	   	out.println("window.location.href='./BasketViewAction.ba'");
	   	out.println("</script>");
	   	out.close();
	   		
	   	System.out.println("결제 성공!");
	  
	
		
		return forward;
	}

}
