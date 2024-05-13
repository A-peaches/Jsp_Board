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
<title>main</title>
</head>
<body>
<jsp:include page="loginState.jsp"></jsp:include>

<p>📑 게시판은 비회원도 이용 가능합니다. </p>
<div class="row justify-content-center my-5">
<div class="card mx-3 py-3" style="width: 18rem;">
  <h1>📝</h1>
  <div class="card-body">
  	<a href="BoardList.bo" class="btn btn-success">게시판</a>
  </div>
</div>
<div class="card mx-3 py-3" style="width: 18rem;" >
  <h1>🤗</h1>
  <div class="card-body">
  	<a href="MyPage.lo" class="btn btn-success">마이페이지</a>
  </div>
</div>
<div class="card mx-3 py-3" style="width: 18rem;">
  <h1>🍪</h1>
  <div class="card-body">
  	<a href="MenuViewAction.ba" class="btn btn-success">쿠키 쇼핑</a>
  </div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
	
</body>
</html>