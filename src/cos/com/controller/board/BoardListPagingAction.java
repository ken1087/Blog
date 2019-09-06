package cos.com.controller.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cos.com.controller.Action;
import cos.com.dao.BoardDAO;
import cos.com.domain.Board;
import cos.com.domain.Code;

public class BoardListPagingAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "index.jsp";
		
		int start = 0;
		int end = 6;
		
		if(request.getParameter("start") != null && request.getParameter("end") != null) {
			try {
				start = Integer.parseInt(request.getParameter("start"));
				end = Integer.parseInt(request.getParameter("end"));
			} catch (Exception e) {
				start = 0;
				end = 6;
			}
			
		}
		BoardDAO boardDAO = new BoardDAO();
		List<Board> list = boardDAO.findAll(start, end);
		
		request.setAttribute("state", 1); //1은 서블릿을 탔다.
		request.setAttribute("list", list);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("maxListNum", Code.getMaxListNum());
		RequestDispatcher dis = 
				request.getRequestDispatcher(url);
		dis.forward(request, response);
		
		
	}

}
