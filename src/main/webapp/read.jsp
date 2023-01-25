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

%>번호 / <%=DB.dto.num%> <br>
제목 / <%=DB.dto.title%> [<%=DB.dto.reply%>] <br>
내용 / <%=DB.dto.text%> <br>
평점 / <%=DB.dto.point%> <br>
주소 / <%=DB.dto.adress%> <br>
전화번호 / <%=DB.dto.tel%> <br><%
%>
<hr>
<!-- ---리플------------------------------------ -->
<%@include file = "reply.jsp" %>
<!-- ------------------------------------------ -->
<hr>
<br><a href="board_ALL.jsp?currentPage=<%=request.getParameter("currentPage")%>&sort=<%=request.getParameter("sort")%>&keyword=<%=request.getParameter("keyword")%>">뒤로</a>
<br><a href="delete?num=<%=DB.dto.num%>">삭제</a>
<br><a href="edit.jsp?num=<%=DB.dto.num%>">수정</a>
</body>
</html>