package net.basket.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketDAO;

public class BasketRemoveAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BasketDAO basketDAO = new BasketDAO();
		int result = 0;
		String cookie_name = request.getParameter("cookie_name");
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		if (cookie_name.equals("Ame")) {
			cookie_name = "American Cookie";
		} else if (cookie_name.equals("Double")) {
			cookie_name = "Double Choco Cookie";
		} else if (cookie_name.equals("Otmeal")) {
			cookie_name = "Otmeal Cookie";
		}

		result = basketDAO.basketRemove(user_id, cookie_name);
		
		forward.setRedirect(false);
		forward.setPath("./BasketViewAction.ba");
		
		if(result > 0 ) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
	   		out.println("alert('삭제가 완료되었습니다!')");
	   		out.println("window.location.href='./BasketViewAction.ba'");
	   		out.println("</script>");
	   		out.close();
	   		
	   		System.out.println("장바구니 삭제 성공!");
	   		
	   		return forward;
	   		
		} else {
			
			System.out.println("장바구니 삭제 실패!");
			
		}
		
		return forward;
	}

}
