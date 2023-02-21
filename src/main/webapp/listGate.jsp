<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<!-- post > get -->
<%
response.sendRedirect("/board/list?category="+request.getAttribute("category"));
%>

</body>
</html>