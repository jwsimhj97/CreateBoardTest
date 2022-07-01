<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


	<%@ include file="head.jsp" %>
	<title>회원가입</title>
	<!-- header -->
	<%@ include file="header.jsp" %>

	<!-- container -->
	<div class="container contents">
		<div class="content">
			<div class="cont">
				<!-- 회원가입 s -->
				<!-- 아이디, 비밀번호, 이름 -->
				<div class="loginBox">
					<div class="login_cont">
						<h3>회원가입</h3>
						<%
							if (request.getParameter("error")!=null) {
								out.println("<div class='alert alert-danger'>");
								out.println("아이디와 비밀번호를 확인해 주세요");
								out.println("</div>");
							}
						%>
					<form class="login_form" action="join.do" method="post">
							<div class="form-group">
								<label for="name" class="title">이름</label> 
								<input 	type="text" class="form-control" placeholder="이름을 입력해주세요." name="name" value="${userVO.name}" >
							</div>
							<div class="form-group">
								<label for="id" class="title">아이디</label> 
								<input	type="text" class="form-control" placeholder="아이디를 입력해주세요." name="id" value="${userVO.id}" autofocus>
							</div>
							<div class="form-group">
								<label for="pw" class="title">비밀번호</label> 
								<input 	type="password" class="form-control" placeholder="비밀번호를 입력해주세요." name="pw" value="${userVO.pw}" >
							</div>
							
							
							<div class="btnBox">
								<div class="btn_container">
									<button class="btn" type="submit">가입하기</button>
									<button class="btn gray_btn" type="button" onclick="location.href='index.jsp'">취소</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<!-- 회원가입 e -->
			</div>
		</div>
	</div>
	
	<!-- footer -->
	<%@ include file="footer.jsp" %>
</body>
</html>