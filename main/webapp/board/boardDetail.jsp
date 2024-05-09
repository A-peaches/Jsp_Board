<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.board.db.*" %>
<% request.setCharacterEncoding("UTF-8"); %>
<%
	BoardBean board = (BoardBean)request.getAttribute("boarddata");
%>  
<head>
<meta charset="UTF-8">
<link rel="styleSheet" type="text/css" href="../../CSS.css"/>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<title>게시글 상세보기</title>
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
    
    span, body, pre{
    	font-size : 15pt;
    	font-family : "Gowun Batang"
    }
    
    
    div {
    	margin : 15px 0px 15px 0px;
    }
    
    .dotted {
        border: none;
        border-top: 3px dotted gray;
        margin: auto;
        width: 95%;
        
    }
    
    * {
    	font-family : "Gowun Batang";
    	text-align : center;
    }
</style>
</head>
<body>
	<br><h3 style="font-weight:bold">🔍게시글 조회</h3><hr>
	<div class="box" >
	<form style="text-align:left;">
		<br>
		<div style='text-align:left;' >
		<span class="form-label">제목 :</span> 
		<span><%= board.getBOARD_SUBJECT() %></span>
		</div>
		<span style="text-align:left;">
		<span class="form-label">작성자 :</span> 
		<span><%= board.getBOARD_NAME() %></span>
		</span>
		<br><br><hr class="dotted"><br>
		<div style="display:flex; flex-direction:column;">
		  <span class="form-label">내용 :</span>
		  <span>
		  <pre style="text-align : left; margin-left : 50px">
		  <%= board.getBOARD_CONTENT() %>
		  </pre>
		  </span>
		</div>
		<br>
		<span style="text-align:left;">
		<span class="form-label">첨부파일 :</span> 
		<%if(!(board.getBOARD_FILE()==null)){ %>
		<a href="./boardupload/<%=board.getBOARD_FILE()%>">
			<%=board.getBOARD_FILE() %>
		</a>
		<%} else { %>
		첨부파일 없음
		<%}%>
		</span><br>
		<div style="margin-top:10px;">
		<a href="./BoardList.bo" name="prev" class="btn btn-secondary">목록</a> 
		<a class="btn btn-warning">답변</a>
		<a class="btn btn-success">수정</a>
		<a href="./BoardDelete.bo?num=<%=board.getBOARD_NUM() %>" class="btn btn-danger">삭제</a> 
		</div>
	</form>
	</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>