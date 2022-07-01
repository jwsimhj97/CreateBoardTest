<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*,com.test.hi.board.BoardVO" %>
<%@ page import="java.util.*,com.test.hi.user.UserVO" %>


	<%@ include file="head.jsp" %>
	<title>게시판 목록</title>
	<!-- header -->
	<%@ include file="header.jsp" %>

	<!-- container -->
	<div class="container contents">
		<div class="content">		
			<div class="cont">
				<!-- 게시판 s -->
				<div class="boardBox">
					<div class="table">
						<div class="thead">
							<div class="tr">
								<div class="th w10"><p>No.</p></div>
								<div class="th w55"><p>제목</p></div>
								<div class="th w20"><p>작성자</p></div>
								<div class="th w15"><p>날짜</p></div>
							</div>
						</div>
						<div class="tbody">
							<c:forEach items="${boardList}" var="board">
								<div class="tr" onclick="selTr(${board.num})" style="cursor:pointer;">
									<div class="td w10"><p>${board.num}</p></div>
									<div class="td w55"><p>${board.title}</p></div>
									<div class="td w20"><p>${board.user_name}</p></div>
									<div class="td w15"><p>${board.date}</p></div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<!-- 게시판 e -->
				
				<div class="btnBox">
					<div class="btn_container">
						<button class="btn" onclick="location.href='insertBoard.jsp'">글 작성하기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- footer -->
	<%@ include file="../footer.jsp" %>
	
	<script>
		function pageFnc(np){
			let frm = document.fm;
			frm.nowPage.value = np;
			frm.action = "getBoardList.do";
			frm.method = "post";
			frm.submit();	
		}
	</script>
</body>
</html>