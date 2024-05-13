package net.basket.action;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;

public class BasketChangeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BasketBean cookies = new BasketBean();
		BasketDAO basket = new BasketDAO();
		ArrayList<BasketBean> basketList = new ArrayList<BasketBean>();
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		int ameCnt = 0;
		int doubleCnt = 0;
		int otmealCnt = 0;

		String ameCntStr = request.getParameter("AmeCnt");
		if (ameCntStr != null && !ameCntStr.isEmpty()) {
		    ameCnt = Integer.parseInt(ameCntStr);
		}

		String doubleCntStr = request.getParameter("DoubleCnt");
		if (doubleCntStr != null && !doubleCntStr.isEmpty()) {
		    doubleCnt = Integer.parseInt(doubleCntStr);
		}

		String otmealCntStr = request.getParameter("OtmealCnt");
		if (otmealCntStr != null && !otmealCntStr.isEmpty()) {
			otmealCnt = Integer.parseInt(otmealCntStr);
		}
		
		basket.removeAll(user_id);
		
		basketList = basket.basketInsert(user_id, ameCnt, doubleCnt, otmealCnt);
		
		request.setAttribute("basketList", basketList);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println("<script>");
	   	out.println("alert('수량 변경이 완료되었습니다!')");
	   	out.println("window.location.href='./BasketViewAction.ba'");
	   	out.println("</script>");
	   	out.close();
	   		
	   	
	   	System.out.println("장바구니 업데이트 성공!");


		forward.setRedirect(false);
		forward.setPath("/basketViewAction.ba");
		
		
		
		return forward;
	}

}
