<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
String UserInfo;

//Session을 받을때는 값이 null로 올때를 생각해서 조건문을 사용한다.
if (session.getAttribute("UserInfo") != null) {
//세션의 값을 가져오기
UserInfo = (String)session.getAttribute("UserInfo");
System.out.println(UserInfo);
}
%>

</head>
<body>
	<!-- header -->
	<header>
		<div class="container">
			<div class="logo"><a href="/">LOGO</a></div>
			<div class="hd_right">
				<div class="logBox">
					<!-- <div class="welcome_message"><span>홍길동</span>님 환영합니다!</div> -->
					<div class="log_state">
						<c:if test="${UserInfo == null }">
							<a href="login.jsp">로그인</a>
						</c:if>
						<c:if test="${UserInfo != null }">
							<p class="welcome_text">${UserInfo }님 환영합니다!</p>
							<a href="logout.do">로그아웃</a>
						</c:if>
					</div>
				</div>
				
				<div class="menuBox">
					<ul>
						<li><a href="#">메뉴1</a></li>
						<li><a href="getBoardList.do">게시판</a></li>
						<li><a href="#">메뉴3</a></li>
						<li><a href="#">메뉴4</a></li>
					</ul>
				</div>
			</div>
		</div>
	</header>