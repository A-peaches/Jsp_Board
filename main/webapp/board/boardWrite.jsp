<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<% request.setCharacterEncoding("UTF-8"); %>   
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
<meta charset="UTF-8">
<link rel="styleSheet" type="text/css" href="../../CSS.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>글작성</title>
<style>
	.box {
		width : 80%;
		margin : auto;
	}
	
	#textarea {
	  height: 350px; /* 높이 설정 */
	  padding: 10px; /* 여백 설정 */
	  margin-left : 10%;
	}
	.form-label {
        display: inline-block;
        width: 10%;
        font-weight: bold;
    }
    
    .form-input {
        width: 85%;
        padding: 10px;
        margin-top: 5px;
        margin-bottom: 5px;
        border: 2px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
        transition: all 0.3s;
    }
    
    * {
    	font-family : "Gowun Batang";
    	text-align : center;
    }
</style>
<script>
	function prevPage() {
		window.history.go(-1);
	}
</script>
</head>
<body>
	<br><h3 style="font-weight:bold">✏️게시글 작성</h3><hr>
	<div class="box" >
	<form action="./BoardAddAction.bo" method="post" 
	enctype="multipart/form-data" name="boardform"
	style="text-align:left;">
		<div style='margin:50px 0px 10px 0px; text-align:left;' >
		<span class="form-label">작성자 :</span> 
		<input type="text" name="BOARD_NAME" class="form-input" placeholder="닉네임을 입력해주세요."
		style="width:25%"/>
		<input type="password" name="BOARD_PASS" class="form-input" placeholder="비밀번호를 작성해주세요."
		style="width:25%"/>
		<sub style="font-size:12pt">⚠️비밀번호를 분실할 경우 게시글에 대한 수정/삭제가 불가능합니다!</sub>
		</div>
		<span style="text-align:left;">
		<span class="form-label">제목 :</span> 
		<input type="text" name="BOARD_SUBJECT" class="form-input" placeholder="제목을 작성해주세요.">
		</span><br>
		<div style="display:flex; flex-direction:column;">
		  <span class="form-label" style="align-self:flex-start;">내용 :</span>
		  <textarea name="BOARD_CONTENT" id="textarea" class="form-input" placeholder="내용을 작성해주세요."></textarea>
		</div>
		<br>
		<span style="text-align:left;">
		<span class="form-label">파일 :</span> 
		<input type="file" name="BOARD_FILE" />
		</span><br>
		<div style="margin-top:10px;">
		<input type="button" name="prev" class="btn btn-secondary" onclick="prevPage()" value="이전"/> 
		<input type="submit" name="wirteBtn" class="btn btn-success"value="작성"/> 
		<input type="reset" name="reset" class="btn btn-danger" value="다시입력"/> 
		</div>
	</form>
	</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>