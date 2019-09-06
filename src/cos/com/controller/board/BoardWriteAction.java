package cos.com.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cos.com.controller.Action;
import cos.com.utils.Myutil;

public class BoardWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "board/write.jsp";
		
		HttpSession session = request.getSession();
		String sessionID = (String) session.getAttribute("userID");
		
		if(sessionID == null) {
			Myutil.script("로그인을 해주세요", response);
			return;
		}
		
		RequestDispatcher dis = 
				request.getRequestDispatcher(url);
		dis.forward(request, response);
		
	}

}
