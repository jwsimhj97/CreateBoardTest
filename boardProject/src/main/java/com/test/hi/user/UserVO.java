package com.test.hi.user;

import java.util.Date;

public class UserVO {
	private String id;
	private String pw;
	private String name;
	private int role;
	
	private boolean autoLogin; //����ڰ� �α����� �Ҷ� �ڵ��α��� üũ�ڽ��� Ŭ���ߴ��� ���ߴ����� ���� ����
	private String sessionId;
	private Date limitDate;
	
	

	@Override
	public String toString() {
		return "UserVO [id=" + id + ", pw=" + pw + ", name=" + name + ", role=" + role + ", autoLogin=" + autoLogin + ", sessionId=" + sessionId + ", limitDate=" + limitDate + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}

	

	public boolean isAutoLogin() {
		return autoLogin;
	}

	public void setAutoLogin(boolean autoLogin) {
		this.autoLogin = autoLogin;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	

	public Date getLimitDate() {
		return limitDate;
	}

	public void setLimitDate(Date limitDate) {
		this.limitDate = limitDate;
	}
}
