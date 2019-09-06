package cos.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cos.com.domain.Board;
import cos.com.domain.Code;
import cos.com.dto.BoardUpdateDTO;
import cos.com.utils.DBManager;
import cos.com.utils.Myutil;

public class BoardDAO {
	
	PreparedStatement pstmt;
	ResultSet rs;
	
	public List<Board> findAll(int start ,int end){
		final String SQL = "SELECT (SELECT count(*)\r\n" + 
				"FROM board) AS cnt ,num, title, content, userID, readCount, createDate, updateDate, mynum\r\n" + 
				"FROM\r\n" + 
				"(\r\n" + 
				"SELECT \r\n" + 
				"num, title, content, userID, readCount, createDate, updateDate, rownum AS mynum\r\n" + 
				"FROM board\r\n" + 
				"ORDER BY num DESC\r\n" + 
				")\r\n" + 
				"WHERE mynum > ? AND mynum < ?";
		
		Connection conn = DBManager.getConnection();
		
		List<Board> list = new ArrayList<Board>();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.isFirst()) { 
					Code.setMaxListNum(rs.getInt("cnt"));
				}
				Board board = new Board();
				board.setNum(rs.getInt("num")); //컬럼명
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setUserID(rs.getString("userID"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(Myutil.StringToLocalDate(rs.getString("createDate")));
				board.setUpdateDate(Myutil.StringToLocalDate(rs.getString("updateDate")));
				
				list.add(board);	
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int save(Board board) {
		
		final String SQL = "INSERT INTO board(num, title, content, userID, readCount, createDate, updateDate) VALUES(board_seq.nextval, ?, ?, ?, ?, ?, ?)";
		
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getUserID());
			pstmt.setInt(4, board.getReadCount());
			pstmt.setString(5, board.getCreateDate().toString());
			pstmt.setString(6, board.getUpdateDate().toString());
			
			int result = pstmt.executeUpdate();
			//result = 0 fail, result = 1 success
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return -1;
	}

	public Board findByID(int num) {
Connection conn = DBManager.getConnection();

		
		final String SQL = "SELECT* FROM board WHERE num=?";
		Board board = new Board();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {	
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setUserID(rs.getString("userID"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(Myutil.StringToLocalDate(rs.getString("createDate")));
				board.setUpdateDate(Myutil.StringToLocalDate(rs.getString("updateDate")));
				return board;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt, rs);
		}
		return null;
	}
	
	public int updateReadCount(int num) {
		
		String SQL = "UPDATE board SET readCount = readCount+1 WHERE num=?";
		Connection conn = DBManager.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
		
			int result = pstmt.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
		
		return -1;
	}
	
	
		
	public int update(BoardUpdateDTO bDto) {
		
		String SQL = "UPDATE board SET title = ?, content = ? WHERE num = ?";
		Connection conn = DBManager.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString(1, bDto.getTitle());
			pstmt.setString(2, bDto.getContent());
			pstmt.setInt(3, bDto.getNum());
			
			int result = pstmt.executeUpdate();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		return -1;
	}
	
	public int delete(int num) {
		
		String SQL = "DELETE FROM board WHERE num =?";
		Connection conn = DBManager.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, num);
			
			int result = pstmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt);
		}
		
		return -1;
	}
	
	/*public Board findByID(String search) {
		Connection conn = DBManager.getConnection();

				
				final String SQL = "SELECT * FROM board WHERE title=?";
				Board board = new Board();
				try {
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, "%"+search+"%");
					rs = pstmt.executeQuery();
					
					if(rs.next()) {	
						board.setNum(rs.getInt("num"));
						board.setTitle(rs.getString("title"));
						board.setContent(rs.getString("content"));
						board.setUserID(rs.getString("userID"));
						board.setReadCount(rs.getInt("readCount"));
						board.setCreateDate(Myutil.StringToLocalDate(rs.getString("createDate")));
						board.setUpdateDate(Myutil.StringToLocalDate(rs.getString("updateDate")));
						
					}
					return board;
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					DBManager.close(conn, pstmt, rs);
				}
				return null;
			}*/
	public List<Board> searchboard(String option,String search) {
		Connection conn = DBManager.getConnection();
		String SQL = "SELECT num , title, userID, readCount, createDate, updateDate FROM board ";
				
		if(option.equals("num")) {
			SQL = SQL+"WHERE num like ?";
		}else if(option.equals("title")) {
			SQL = SQL+"WHERE title like ?";
		}else if(option.equals("userID")){
			SQL = SQL+"WHERE userID like ?";
		}else {
			
			SQL = SQL+"WHERE title like ? ";
		}
		
		System.out.println(SQL);
		List<Board> list = new ArrayList<Board>();
		try {
			pstmt = conn.prepareStatement(SQL);
			
			pstmt.setString( 1, "%"+search+"%" );
			rs = pstmt.executeQuery();
			
			while(rs.next()) {	
				Board board = new Board();
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setUserID(rs.getString("userID"));
				board.setReadCount(rs.getInt("readCount"));
				board.setCreateDate(Myutil.StringToLocalDate(rs.getString("createDate")));
				board.setUpdateDate(Myutil.StringToLocalDate(rs.getString("updateDate")));
				list.add(board);
			}
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(conn, pstmt,rs);
		}
		
		
		return null;
	}
	
}
