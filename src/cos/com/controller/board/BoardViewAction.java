package cos.com.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cos.com.dao.ReplyDAO;
import cos.com.domain.Reply;

import cos.com.controller.Action;
import cos.com.dao.BoardDAO;
import cos.com.domain.Board;
import cos.com.utils.Myutil;



public class BoardViewAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "board/view.jsp";
		
		//request.setAttribute() -> Object
		//request.getAttribute() -> Object -> EL표현식 ${}
		
		//request.getParameter() -> String
		//Integer.parseInt는 문자를 숫자로 변환해주는 함수이다.
		int num = Integer.parseInt(request.getParameter("num"));
		
		
		HttpSession session = request.getSession();
		String sessionID = (String) session.getAttribute("userID");
		
		//DAO -> Data Access Object 객체 생성 - 함수 만들어주고!!
		BoardDAO boardDAO = new BoardDAO();
		
		//상세보기시에 조회수 증가.
		int result = boardDAO.updateReadCount(num);
		
		//상세보기를 위해서 객체하나 들고옴.
		Board board = boardDAO.findByID(num);
		
		ReplyDAO replyDAO =ReplyDAO.getInstance();
		List<Reply> list = replyDAO.findAll(num);
		
		if(sessionID == null) {
			Myutil.script("권한이 없습니다. 로그인을 해주세요", response);
			return;
		}
		
		if(board != null && result == 1) {
			request.setAttribute("boardw", board);
			request.setAttribute("list", list);
			RequestDispatcher dis =
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			Myutil.script("server error","board?cmd=boardListPaging", response);
		}
		
		
	}
}
