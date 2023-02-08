<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="/homeButton.jsp" %>
<%@include file="messageAlert.jsp" %>

<form action="/board/login" method="post">
id: <input type="text" name="id" required="required"> <br>
pw: <input type="password" name="pw" required="required"> <br>
<input type="submit" value="로그인"> <br>
</form> <br>
<a href="/memjoin.jsp">회원가입</a> <br>
<a href="/index.jsp">처음으로</a> <br>

</body>
</html>