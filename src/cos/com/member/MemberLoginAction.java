package cos.com.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cos.com.controller.Action;

public class MemberLoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String url = "member/login.jsp";
		
		RequestDispatcher dis =
				request.getRequestDispatcher(url);
		dis.forward(request, response);
		
		
	}

}
