<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.login.db.LoginBean" %>
<% request.setCharacterEncoding("UTF-8"); %>   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<style>
	a {
		font-family : "Gowun Dodum";
	}
</style>
<meta charset="UTF-8">
<title>main</title>
<% LoginBean userData = (LoginBean)request.getAttribute("loginData"); %>
</head>
<body>
<jsp:include page="loginState.jsp"></jsp:include>
<h3>👧🏻마이페이지👦🏻</h3><br>
<div class="d-flex justify-content-center" style="width:60%; margin:auto;">
<div class="card" style="width: 20rem;">
  <div class="card-body">
    <h5 class="card-title">나의 정보</h5>
    <p class="card-text">ID: <%= userData.getUser_id() %></p>
    <p class="card-text">PW: <%= userData.getUser_pw() %></p>
    <p class="card-text">Email: <%= userData.getUser_email() %></p>
    <p class="card-text">Name: <%= userData.getUser_name() %></p>
    <p class="card-text">Birth: <%= userData.getBirth() %></p>
    <p class="card-text">Hobby: <%= userData.getHobby() %></p>
    <p class="card-text">Info: <%= userData.getInfo() %></p>
  </div>
</div>
</div>
<div class="my-3">
<a href="./Main.lo" class="btn btn-sm btn-warning mx-3">메인으로</a>
<%if (userData.getIsadmin()) { %>
<a href="./MemberListAction.lo" class="btn btn-sm btn-success">회원 목록 조회</a> 
<%} %>

</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

</body>
</html>