<%@ page import="DAO.DAOcrud" %>
<%@ page import="DB.DB" %>
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

<form action ="/board/edit_proc" method="post" enctype="multipart/form-data">
번호 / <%=DB.dto.num%> <input type="hidden" name="editNum" value="<%=DB.dto.num%>">
상호명: <input type="text" name="title" value="<%=DB.dto.title%>"> <br>
작성자: <%=DB.dto.id%> <br>
내용: <textarea name="text"><%=DB.dto.text%></textarea> <br>
<%-- 주소: <input type="text" name="adress_select" value="<%=DB.dto.adress%>"> <br> --%>
주소: <select name="adress_select">
	<option value="서울">서울</option>
	<option value="인천">인천</option>
	<option value="경기">경기</option>
	<option value="제주">제주</option>
	<option value="강원">강원</option>
	<option value="전북">전북</option>
	<option value="전남">전남</option>
	<option value="충북">충북</option>
	<option value="충남">충남</option>
	<option value="경북">경북</option>
	<option value="경남">경남</option>
	<option value="경남">대구</option>
	<option value="부산">부산</option>
	<option value="울산">울산</option>
	<option value="광주">광주</option>
	<option value="대전">대전</option>
 </select> <br>
연락처: <input type="text" name="tel" value="<%=DB.dto.tel%>"> <br>
<input type="hidden" name="category" value="<%=request.getParameter("category")%>">
<hr> 
<%
if(DB.dto.img==null || DB.dto.img.equals("null")){
	%><img src="\upload\no.jpg" alt="img" width="200"><%
}
else{
	%><img src="\upload\<%=DB.dto.img%>" alt="img" width="200"><%
}
%>
<br> 
<input type="file" name="fileName"> <hr>
<input type="hidden" name="oldFileName" value="<%=DB.dto.img%>">
<input type="submit" value="수정">
</form>

<br><a href="/board/read?num=<%=DB.dto.num%>">뒤로</a>

</body>
</html>