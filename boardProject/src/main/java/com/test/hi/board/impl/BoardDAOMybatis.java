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
	
	//�۵��
		public void insertBoard(BoardVO vo) {
			System.out.println("===>JDBC�� insertBoard() ���ó��");
			mybatis.insert("BoardDAO.insertBoard", vo);
		}
		
		//�ۼ���
		public void updateBoard(BoardVO vo) {
			System.out.println("===>JDBC�� updateBoard() ���ó��");
			mybatis.update("BoardDAO.updateBoard", vo);
		}
		
		//�ۻ���
		public void deleteBoard(BoardVO vo) {
			System.out.println("===>JDBC�� deleteBoard() ���ó��");
			mybatis.delete("BoardDAO.deleteBoard", vo);
		}
		
		//�ۻ� ��ȸ
		public BoardVO getBoard(BoardVO vo) {
			System.out.println("===>JDBC�� getBoard() ���ó��");
			mybatis.update("BoardDAO.updateCnt", vo);
			return (BoardVO) mybatis.selectOne("BoardDAO.getBoard", vo);
		}
		
		//�۸�� ��ȸ
		public List<BoardVO> getBoardList(BoardVO vo) {
			System.out.println("===>JDBC�� getBoardList() ");
			
			return mybatis.selectList("BoardDAO.getBoardList", vo);
		}

}
