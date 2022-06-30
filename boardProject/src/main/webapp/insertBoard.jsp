<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


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
			      <input type="text" class="form-control" name="name" value="${UserInfo }" readonly>      
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
					<button class="btn" id="conComplete" type="submit">글 작성하기</button>
					<button class="btn" id="conList" type="button">글목록</button>
				</div>
			 </form>
		</div>
		 
	</div>
	
	<!-- footer -->
	<%@ include file="../footer.jsp" %>
</body>
</html>