package com.ssafy.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.model.dto.Board;

// 1. JDBC 드라이버 로드
// 2. 데이터베이스 연결
// 3. SQL 준비 및 실행
// 4. 데이터베이스 연결해제

public class JDBCTest {
	
	public JDBCTest() {
		//Class라고 하는 클래스가 가진 정적메소드 forName을 사용하게 되면 메모리에 미리 올려둠.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로딩 성공!!!");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		JDBCTest db = new JDBCTest();
		 
		for(Board b : db.selectAll()) {
			System.out.println(b);
		}
		
	}
	 
	public List<Board> selectAll(){
		List<Board> list = new ArrayList<>();
		
		//2. 데이터베이스 연결
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssafy_board?serverTimezone=UTC", "ssafy", "ssafy");
			
			//3. SQL 준비 및 실행
			Statement stmt = conn.createStatement();
			// SQL (전체 게시글 가져와)
			String sql = "SELECT * FROM board";
			
			stmt.executeQuery(sql);
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Board board = new Board();
//				board.setId(rs.getInt("id"));
//				board.setTitle(rs.getString("title"));
//				board.setWriter(rs.getString("writer"));
//				board.setContent(rs.getString("content"));
//				board.setRegDate(rs.getString("reg_date"));
//				board.setViewCnt(rs.getInt("view_cnt"));
				//인덱스 값으로 가져 올 수 있음.
				board.setId(rs.getInt(1));
				board.setWriter(rs.getString(2));
				board.setTitle(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setViewCnt(rs.getInt(5));
				board.setRegDate(rs.getString(6));
			
				list.add(board);
			}
			
			rs.close();
			stmt.close();
			conn.close();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
