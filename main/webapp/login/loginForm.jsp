<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>   
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="CSS.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>

<meta charset="UTF-8">
<title>Login</title>

<script>
	function loginCheck() {
		var id = document.forms["loginForm"]["user_id"].value;
		var pw = document.forms["loginForm"]["user_pw"].value;
		if(id == "" || pw == "") {
			alert("ID와 PW를 모두 입력해주세요 !");
			return false;
		}
		return true;
	}
</script>
</head>

<body>
<h3 class="fw-bold my-3">🔐 로그인</h3>
<hr>
<form name="loginForm" method="post" action="MemberLoginAction.lo" onsubmit="return loginCheck()">
<div class="form-floating mb-3 col-md-3 mx-auto">
  <input type="text" class="form-control" name="user_id" placeholder="아이디를 입력해주세요.">
  <label for="floatingInput">ID</label>
</div>
<div class="form-floating mb-3 col-md-3 mx-auto">
  <input type="password" class="form-control" name="user_pw"
  id="floatingPassword" placeholder="비밀번호를 입력해주세요.">
  <label for="floatingPassword">Password</label>
</div>
<input type="submit" name="login" value="Login" class="btn btn-success btn-lg"/>
<p class="my-3">회원가입이 안되어있으신가요? <a href="./MemberJoinView.lo">회원가입</a></p>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
	
</body>
</form>
</html>