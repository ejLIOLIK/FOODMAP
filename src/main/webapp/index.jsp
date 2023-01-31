<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
String id = (String)request.getAttribute("id");
if(id==null || id.equals("null")){
	%>id : 손님<%
}
else{
session.setAttribute("id", id);
session.setMaxInactiveInterval(5*60);
%>
id : <%=request.getAttribute("id") %><% 
}%>
<br>

<a href="/board/list">자유게시판</a> <br>
<a href="/login.jsp">로그인/회원가입</a>

<br><br><br>developed by ejLIOLIK
</body>
</html>