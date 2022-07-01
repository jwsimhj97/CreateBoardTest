<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*,com.test.hi.board.BoardVO" %>
<%@ page import="java.util.*,com.test.hi.user.UserVO" %>

	<%@ include file="head.jsp" %>
	<title>게시판 작성하기</title>
	<!-- header -->
	<%@ include file="header.jsp" %>

	<!-- container -->
	<div class="container contents">
	
		<div class="commonBoardBox">
			<form action="insertBoard.do" method="post" enctype="multipart/form-data">
			    <div class="inputBox">
			      <div class="title">
			        <span class="tit">제목</span>
			      </div>
			      <input type="text" class="form-control" name="title" placeholder="제목을 입력하세요." required>      
			    </div>
			    <div class="inputBox">
			      <div class="title">
			        <span class="tit">작성자</span>
			      </div>
			      <input type="text" class="form-control" name="user_name" value="${UserInfo }" readonly>      
			    </div>
			    <div class="inputBox">
			      <div class="title">
			        <span class="tit">파일선택</span>
			      </div>
			       <input type="file" class="form-control" name="file_name">      
			    </div>
			    <div class="inputBox">
			      <div class="title">
			        <span class="tit">내용</span>
			      </div>
			      <textarea class="form-control" rows="10" id="comment" name="content"></textarea>      
			    </div>
			    
			    
				
				<div class="btnBox">
					<div class="btn_container">
						<button class="btn" id="conComplete" type="submit">작성완료</button>
						<button class="btn gray_btn" id="conList" type="button">목록가기</button>
					</div>
				</div>
			 </form>
		</div>
		 
	</div>
	
	<!-- footer -->
	<%@ include file="../footer.jsp" %>
</body>
</html>