package com.test.hi.board.impl;


import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.hi.board.BoardVO;

@Repository
public class BoardDAOMybatis {
	@Autowired
	private SqlSessionTemplate mybatis;
	
		//글등록
		public void insertBoard(BoardVO vo) {
			System.out.println("===>JDBC�� insertBoard() ���ó��");
			mybatis.insert("BoardDAO.insertBoard", vo);
		}
		
		//글수정
		public void updateBoard(BoardVO vo) {
			System.out.println("===>JDBC�� updateBoard() ���ó��");
			mybatis.update("BoardDAO.updateBoard", vo);
		}
		
		//글삭제
		public void deleteBoard(BoardVO vo) {
			System.out.println("===>JDBC�� deleteBoard() ���ó��");
			mybatis.delete("BoardDAO.deleteBoard", vo);
		}
		
		//글상세 조회
		public BoardVO getBoard(BoardVO vo) {
			System.out.println("===>JDBC�� getBoard() ���ó��");
			/* mybatis.update("BoardDAO.updateCnt", vo); */
			return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo);
		}
		
		//글목록 조회
		public List<BoardVO> getBoardList(BoardVO vo) {
			System.out.println("===>JDBC�� getBoardList() ");
			
			return mybatis.selectList("BoardDAO.getBoardList", vo);
		}

}
