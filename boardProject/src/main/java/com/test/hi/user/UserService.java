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

	
	void autoLogin(Map<String,Object> map); //특정 회원의 세션아이디와 쿠키 유효기간을 저장
	void autoLogin(String sessionId, Date limitDate, String id, String name); //특정 회원의 세션아이디와 쿠키 유효기간을 저장
	UserVO selectSession(String sessionId);//세션아이디로 회원조회

}
