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
번호 / <%=DB.dto.num%> <br>
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
<br><a href="/board_ALL.jsp">전체페이지</a>
<br><a href="/board/delete?delNum=<%=DB.dto.num%>">삭제</a>
<br><a href="/board/edit_insert?editNum=<%=DB.dto.num%>">수정</a>
</body>
</html>