package cos.com.controller.ajax;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cos.com.domain.Board;


@WebServlet("/rest")
public class RestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public RestController() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userID = request.getParameter("userID");
		
		
		RestUtil r = new RestUtil();
		String result = r.duplicateID(userID);
		
		PrintWriter out = response.getWriter();
		out.print(result);
		
		/*String search = request.getParameter("search");
		Board board = new Board();
		board = r.userID(search);
		
		
		
		if(board != null) {
			out.print(board);
		}*/
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		
		
		
		
		doGet(request, response);
	}

}
