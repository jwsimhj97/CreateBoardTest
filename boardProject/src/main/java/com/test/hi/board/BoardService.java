package com.test.hi.board;

import java.util.List;

public interface BoardService {
	//�۵��
	void insertBoard(BoardVO vo);
	
	//�ۼ���
	void updateBoard(BoardVO vo);
	
	//�ۻ���
	void deleteBoard(BoardVO vo);
	
	//�ۻ� ��ȸ
	BoardVO getBoard(BoardVO vo);
	
	//�۸�� ��ȸ
	List<BoardVO> getBoardList(BoardVO vo);
	
}
