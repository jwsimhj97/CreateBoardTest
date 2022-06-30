$(document).ready(function(){	
	$("#conWrite").click(function(){
		location.href = "insertBoard.jsp";
	});
	
	$("#conDel").click(function(){
		let con_test = confirm("삭제하시겠습니까?");
		if(con_test == true){
			let v = document.fm.num.value;
			location.href = "deleteBoard.do?num="+v;
		}
		else if(con_test == false){
		  	return false;
		}
	});
	
	$("#conList").click(function(){
		location.href = "getBoardList.do";
		console.log("HIhihi");
	});
});

function selTr(val){
	location.href = "getBoard.do?num="+val;
}