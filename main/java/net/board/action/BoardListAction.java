package net.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.board.db.BoardDAO;

public class BoardListAction implements Action{ //비즈니스 로직 클래스

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boarddao=new BoardDAO();
		List boardlist=new ArrayList();
		
	  	int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount=boarddao.getListCount(); 
		boardlist = boarddao.getBoardList(page,limit); 
		

   		int maxpage=(int)((double)listcount/limit+0.95); //한페이지당 10개가최대.
   		int startpage = (((int) ((double)page / 10 + 0.9)) - 1) * 10 + 1;
   		int endpage = maxpage;
   		
   		if (endpage>startpage+10-1) endpage=startpage+10-1;
   		
   		request.setAttribute("page", page);		
   		request.setAttribute("maxpage", maxpage);
   		request.setAttribute("startpage", startpage); 
   		request.setAttribute("endpage", endpage);   
		request.setAttribute("listcount",listcount); 
		request.setAttribute("boardlist", boardlist);
		
		ActionForward forward= new ActionForward();
	   	forward.setRedirect(false);
   		forward.setPath("./board/boardList.jsp");
   		return forward;
	 }
	
	
}
