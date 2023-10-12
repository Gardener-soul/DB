package com.ssafy.test;

import java.sql.SQLException;

import com.ssafy.board.model.dao.BoardDao;
import com.ssafy.board.model.dao.BoardDaoImpl;
import com.ssafy.board.model.dto.Board;

public class Test {
	public static void main(String[] args) {
		BoardDao dao = BoardDaoImpl.getInstance();
		
//		for(int i = 0; i<10; i++) {
//			Board board = new Board("김은지"+i,"김은지"+i,"무빙재밌다"+i);
//			dao.insertBoard(board);
//		}
		
//		dao.insertBoard(board);
//		
		
		dao.deleteBoard(817);
		
//		try {
//			dao.updateViewCnt(7);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		System.out.println(dao.selectOne(10));
		
//		for(Board b : dao.selectAll()) {
//			System.out.println(b);
//		}
	}
} 