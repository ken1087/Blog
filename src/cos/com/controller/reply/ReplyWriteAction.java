package cos.com.controller.reply;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import cos.com.controller.Action;
import cos.com.dao.ReplyDAO;
import cos.com.domain.Reply;
import cos.com.utils.Myutil;

public class ReplyWriteAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String userID = (String) session.getAttribute("userID");
		
	 	int boardNum;
		
		String replyContent;
		LocalDate createDate;
		String url;
			if(request.getParameter("boardNum") != null) {
			boardNum = Integer.parseInt(request.getParameter("boardNum"));
			url = "board?cmd=boardView&num=" + boardNum ;
			}else {
				boardNum = 1;
				 url = "index.jsp" ;
				RequestDispatcher dis = request.getRequestDispatcher(url);
				dis.forward(request, response);
			}
			replyContent = request.getParameter("replyContent");
			createDate = LocalDate.now();
		

		Reply reply = new Reply();
		reply.setBoardNum(boardNum);
		reply.setUserID(userID);
		reply.setReplyContent(replyContent);
		reply.setCreateDate(createDate);
		

		ReplyDAO replyDAO = ReplyDAO.getInstance();
		int result = replyDAO.save(reply);
		System.out.println("왔나?");
		if (result == 1) {
			
			RequestDispatcher dis = request.getRequestDispatcher(url);
			dis.forward(request, response);
		} else {
			Myutil.script("errorWrite", response);
		}

	}
	

}
