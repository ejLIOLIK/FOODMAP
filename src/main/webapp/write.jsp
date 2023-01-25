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
<form action ="write" method="post">
상호명: <input type="text" name="title"> <br>
작성자: <input type="text" name="id"> <br>
내용: <textarea name="text"></textarea> <br>
주소: <input type="text" name="adress"> <br>
연락처: <input type="text" name="tel"> <br>
<input type="submit" value="등록">
</form>

</body>
</html>