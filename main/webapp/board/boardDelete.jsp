<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.board.db.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	int num = Integer.parseInt(request.getParameter("num"));
%>  
<title>MVC 게시판</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<style>
    * {
    	font-family : "Gowun Batang";
    	text-align : center;
    }
</style>
<script>
	function deleteConfirm() {
		return confirm("정말로 삭제하시겠습니까?");
	}
</script>
</head>
<body>
<form name="deleteForm" action="./BoardDeleteAction.bo?num=<%=num %>" method="post"
	onsubmit = "return deleteConfirm()"/>
 <div class="card text-center mt-5" style="margin:auto; width:30%">
  <div class="card-header">
    게시글 비밀번호를 입력해주세요.
  </div>
  <div class="card-body my-3">
   	<span>비밀번호 : </span><input type="password" class="form-control" name="BOARD_PASS"/>
  </div>
  <div class="card-footer text-body-secondary">
    <input type="submit" class="btn btn-danger" value="삭제"/>
    <input type="button" onclick="window.history.go(-1)" class="btn btn-secondary"
    value="돌아가기">
  </div>
</div>

</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>