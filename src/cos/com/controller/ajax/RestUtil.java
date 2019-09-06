package cos.com.controller.ajax;

import cos.com.dao.BoardDAO;
import cos.com.dao.MemberDAO;
import cos.com.domain.Board;

public class RestUtil {
	public String duplicateID(String userID) {
		
		MemberDAO memberDAO = new MemberDAO();
		int result = memberDAO.findByUserID(userID);
		if(result ==0) {
			return "ok";  //중복된 아이디가 없다
		}
		
		return "error"; //있다.
	}

	/*public Board userID(String search) {
		
		BoardDAO boardDAO = new BoardDAO();
		Board board = boardDAO.findByID(search);
		if(board != null) {
			return board;
		}
		return null;
	}*/
}