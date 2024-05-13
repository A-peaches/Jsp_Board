<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>logout</title>
</head>
<%
    session.invalidate();
    response.sendRedirect("../Login.lo");
%>
<body>

</body>
</html>