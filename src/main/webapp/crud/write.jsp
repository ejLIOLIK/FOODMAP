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

<!-- DB.dto.title, DB.dto.id, DB.dto.text, DB.dto.point, DB.dto.adress, DB.dto.tel -->
<form action ="/board/write" method="get">
상호명: <input type="text" name="title"> <br>
작성자: <input type="text" name="id"> <br>
내용: <textarea name="text"></textarea> <br>
<!-- 주소: <input type="text" name="adress"> <br> -->
주소: <select name="adress">
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
	<option value="부산">부산</option>
	<option value="울산">울산</option>
	<option value="광주">광주</option>
	<option value="대전">대전</option>
 </select> <br>
연락처: <input type="text" name="tel"> <br>
<input type="submit" value="등록">
</form>

<br><a href="/board/list">뒤로</a>

</body>
</html>