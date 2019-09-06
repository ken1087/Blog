package cos.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import cos.com.domain.Reply;
import cos.com.utils.DBManager;
import cos.com.utils.Myutil;

public class ReplyDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;

	private static ReplyDAO instance = new ReplyDAO();

	public ReplyDAO() {
	}

	public static ReplyDAO getInstance() {
		return instance;

	}
	
	
	public List<Reply> findAll(int boardNum) {
		final String SQL = "SELECT * FROM reply where boardnum = ? ORDER BY replyNum DESC";
		Connection conn = DBManager.getConnection();
		List<Reply> list = new ArrayList<Reply>();
		System.out.println(boardNum);
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Reply reply =new Reply();
				reply.setReplyNum(rs.getInt("replynum"));
				reply.setUserID(rs.getString("userID"));
				reply.setBoardNum(rs.getInt("boardNum"));
				reply.setReplyContent(rs.getString("replyContent"));
				reply.setCreateDate(Myutil.StringToLocalDate(rs.getString("createDate")));
				list.add(reply);

			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null;

	}
	public int save(Reply reply) {
		final String SQL = "INSERT INTO reply VALUES(reply_seq.nextval, ?, ?, ?, ? )";
		Connection conn = DBManager.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, reply.getUserID());
			pstmt.setInt(2, reply.getBoardNum());
			pstmt.setString(3, reply.getReplyContent());
			

			pstmt.setString(4, reply.getCreateDate().toString());
			
			
			// result = 0 DML사용시 아무작용하지않았을 경우, 1~ 작용한 열의 갯수
			int result = pstmt.executeUpdate();// 트랜잭션 commit을 가지고 있다.
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return -1;
	}
	public int delete(int replyNum) {
		final String SQL = "DELETE FROM reply where replynum= ?";
		Connection conn = DBManager.getConnection();

		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, replyNum);

			// result = 0 DML사용시 아무작용하지않았을 경우, 1~ 작용한 열의 갯수
			int result = pstmt.executeUpdate();// 트랜잭션 commit을 가지고 있다.
			return result;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}

		return -1;
	}
	
}
