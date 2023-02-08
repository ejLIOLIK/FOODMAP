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
String checkID = (String)session.getAttribute("id");
if(checkID==null || checkID.equals("null")){
%><a href="/board/rightWrite_login?adress=<%=request.getParameter("adress")%>">글쓰기</a> 	
<%}
else{	
	%><a href="/board/write?adress=<%=request.getParameter("adress")%>">글쓰기</a> 
<%}%>
<a href="/board/list?adress=<%=request.getParameter("adress")%>">게시판</a> <br>
ID : <%@include file = "/loginInfo.jsp" %>

</body>
</html>