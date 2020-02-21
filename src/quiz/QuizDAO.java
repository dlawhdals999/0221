package quiz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import board.BoardMain;
import board.DBUtil;
import login.MemberInfoVO;

public class QuizDAO {

	
//  문제의 idx를 얻어와서 리스트에 저장하는 메소드
	public static ArrayList<Integer> readIdx() {
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		try {
			Connection conn = DBUtil.getMySQLConnection();
			String sql = "select idx from quiz"; 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt("idx"));
			}
			DBUtil.close(rs);
			DBUtil.close(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
//  QuizVO클래스 객체를 읽어오는 메소드	
	public static QuizVO readQuiz(int count) {
		QuizVO vo = new QuizVO();
		try {
			Connection conn = DBUtil.getMySQLConnection();
			String sql = "select * from quiz where idx = ?"; 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, count);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			vo.setQuiz(rs.getString("quiz"));
			vo.setAnswer(rs.getString("answer"));
			vo.setwrong1(rs.getString("wrong1"));
			vo.setwrong2(rs.getString("wrong2"));
			vo.setwrong3(rs.getString("wrong3"));
			vo.setquizPassword(rs.getString("quizPassword"));
			vo.setIdx(rs.getInt("idx"));
			vo.setWriteDate(rs.getTimestamp("writedate"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vo;
	}

//	메인보드에 퀴즈객체배열 전송하기
	public static ArrayList<QuizVO> setquiz() {
		ArrayList<QuizVO> quizvo = null;
		try {
			Connection conn = DBUtil.getMySQLConnection();
			String sql = "select * from quiz"; 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			quizvo = new ArrayList<QuizVO>();
			while(rs.next()) {
				QuizVO vo = new QuizVO();
				vo.setQuiz(rs.getString("quiz"));
				vo.setAnswer(rs.getString("answer"));
				vo.setwrong1(rs.getString("wrong1"));
				vo.setwrong2(rs.getString("wrong2"));
				vo.setwrong3(rs.getString("wrong3"));
				vo.setquizPassword(rs.getString("quizPassword"));
				vo.setIdx(rs.getInt("idx"));
				vo.setWriteDate(rs.getTimestamp("writedate"));
				quizvo.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quizvo;
	}

	public static void addScore(int an, int wr) {
		
		try {
			MemberInfoVO vo = BoardMain.getMvo();
			Connection conn = DBUtil.getMySQLConnection();
			String sql = "UPDATE memberinfo SET answerCount=? , wrongCount=? WHERE userNo=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, an);
			pstmt.setInt(2, wr);
			pstmt.setInt(3, vo.getUserNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}