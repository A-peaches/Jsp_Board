package net.basket.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;

public class BasketAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BasketBean cookies = new BasketBean();
		BasketDAO basket = new BasketDAO();
		ArrayList<BasketBean> basketList = new ArrayList<BasketBean>();
		
		HttpSession session = request.getSession();
		ArrayList<BasketBean> sessionBasket = (ArrayList) session.getAttribute("basketList");
		String user_id = (String) session.getAttribute("user_id");
		int ameCnt = Integer.parseInt(request.getParameter("AmeCnt"));
		int doubleCnt = Integer.parseInt(request.getParameter("DoubleCnt"));
		int otmealCnt = Integer.parseInt(request.getParameter("OtmealCnt"));
		
		boolean exists = false;
		 if (sessionBasket != null) {
		        for (BasketBean sb : sessionBasket) {
		            if ((sb.getFormat().equals("Ame") && ameCnt > 0) ||
		                (sb.getFormat().equals("Double") && doubleCnt > 0) ||
		                (sb.getFormat().equals("Otmeal") && otmealCnt > 0)) {
		                exists = true;
		                break;
		            }
		        }
		    }
		 
			forward.setRedirect(false);
			forward.setPath("/BasketViewAction.ba");
			
		 if(exists) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('이미 장바구니에 있습니다! 장바구니에서 수량을 변경해주세요!')");
		   		out.println("window.location.href='./BasketViewAction.ba'");
				out.println("</script>");
				out.close();

				System.out.println("이미 있는 상품임!!");
				return null; // 추가 처리를 중지하고 반환
			}

		System.out.println(user_id);
		System.out.println(ameCnt);
		System.out.println(doubleCnt);
		System.out.println(otmealCnt);
		basketList = basket.basketInsert(user_id, ameCnt, doubleCnt, otmealCnt);
		
		request.setAttribute("basketList", basketList);
		

		
		return forward;
	}

}
