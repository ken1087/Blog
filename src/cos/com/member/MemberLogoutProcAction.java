package cos.com.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cos.com.controller.Action;

public class MemberLogoutProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		
		HttpSession session = request.getSession();
		session.invalidate();   // 세션을 무효화 
		
		RequestDispatcher dis =
				request.getRequestDispatcher(url);
		dis.forward(request, response);
		
	}

}
