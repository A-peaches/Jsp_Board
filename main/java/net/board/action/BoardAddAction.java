package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardBean;
import net.board.db.BoardDAO;

public class BoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDAO boardDao = new BoardDAO(); // DB연결
		BoardBean boardData = new BoardBean(); // 빈즈생성
		ActionForward forward = new ActionForward(); //전송방식, 패쓰 객체 생성
		
		 request.setCharacterEncoding("UTF-8"); 
		
		String realFolder = "";
		String saveFolder = "boardupload";

		int fileSize=5*1024*1024;
		realFolder = request.getRealPath(saveFolder);
		
		boolean result=false;
		
		try {
			MultipartRequest multi = null;
			
			multi = new MultipartRequest(request,
					realFolder,
					fileSize,
					"UTF-8",
					new DefaultFileRenamePolicy());

			boardData.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
   			boardData.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
	   		boardData.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
	   		boardData.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
	   		boardData.setBOARD_FILE(
	   				multi.getFilesystemName((String)multi.getFileNames().nextElement()));
	   		
	   		result=boardDao.boardInsert(boardData);

	   		if(result == false) {
	   			System.out.println("게시글 등록에 실패하였습니다.");
	   			return null;
	   		}
	   		System.out.println("게시글 등록에 성공하였습니다.");
	   		
	   		forward.setRedirect(true);
	   		forward.setPath("./BoardList.bo");
	   		return forward;
	   		
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
