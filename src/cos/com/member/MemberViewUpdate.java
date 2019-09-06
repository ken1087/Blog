package cos.com.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cos.com.controller.Action;
import cos.com.dao.BoardDAO;
import cos.com.dao.MemberDAO;
import cos.com.domain.Board;
import cos.com.domain.Member;
import cos.com.utils.Myutil;

public class MemberViewUpdate implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		String userID = request.getParameter("userID");
		
		String url = "member/memberupdate.jsp";
		MemberDAO memberDAO = new MemberDAO();
		Member member = memberDAO.findMy(userID);
		
		
		request.setAttribute("member", member);
		
		if(member != null) {
		
		RequestDispatcher dis =
				request.getRequestDispatcher(url);
		dis.forward(request, response);
		}else {
			Myutil.script("안돼요", response);
		}
	
	}

}
