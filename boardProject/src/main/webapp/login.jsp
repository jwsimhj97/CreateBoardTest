<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	
	<%@ include file="head.jsp" %>
	<title>main</title>
	<!-- header -->
	<%@ include file="header.jsp" %>

	<!-- container -->
	<div class="container contents">
		<div class="content">
			<div class="cont">
				<div class="loginBox">
					<div class="login_cont">
						<h3>로그인</h3>
						<%
							if (request.getParameter("error")!=null) {
								out.println("<div class='alert alert-danger'>");
								out.println("아이디와 비밀번호를 확인해 주세요");
								out.println("</div>");
							}
						%>
						<form class="form-signin login_form" action="login.do" method="post">
							<div class="form-group">
								<label for="id" class="title">아이디</label> 
								<input	type="text" class="form-control" placeholder="ID" name="id" value="${userVO.id}" autofocus>
							</div>
							<div class="form-group">
								<label for="pw" class="title">비번</label> 
								<input 	type="password" class="form-control" placeholder="Password" name="pw" value="${userVO.pw}" >
							</div>
							
							<div class="btnBox">
								<div class="btn_container">
									<button class="btn" type="submit">로그인</button>
									<button class="btn gray_btn" type="button" onclick="location.href='join.jsp'">회원가입</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- footer -->
	<%@ include file="footer.jsp" %>
</body>
</html>