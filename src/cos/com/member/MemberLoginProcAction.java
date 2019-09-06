package cos.com.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cos.com.controller.Action;
import cos.com.dao.MemberDAO;
import cos.com.utils.Myutil;
import cos.com.utils.SHA256;

public class MemberLoginProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		
		String userID = request.getParameter("userID");
		String userPassword = request.getParameter("userPassword");
		String idSave = request.getParameter("idSave"); //on
		
		
		if(idSave != null) {
			if(idSave.equals("on")) {
				Cookie cookie = new Cookie("cookieID", userID);
				cookie.setMaxAge(3600); //1시간
				response.addCookie(cookie);
			}
		}else {
			Cookie cookie = new Cookie("cookieID", null);
			cookie.setMaxAge(0); //1시간
			response.addCookie(cookie);
		}
		String salt = "cos";
		userPassword = SHA256.getEncrypt(userPassword, salt);
		
		MemberDAO memberDAO = new MemberDAO();

		int result = memberDAO.findByUserIDAndUserPassword(userID, userPassword);
		
		if(result == 1) {
			//로그인 완료(세션)
			HttpSession session = request.getSession();
			
			session.setAttribute("userID", userID);
			
			
			//index페이지로 이동 
			RequestDispatcher dis =
					request.getRequestDispatcher(url);
			dis.forward(request, response);
			
		}else if(result == 0) {
			//로그인 실패
			Myutil.script("비밀번호 혹은 아이디가 일치하지 않습니다.", response);
		}else {
			//서버 에러
			Myutil.script("서버에러", response);
		}
		
	}

}
