package cos.com.member;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cos.com.controller.Action;
import cos.com.dao.MemberDAO;
import cos.com.domain.Member;
import cos.com.utils.Myutil;
import cos.com.utils.SHA256;

public class MemberJoinProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "index.jsp";
		
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		String userAddress = request.getParameter("userAddress");
		String userEmail = request.getParameter("userEmail");
		String userPhone = request.getParameter("userPhone");
		String userGender = request.getParameter("userGender");
		
		String salt = "cos";
		userPassword = SHA256.getEncrypt(userPassword, salt);
		
		
		Member member = new Member();
		member.setUserID(userID);
		member.setUserPassword(userPassword);
		member.setUserEmail(userEmail);
		member.setUserAddress(userAddress);
		member.setUserPhone(userPhone);
		member.setUserGender(userGender);
		member.setUserState(1); //1활성, 0휴먼
		member.setCreateDate(LocalDate.now());
		member.setUpdateDate(LocalDate.now());
		
		
		
		
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.save(member);
		
		if(result == 1) {
			RequestDispatcher dis =
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else if(result == 0) {
			Myutil.script("DB오류", response);
		}else if(result == -1) {
			Myutil.script("서버오류", response);
		}
	}

}
