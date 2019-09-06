package cos.com.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cos.com.controller.Action;
import cos.com.dao.BoardDAO;
import cos.com.utils.Myutil;


public class BoardDeleteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "index.jsp";
		
		int num = Integer.parseInt(request.getParameter("num"));
		String userID = request.getParameter("userID");
		
		HttpSession sesstion = request.getSession();
		String sesstionID = (String) sesstion.getAttribute("userID");
		
		if(sesstionID.equals("강인")) {
			
		}else if(!sesstion.equals(userID)) {
			Myutil.script("권한이 없습니다.", response);
			return;
		}
		
		
		
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.delete(num);
		
		if(result == 1) {
			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			Myutil.script("안돼요", response);
		}
		
		
	}

}
