<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="net.board.db.*" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%
	List boardList=(List)request.getAttribute("boardlist");
	int listcount=((Integer)request.getAttribute("listcount")).intValue();
	int nowpage=((Integer)request.getAttribute("page")).intValue();
	int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
	int startpage=((Integer)request.getAttribute("startpage")).intValue();
	int endpage=((Integer)request.getAttribute("endpage")).intValue();
%> --%>
<% request.setCharacterEncoding("UTF-8"); %>
<html>
<head>
	<title>MVC 게시판</title>
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
	<style>
    .page-navigation {
        margin: 20px 0;
        text-align: center;
        
    }
    
    a {
    	color:inherit;
    	text-decoration : none;
    }
    a:hover{
    	color:gray;
    	text-decoration: underline;
    	cursor:pointer;
    }
    
    * {
    	font-family : "Gowun Batang";
    	text-align : center;
    }
</style>

</head>
<body>
<div class="container mt-4">
    <h3 class="mb-4" style="font-weight : bold;">📑 게시판 목록</h3>
    <hr>
    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead class="table-light">
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>날짜</th>
                    <th>조회수</th>
                </tr>
            </thead>
            <tbody><%-- 
                <% for (int i = 0; i < boardList.size(); i++) {
                    BoardBean bl = (BoardBean) boardList.get(i);
                %> --%>
                <c:forEach var="bl" items="${boardlist}">
                <tr>
                    <td>${bl.BOARD_NUM}</td>
                    <td class="text-start">
                    <c:if test="${bl.BOARD_RE_LEV != 0}">
                   		<c:forEach begin="0" end="${bl.BOARD_RE_LEV * 2}" var="a">
                       	&nbsp;
                   		</c:forEach>
               		</c:if>
					<%-- <%if(bl.getBOARD_RE_LEV()!=0){ %>
						<%for(int a=0;a<=bl.getBOARD_RE_LEV()*2;a++){ %>
						&nbsp;
						<%} %>
						
					<%}else{ %>
						
					<%} %> --%>
					<a href="./BoardDetailAction.bo?num=${bl.BOARD_NUM}">
						<%-- <%=bl.getBOARD_SUBJECT()%> --%> ${bl.BOARD_SUBJECT}
					</a></td>
					<td><%-- <%= bl.getBOARD_NAME() %> --%>${bl.BOARD_NAME}</td>
                    <td><%-- <%= bl.getBOARD_DATE() %> --%>${bl.BOARD_DATE}</td>
                    <td><%-- <%= bl.getBOARD_READCOUNT() %> --%>${bl.BOARD_READCOUNT}</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="page-navigation">
       <%--  <% if (nowpage > 1) { %>
        <a href="./BoardList.bo?page=<%= nowpage - 1 %>">
        <span>이전</span></a>
        <% } %>
        <% for (int a = startpage; a <= endpage; a++) { %>
        <% if (a == nowpage) { %>
        <span >[<%= a %>]</span>
        <% } else { %>
        <a href="./BoardList.bo?page=<%= a %>" ><span>[<%= a %>]</span></a>
        <% } %>
        <% } %>
        <% if (nowpage < maxpage) { %>
        <a href="./BoardList.bo?page=<%= nowpage + 1 %>"><span>다음</span></a>
        <% } %> --%>
          <c:if test="${page > 1}">
        <a href="./BoardList.bo?page=${page - 1}">
            <span>이전</span>
        </a>
    </c:if>
    <c:forEach var="a" begin="${startpage}" end="${endpage}">
        <c:choose>
            <c:when test="${a == page}">
                <span>[${a}]</span>
            </c:when>
            <c:otherwise>
                <a href="./BoardList.bo?page=${a}"><span>[${a}]</span></a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${page < maxpage}">
        <a href="./BoardList.bo?page=${page + 1}">
            <span>다음</span>
        </a>
    </c:if>
    </div>
    <div class="text-end mb-4">
    	<a href="./Main.lo" class="btn btn-warning">메인으로</a>
        <a href="./BoardWrite.bo" class="btn btn-success">글쓰기</a>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

</body>
</html>