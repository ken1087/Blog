package cos.com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cos.com.domain.Member;
import cos.com.dto.MemberDTO;
import cos.com.utils.DBManager;


public class MemberDAO {
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int save(Member member) {
		final String SQL = "INSERT INTO member(num, userID, userPassword, userEmail, userAddress, userPhone, userGender, userState, createDate, updateDate) VALUES(member_seq.nextval, ?, ?, ?, ?, ?,?, ?, ?, ?)";
		Connection conn = DBManager.getConnection();
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getUserID());
			pstmt.setString(2, member.getUserPassword());
			pstmt.setString(3, member.getUserEmail());
			pstmt.setString(4, member.getUserAddress());
			pstmt.setString(5, member.getUserPhone());
			pstmt.setString(6, member.getUserGender());
			pstmt.setInt(7, member.getUserState());
			pstmt.setString(8, member.getCreateDate().toString());
			pstmt.setString(9, member.getUpdateDate().toString());
			
			//result = 0 fail, result = 1 success
			int result = pstmt.executeUpdate(); //트랜잭션 commit가지고 있다.
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//함수호출
			DBManager.close(conn, pstmt);
		}
		//서버 오류가 난거에요.
		return -1;
	}
	public int findByUserIDAndUserPassword(String userID, String userPassword) {
		
		final String SQL = "SELECT count(*) FROM member WHERE userID =? AND userPassword =?";
		Connection conn = DBManager.getConnection();
		
		try {
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			pstmt.setString(2, userPassword);
			rs = pstmt.executeQuery(); //rs는 첫번째 커서를 가리킨다.
			
			if(rs.next()) { // true 인지 false인지
				int result = rs.getInt(1); //count(*) 1이면 정상 0이면 미인증
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}	
		return -1; //서버 에러
	}
	
	public int findByUserID(String userID) {
		
		final String SQL = "SELECT count(*) FROM member WHERE userID =?";
		Connection conn = DBManager.getConnection();
		
		try {
			
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //rs는 첫번째 커서를 가리킨다.
			
			if(rs.next()) { // true 인지 false인지
				int result = rs.getInt(1); //count(*) 1이면 정상 0이면 미인증
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt, rs);
		}	
		return -1; //서버 에러
	}
	
	public Member findMy(String userID) {
		final String SQL = "SELECT* FROM member WHERE userID =?";
		Connection conn = DBManager.getConnection();
		
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			Member member = new Member();
			if(rs.next()) {
				
				member.setNum(rs.getInt("num"));
				member.setUserID(rs.getString("userID"));
				member.setUserPassword(rs.getString("userPassword"));
				member.setUserEmail(rs.getString("userEmail"));
				member.setUserAddress(rs.getString("userAddress"));
				member.setUserPhone(rs.getString("userPhone"));
				member.setUserGender(rs.getString("userGender"));
				member.setUserState(rs.getInt("userState"));
				
			}
			return member;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int memberUpdate(MemberDTO mdto) {
		final String SQL = "UPDATE member SET  userEmail =?, userPhone =?, userAddress = ? WHERE userID=?";
		Connection conn = DBManager.getConnection();
			try {
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, mdto.getUserEmail());
				pstmt.setString(2, mdto.getUserPhone());
				pstmt.setString(3, mdto.getUserAddress());
				pstmt.setString(4, mdto.getUserID());
				
			
				
				
				int result = pstmt.executeUpdate();
				return result;
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				DBManager.close(conn, pstmt);
			}
			return -1;
		}
	}

