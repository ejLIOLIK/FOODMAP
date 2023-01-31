<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%@include file="messageAlert.jsp" %>

<form action="/board/memjoin" method="post">
email: <input type="email" name="email" required="required"> <br>
id: <input type="text" name="id" required="required"> <br>
pw: <input type="password" name="pw1" required="required"> <br>
pw재입력: <input type="password" name="pw2" required="required"> <br>
<input type="submit" value="가입"> <br>
</form> <br>
<a href="/login.jsp">로그인</a> <br>
<a href="/index.jsp">처음으로</a> <br>


</body>
</html>