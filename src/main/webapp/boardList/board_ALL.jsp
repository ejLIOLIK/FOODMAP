<%@ page import="PROC.boardProc" %>
<%@ page import="DAO.DAOsearch" %>
<%@ page import="DAO.DAOcrud" %>
<%@ page import="DTO.DTOres" %>
<%@ page import="DB.DB" %>
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

<%@include file="/homeButton.jsp" %>
<%@include file="/messageAlert.jsp" %>

<%
boardProc blp = (boardProc)request.getAttribute("blp");
%>

<!-- 검색 / 정렬 / 리스트바 인크루드 -->
<%@include file = "/boardList/boardTop.jsp" %>

<!-- 리스트 페이징 -->
<%if(blp.blSearchCheck() && blp.keywordRange.equals("reply")){%>
	<%=blp.htmlBoardListSearchReply()%><%}
else{
	%><%=blp.htmlBoardList()%>
<%}%>

<%=blp.htmlBoardPage()%>
	<br>

<!-- 글쓰기 / 전체글보기 등  -->
<%@include file = "/boardList/boardBottom.jsp" %>

</body>
</html>