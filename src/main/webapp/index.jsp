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
ID: <%@include file = "loginInfo.jsp"%>
<hr>
<a href="/board/list">전체게시판</a> <br>
<hr>
지역별 <br>
<a href="/board/list?category=서울">서울</a> 
<a href="/board/list?category=인천">인천</a> 
<a href="/board/list?category=경기">경기</a> <br>
<a href="/board/list?category=강원">강원</a> 
<a href="/board/list?category=충북">충북</a> 
<a href="/board/list?category=충남">충남</a> 
<a href="/board/list?category=대전">대전</a> <br>
<a href="/board/list?category=전북">전북</a> 
<a href="/board/list?category=전남">전남</a> 
<a href="/board/list?category=광주">광주</a> <br>
<a href="/board/list?category=부산">부산</a> 
<a href="/board/list?category=울산">울산</a> 
<a href="/board/list?category=대구">대구</a> 
<a href="/board/list?category=경북">경북</a> 
<a href="/board/list?category=경남">경남</a> <br>
<a href="/board/list?category=제주">제주</a> <br>
<hr>

<% 
if(id==null || !request.isRequestedSessionIdValid()){
	%><a href="/login.jsp">로그인/회원가입</a><%
}
else{
	%><a href="/board/logout">로그아웃</a><%
}
%>
<br><br><br>developed by ejLIOLIK
</body>
</html>