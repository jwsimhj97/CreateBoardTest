<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.test.hi.board.BoardVO" %>
	
	<%@ include file="head.jsp" %>
	
<%	String sts = ""; %>
<c:if test="${name ne board.user_name }">
	<% 
		sts = "disabled"; 
		System.out.println(sts);
	%>
</c:if>

	<title>게시판 상세보기</title>
	<!-- header -->
	<%@ include file="header.jsp" %>

	<!-- container -->
	<div class="container contents">
		<div class="commonBoardBox">
			<form action="updateBoard.do" method="post" enctype="multipart/form-data">
				<input type="hidden" name="num" value="${board.num }"/>
				<div class="inputBox">
			      <div class="title">
			        <span class="tit">작성자</span>
			      </div>
			      <input type="text" class="form-control" name="title" value="${board.user_name }" <%=sts %> readonly>      
			    </div>
				<div class="inputBox">
			      <div class="title">
			        <span class="tit">제목</span>
			      </div>
			      <input type="text" class="form-control" name="title" value="${board.title }" <%=sts %>>      
			    </div>
			    <div class="inputBox">
			      <div class="title">
			        <span class="tit">내용</span>
			      </div>
			      <textarea class="form-control" rows="10" id="comment" name="content" <%=sts %>>${board.content }</textarea>      
			    </div>
				<div class="inputBox">
			      <div class="title">
			        <span class="tit">파일</span>
			      </div>
			      <div type="text" class="form-control" name="file_name" value="${board.file_name }" <%=sts %>></div>
			      <c:choose>
			      	<c:when test="${board.file_name eq ''}">파일없음</c:when>
			      	<c:otherwise><button type="button" onclick="downloadFile('${board.file_name}')">[파일다운]</button></c:otherwise>
			      </c:choose>
			    </div>
				<div class="inputBox">
			      <div class="title">
			        <span class="tit">등록일</span>
			      </div>
			      <input type="text" class="form-control" name="date" value="${board.date }" <%=sts %> readonly>      
			    </div>
			    
			    
			    
				<div class="btnBox">
					<div class="btn_container">
						<button class="btn" type="submit" <%=sts %>>글 수정</button>
						<button class="btn gray_btn" id="conWrite" type="button" <%=sts %>>글쓰기</button>
						<button class="btn deepGray_btn" id="conDel" type="button" <%=sts %>>글삭제</button>
						<button class="btn purpGray_btn" id="conList" type="button">글목록</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<!-- footer -->
	<%@ include file="footer.jsp" %>
	
	<script type="text/javascript">
	function downloadFile(file_name){
		window.location='download.do?file_name='+file_name;
	}
	
	
	</script>
</body>
</html>