package cos.com.controller.board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cos.com.controller.Action;
import cos.com.controller.ActionFactory;


@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public BoardController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String cmd = null;
		request.getParameter("cmd");
		if(request.getParameter("cmd") != null) {
			cmd = request.getParameter("cmd");
		}
		
		ActionFactory af = ActionFactory.getInstance();
		Action action = af.getAction(cmd);
		if(action != null)action.execute(request, response);

	}
		
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
