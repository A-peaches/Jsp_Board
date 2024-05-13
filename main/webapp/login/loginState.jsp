<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>   
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<meta charset="UTF-8">
<script>
	function loginCheck() {
   	 var loggedIn = <%= (session.getAttribute("user_id") != null) ? "true" : "false" %>;
    	if(!loggedIn) {
     	   alert("로그인이 필요합니다!");
     	   window.location.href = "Login.lo";
    	}
	}
</script>
<style>
	* {
		font-family : "Gowun Dodum";
		text-align : center;
	}
</style>
<title>loginState</title>
</head>
<body onload="loginCheck()">
	<div class="my-5 w-50 mx-auto">
    💕 <%=session.getAttribute("user_id") %> 💕 님 환영합니다 !!  | 
	<input type=button onclick="logout()" name="logout" value="Logout"
	class="btn btn-danger btn-sm mx-3"/> | 
	<input type=button onclick="window.location.href='./Main.lo'" name="Home" value="Home"
	class="btn btn-primary btn-sm mx-3"/> 
	<hr>
	<script type="text/javascript">
		function logout() {
			if(confirm("정말 로그아웃하시겠습니까?")) {
				window.location.href = "./login/logout.jsp";
			}
		}
	</script>
	</div>
</body>
</html>