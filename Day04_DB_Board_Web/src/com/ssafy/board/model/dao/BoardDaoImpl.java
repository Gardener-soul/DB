package com.ssafy.board.model.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.board.model.dto.Board;
import com.ssafy.board.util.DBUtil;

// 싱글턴으로 만들어보자.
public class BoardDaoImpl implements BoardDao {

	//DBUtil 들고 와야겠다.
	private DBUtil util 	= DBUtil.getInstance();
	
	private static BoardDaoImpl instance = new BoardDaoImpl();
	private BoardDaoImpl() {
	}
	
	public static BoardDaoImpl getInstance() {
		return instance;
	}
	
	@Override
	public List<Board> selectAll() {
		List<Board> list = new ArrayList<>();
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		//2. 데이터베이스 연결
		try {
			conn = util.getConnection();
			//3. SQL 준비 및 실행
			stmt = conn.createStatement();
			// SQL (전체 게시글 가져와)
			String sql = "SELECT * FROM board";
			
			stmt.executeQuery(sql);
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Board board = new Board();
				//인덱스 값으로 가져 올 수 있음.
				board.setId(rs.getInt(1));
				board.setWriter(rs.getString(2));
				board.setTitle(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setViewCnt(rs.getInt(5));
				board.setRegDate(rs.getString(6));
			
				list.add(board);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(rs, stmt, conn);
		}
		// list : DB안에 들어있는 모든 게시글 정보
		return list;
	}

	@Override
	public Board selectOne(int id) {
		
		String sql = "SELECT * FROM board WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Board board = new Board();
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board.setId(rs.getInt(1));
				board.setWriter(rs.getString(2));
				board.setTitle(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setViewCnt(rs.getInt(5));
				board.setRegDate(rs.getString(6));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(conn, pstmt, rs);
		}
		
		return board;
	}

	@Override
	public void insertBoard(Board board) {
		String sql = "INSERT INTO board(title, writer, content) VALUES (?,?,?)";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = util.getConnection();
			//autocommit
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			
			int result = pstmt.executeUpdate();
			System.out.println(result);
			conn.commit();
		} catch (SQLException e) {
//			conn.rollback();
		} finally {
			util.close(pstmt,conn);
		}
		
	}

	@Override
	public void deleteBoard(int id) {
		String sql = "DELETE FROM board WHERE id =?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(pstmt,conn);
		}
	}

	@Override
	public void updateBoard(Board board) {
		String sql = "UPDATE board SET title=? , content=? WHERE id=?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  board.getTitle());
			pstmt.setString(2,  board.getContent());
			pstmt.setInt(3, board.getId());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			util.close(conn, pstmt);
		}
		
	}

	@Override
	public void updateViewCnt(int id) throws SQLException {
		String sql = "UPDATE board SET view_cnt = view_cnt+1 WHERE id = ?";
		
		Connection conn = null; 
		PreparedStatement pstmt = null;
		
		try {
			conn = util.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			pstmt.executeUpdate();
		} finally {
			util.close(conn,pstmt);
		}
	}
	
}
