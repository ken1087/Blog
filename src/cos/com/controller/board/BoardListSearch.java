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



public class BoardListSearch implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url= "index.jsp";
		
		String search = request.getParameter("search");
		String option = request.getParameter("option");
		
		BoardDAO boardDAO = new BoardDAO();
		
		List<Board> list = boardDAO.searchboard(option, search); 

		
		request.setAttribute("list", list);
		RequestDispatcher dis = 
				request.getRequestDispatcher(url);
		dis.forward(request, response);
	
		
	}

}
