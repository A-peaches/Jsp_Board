<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.login.db.LoginBean" %>
<%@ page import="java.util.*" %>
<% request.setCharacterEncoding("UTF-8"); %>   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<style>
td {
	padding : 10px 10px 10px 10px;
}
</style>
<link rel="styleSheet" type="text/css" href="../../CSS.css"/>
<meta charset="UTF-8"">
<title>Member list</title>
</head>
<% 
  ArrayList memberList = (ArrayList)request.getAttribute("memberList"); 
  boolean isAdmin = (boolean)session.getAttribute("isadmin");
%>
 <script>
	
	function loginCheck() {
		var isAdmin = <%= isAdmin %>;
		if(!isAdmin) {
		  alert("관리자만 이용할 수 있습니다!");
		  window.location.href = "/Main.lo";
		} 
	}
	
 </script>
<body onload="loginCheck()">
<h3 class="fw-bold my-3">🪪 회원 목록</h3>

<hr>
<form method="post" >
	<table class="table table-hover table-striped w-50 mx-auto">
		<tr>
			<td>id</td>
			<td>pw</td>
			<td>name</td>
			<td>email</td>
			<td>birth</td>
			<td>hobby</td>
			<td>info</td>
			<td>삭제</td>
		</tr>
		<%if (memberList != null) { %> 
		<% for (int i =0 ; i<memberList.size(); i++) { 
			LoginBean lb = (LoginBean) memberList.get(i);
		%>
			<tr>
			<td><%= lb.getUser_id() %></td>
			<td><%= lb.getUser_pw() %></td>
			<td><%= lb.getUser_name() %></td>
			<td><%= lb.getUser_email() %></td>
			<td><%= lb.getBirth() %></td>
			<td><%= lb.getHobby() %></td>
			<td><%= lb.getInfo() %></td>
			<td><input type="button"  value="삭제" 
			onclick ="window.location.href='MemberDelete.lo?user_id=<%=lb.getUser_id() %>'"
			class="btn btn-sm btn-danger"/></td>
			</tr>
		<%} %>
		<% } else { %>
			<tr>
			<td colspan="5">회원이 존재하지 않습니다.</td>
			</tr>
		<% } %>
	</table>
	<br>
	<a href="./Main.lo" class="btn btn-sm btn-warning">메인으로</a>
	<a href="javascript:history.back()" class="btn btn-sm btn-secondary mx-3">이전</a>
</form>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

</body>
</html>