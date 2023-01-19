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

<% 
DAOcrud dao = new DAOcrud();
dao.read(request.getParameter("num"));

%>
<form action ="edit" method="post">
번호 / <%=DB.dto.num%> <input type="hidden" name="num" value="<%=DB.dto.num%>">
상호명: <input type="text" name="title" value="<%=DB.dto.title%>"> <br>
작성자: <%=DB.dto.id%> <br>
별점(0~5): <input type="number" max="5" min="0" name="point" value="<%=DB.dto.point%>"> <br>
내용: <textarea name="text"><%=DB.dto.text%></textarea> <br>
주소: <input type="text" name="adress" value="<%=DB.dto.adress%>"> <br>
연락처: <input type="text" name="tel" value="<%=DB.dto.tel%>"> <br>
<input type="submit" value="수정">
</form>

<br><a href="board_ALL.jsp">뒤로</a>

</body>
</html>