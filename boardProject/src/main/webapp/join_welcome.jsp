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
						<form class="login_form" action="join_welcome.do" method="post">
							<p>회원가입이 완료되었습니다!</p>
							<p>로그인 후에 서비스를 이용해보세요</p>
							
							
							<div class="btnBox">
								<div class="btn_container">
									<button class="btn" type="submit">로그인하기</button> 
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