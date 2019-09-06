package cos.com.controller.board;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cos.com.controller.Action;
import cos.com.dao.BoardDAO;
import cos.com.domain.Board;

public class BoardWriteProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "index.jsp";
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String userID = request.getParameter("userID");
		int readCount = 0;
		LocalDate createDate = LocalDate.now();
		LocalDate updateDate = LocalDate.now();
		
		Board board = new Board();
		board.setTitle(title);
		board.setContent(content);
		board.setUserID(userID);
		board.setReadCount(readCount);
		board.setCreateDate(createDate);
		board.setUpdateDate(updateDate);
		
		BoardDAO boardDAO = new BoardDAO();
		int result = boardDAO.save(board);
		
		if(result == 1) {
			RequestDispatcher dis = 
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			try {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('"+"안돼요"+"')");
				script.println("history.back()");
				script.println("</script>");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
