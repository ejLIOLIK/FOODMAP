<%@ page import="DAO.DAOcrud" %>
<%@ page import="DTO.DTOres" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

no./ title <hr>
<%

DAOcrud dao = new DAOcrud();
ArrayList<DTOres> list = new ArrayList<DTOres>();

list = dao.listAll("");

for(DTOres d:list){
	%><%= d.num %>/ <a href="read.jsp?num=<%=d.num%>"><%= d.title %></a><br><%
}

%>

<br>
<a href="write.jsp">글쓰기</a>

</body>
</html>