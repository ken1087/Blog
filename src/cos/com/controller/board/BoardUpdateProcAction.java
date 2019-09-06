package cos.com.controller.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cos.com.controller.Action;
import cos.com.dao.BoardDAO;
import cos.com.dto.BoardUpdateDTO;
import cos.com.utils.Myutil;

public class BoardUpdateProcAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String url = "board?cmd=boardView&num="+num;
		
		BoardUpdateDTO bDto = new BoardUpdateDTO();
		bDto.setNum(num);
		bDto.setTitle(title);
		bDto.setContent(content);
		
		BoardDAO boardDAO = new BoardDAO();
		
		int result = boardDAO.update(bDto);
		if(result == 1) {
			RequestDispatcher dis =
					request.getRequestDispatcher(url);
			dis.forward(request, response);
		}else {
			Myutil.script("안돼요", response);
		}
		
		
	}

}
