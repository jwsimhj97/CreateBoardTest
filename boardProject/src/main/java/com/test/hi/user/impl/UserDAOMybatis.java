package com.test.hi.user.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.hi.user.UserVO;

@Repository("userDAO")
public class UserDAOMybatis {

	@Autowired
	private SqlSessionTemplate mybatis;

	public UserVO getUser(UserVO vo) {
		System.out.println("===> JDBC로 getUser() 기능 처리");
		return (UserVO) mybatis.selectOne("UserDAO.getUser", vo);
	}
	
	public List<UserVO> getUserList(UserVO vo) {
			System.out.println("===> JDBC로 getUserList() 기능 처리");
			return mybatis.selectList("UserDAO.getUserList", vo);		
	}
	
	
	public int insertUser(UserVO vo) {
		System.out.println("===> JDBC로 insertUser() 기능 처리");
		return mybatis.insert("UserDAO.insertUser", vo);
	}
	
	public int updateUser(UserVO vo) {
		System.out.println("===> JDBC로 updateUser() 기능 처리");
		return mybatis.update("UserDAO.updateUser", vo);
	}
	
	public int deleteUser(UserVO vo) {
		System.out.println("===> JDBC로 deleteUser() 기능 처리");
		return mybatis.delete("UserDAO.deleteUser", vo);
	}
	
	
	
	//autoLogin(map);
	public void autoLogin(Map<String, Object> map){
		System.out.println("Mybatis >> autoLogin");
		mybatis.update("UserDAO.autoLogin", map);
	}
	
	public UserVO selectSession(String sessionId) {
		System.out.println("Mybatis >> selectSession");
		return (UserVO) mybatis.selectList("UserDAO.selectSession", sessionId);
	}

	public void autoLogin(String sessionId, Date limitDate, String id, String name) {
		System.out.println("Mybatis >> autoLogin(limitDate)");
		mybatis.update("UserDAO.autoLogin");
	}
}
