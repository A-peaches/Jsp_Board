package net.basket.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;

public class BasketViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BasketBean cookies = new BasketBean();
		BasketDAO basket = new BasketDAO();
		ArrayList<BasketBean> basketList = new ArrayList<BasketBean>();
		
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		
		basketList = basket.getBasket(user_id);
		
		if(basketList == null) {
			request.setAttribute("basketList", null);
		} else {
			session.setAttribute("basketList", basketList);
		}
		
		forward.setRedirect(false);
		forward.setPath("./basket/cookieCart.jsp");
		
		
		return forward;
		
	}

}
