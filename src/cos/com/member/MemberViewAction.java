package cos.com.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cos.com.controller.Action;
import cos.com.dao.MemberDAO;
import cos.com.domain.Member;
import cos.com.utils.Myutil;

public class MemberViewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userID = request.getParameter("userID");
		String url = "member/memberview.jsp";
		
		HttpSession session = request.getSession();
		String sessionID = (String) session.getAttribute("userID");
		
		if(sessionID == null) {
			Myutil.script("로그인을 해주세요", response);
			return;
		}
		
		MemberDAO memberDAO = new MemberDAO();
		
		Member member = memberDAO.findMy(userID);
		
		
		
		if(member != null) {
			request.setAttribute("member", member);
			RequestDispatcher dis =
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			Myutil.script("안돼요", response);
		}
	}
	
}
