package com.test.hi.user;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {

	UserVO getUser(UserVO vo);
	
	List<UserVO> getUserList(UserVO vo);
	
	int insertUser(UserVO vo);
	
	int updateUser(UserVO vo);
	
	int deleteUser(UserVO vo);

	
	void autoLogin(Map<String,Object> map); //Ư�� ȸ���� ���Ǿ��̵�� ��Ű ��ȿ�Ⱓ�� ����
	void autoLogin(String sessionId, Date limitDate, String id, String name); //Ư�� ȸ���� ���Ǿ��̵�� ��Ű ��ȿ�Ⱓ�� ����
	UserVO selectSession(String sessionId);//���Ǿ��̵�� ȸ����ȸ

}
