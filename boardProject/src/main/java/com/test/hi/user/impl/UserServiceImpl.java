package com.test.hi.user.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.hi.user.UserService;
import com.test.hi.user.UserVO;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDAOMybatis userDAO;
	
	@Override
	public UserVO getUser(UserVO vo) {
		return userDAO.getUser(vo);
	}

	@Override
	public List<UserVO> getUserList(UserVO vo) {
		return userDAO.getUserList(vo);
	}

	@Override
	public int insertUser(UserVO vo) {
		return userDAO.insertUser(vo);
	}

	@Override
	public int updateUser(UserVO vo) {
		return userDAO.updateUser(vo);
	}

	@Override
	public int deleteUser(UserVO vo) {
		return userDAO.deleteUser(vo);
	}
	
	@Override
	public void autoLogin(String sessionId, Date limitDate, String id, String name) {
		Map<String,Object> map = new HashMap<String,Object>();
		System.out.println(sessionId);
		System.out.println(limitDate);
		System.out.println(id);
		System.out.println(name);
        map.put("sessionId",sessionId);
        map.put("limitDate",limitDate);
        map.put("id",id);
        map.put("name",name);
        System.out.println("map"+map);
        userDAO.autoLogin(map);
	}

	@Override
	public UserVO selectSession(String sessionId) {
		return userDAO.selectSession(sessionId);
	}

	@Override
	public void autoLogin(Map<String, Object> map) {
		userDAO.autoLogin(map);
	}

}
