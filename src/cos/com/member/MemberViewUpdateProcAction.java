package cos.com.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cos.com.controller.Action;
import cos.com.dao.MemberDAO;

import cos.com.dto.MemberDTO;
import cos.com.utils.Myutil;

public class MemberViewUpdateProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "index.jsp";
	
		String userID = request.getParameter("userID");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		String userAddress = request.getParameter("userAddress");
		
		
		
		MemberDTO mdto = new MemberDTO();
		mdto.setUserID(userID);
		mdto.setUserEmail(userEmail);
		mdto.setUserPhone(userPhone);
		mdto.setUserAddress(userAddress);
		
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.memberUpdate(mdto);
		
		if(result == 1) {
			RequestDispatcher dis =
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			Myutil.script("안돼요", response);
		}
	}
	
}
