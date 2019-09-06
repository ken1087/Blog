package cos.com.controller.reply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cos.com.dao.ReplyDAO;
import cos.com.domain.Reply;
import com.google.gson.Gson;


@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReplyController() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		BufferedReader br = request.getReader();
		String line = br.readLine();
		System.out.println(line);
		Gson gson = new Gson();
		Reply reply = gson.fromJson(line, Reply.class);
		reply.setCreateDate(LocalDate.now());
	
		ReplyDAO replyDAO = ReplyDAO.getInstance();
		int result = replyDAO.save(reply);
		PrintWriter out = response.getWriter();
		if (result == 1) {
			out.print("ok");
		
		} else {
			out.print("errorServlet");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
