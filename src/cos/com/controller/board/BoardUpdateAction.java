package cos.com.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cos.com.controller.Action;
import cos.com.dao.BoardDAO;
import cos.com.domain.Board;
import cos.com.utils.Myutil;

public class BoardUpdateAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "board/update.jsp";
		HttpSession sesstion = request.getSession();
		String sesstionID = (String) sesstion.getAttribute("userID");
		
		
		int num = Integer.parseInt(request.getParameter("num"));
	
		
		BoardDAO boardDAO = new BoardDAO();
		Board board = boardDAO.findByID(num);
		
		request.setAttribute("boardu", board);
		
		
		
		if(sesstionID.equals("강인")){
			
		}else if(!board.getUserID().equals(sesstionID)) {
			Myutil.script("권한이 없습니다. ", response);
			return;
		}else if(board.getUserID().equals(sesstionID)){
			
		}
		
		if(board != null) {
		RequestDispatcher dis =
				request.getRequestDispatcher(url);
		dis.forward(request, response);
		}else {
			Myutil.script("안돼요", response);
		}
	}

}
