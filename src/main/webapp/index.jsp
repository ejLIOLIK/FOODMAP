<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
HOME
<hr>
<% 
String id = (String)request.getAttribute("id");
if(id==null || id.equals("null")){
	%>id : 손님<%
}
else{%>
id : <%=id %><% 
}%>
<hr>
<a href="/board/list">전체게시판</a> <br>
<hr>
지역별 <br>
<a href="/board/list?adress=서울">서울</a> 
<a href="/board/list?adress=인천">인천</a> 
<a href="/board/list?adress=경기">경기</a> <br>
<a href="/board/list?adress=강원">강원</a> 
<a href="/board/list?adress=충북">충북</a> 
<a href="/board/list?adress=충남">충남</a> 
<a href="/board/list?adress=대전">대전</a> <br>
<a href="/board/list?adress=전북">전북</a> 
<a href="/board/list?adress=전남">전남</a> 
<a href="/board/list?adress=광주">광주</a> <br>
<a href="/board/list?adress=부산">부산</a> 
<a href="/board/list?adress=울산">울산</a> 
<a href="/board/list?adress=대구">대구</a> 
<a href="/board/list?adress=경북">경북</a> 
<a href="/board/list?adress=경남">경남</a> <br>
<a href="/board/list?adress=제주">제주</a> <br>
<hr>

<% 
if(id==null || id.equals("null")){
	%><a href="/login.jsp">로그인/회원가입</a><%
}
else{
	%><a href="/board/logout">로그아웃</a><%
}
%>
<br><br><br>developed by ejLIOLIK
</body>
</html>