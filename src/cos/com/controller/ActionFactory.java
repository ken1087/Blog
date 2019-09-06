package cos.com.controller;

import cos.com.controller.reply.ReplyDeleteAction;
import cos.com.controller.reply.ReplyWriteAction;
import cos.com.controller.board.BoardDeleteAction;
import cos.com.controller.board.BoardListPagingAction;
import cos.com.controller.board.BoardListSearch;
import cos.com.controller.board.BoardUpdateAction;
import cos.com.controller.board.BoardUpdateProcAction;
import cos.com.controller.board.BoardViewAction;
import cos.com.controller.board.BoardWriteAction;
import cos.com.controller.board.BoardWriteProcAction;
import cos.com.member.MemberJoinAction;
import cos.com.member.MemberJoinProcAction;
import cos.com.member.MemberLoginAction;
import cos.com.member.MemberLoginProcAction;
import cos.com.member.MemberLogoutProcAction;
import cos.com.member.MemberViewAction;
import cos.com.member.MemberViewUpdate;
import cos.com.member.MemberViewUpdateProcAction;

public class ActionFactory {
	
	private static ActionFactory instance = new ActionFactory(); 
	
	private ActionFactory() {}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String cmd) {
		if(cmd == null) {
			return new BoardListPagingAction();
		}else if(cmd.equals("boardListPage")) {
		return new BoardListPagingAction();
		}else if(cmd.equals("boardWrite")) {
			return new BoardWriteAction();
		}else if(cmd.equals("boardWriteProc")) {
			return new BoardWriteProcAction();
		}else if(cmd.equals("boardView")) {
			return new BoardViewAction();
		}else if(cmd.equals("boardUpdate")) {
			return new BoardUpdateAction();
		}else if(cmd.equals("boardUpdateProc")) {
			return new BoardUpdateProcAction();
		}else if(cmd.equals("boardDelete")) {
			return new BoardDeleteAction();
		}else if(cmd.equals("memberLogin")) {
			return new MemberLoginAction();
		}else if(cmd.equals("memberLoginProc")) {
			return new MemberLoginProcAction();
		}else if(cmd.equals("memberJoin")) {
			return new MemberJoinAction();
		}else if(cmd.equals("memberJoinProc")) {
			return new MemberJoinProcAction();
		}else if(cmd.equals("memberLogout")) {
			return new MemberLogoutProcAction();
		}else if(cmd.equals("memberView")) {
			return new MemberViewAction();
		}else if(cmd.equals("memberupdate")) {
			return new MemberViewUpdate();
		}else if(cmd.equals("memberviewudapteproc")) {
			return new MemberViewUpdateProcAction();
		}else if(cmd.equals("boardListsearch")) {
			return new BoardListSearch();
		}else if (cmd.contentEquals("replyWrite")) {
			return new ReplyWriteAction();
		}else if (cmd.contentEquals("replyDelete")) {
			return new ReplyDeleteAction();
		}
		
	return null;
	}
}
