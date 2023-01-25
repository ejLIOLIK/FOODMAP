<%@ page import="DTO.DTOreply" %>
<%@ page import="DAO.DAOreply" %>
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
<% 

ArrayList<DTOreply> replyList = new ArrayList<DTOreply>();
DAOreply daoR = new DAOreply();
replyList = daoR.list(DB.dto.num);

%>

id / 평점 / 내용 / 날짜 <br>
<%
for(DTOreply d : replyList){
if(d.Rnum.equals(request.getParameter("editReplyNum"))){
%>
	<form action="replyEdit" method="get">
	<%=d.id %> <br>
	<input type="number" max="5" min="0" name="point" value="<%=d.point%>">
	<textarea name="text"><%=d.text%></textarea>
	<input type="hidden" name="editReplyNum" value="<%=request.getParameter("editReplyNum")%>">
	<input type="hidden" name="num" value="<%=DB.dto.num%>">
	<input type="hidden" name="currentPage" value="<%=request.getParameter("currentPage")%>">
	<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
	<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
	<input type="submit" value="수정">
	</form>
<%}
else{%>
<%=d.id %> / <%=d.point %> / <%=d.text %> / <%=d.date %>
<a href="read.jsp?editReplyNum=<%=d.Rnum%>&num=<%=d.Pnum%>&currentPage=<%=request.getParameter("currentPage")%>&sort=<%=request.getParameter("sort")%>&keyword=<%=request.getParameter("keyword")%>">수정</a>
 / 
<a href="replyDelete?delNum=<%=d.Rnum%>&num=<%=DB.dto.num%>&currentPage=<%=request.getParameter("currentPage")%>&sort=<%=request.getParameter("sort")%>&keyword=<%=request.getParameter("keyword")%>">삭제</a>
<br>
<%
}}
%>
<hr>
<form action="replyWrite" method="get">
ID:<input type="text" name="id"><br>
<input type="number" max="5" min="0" name="point">
<textarea name="text"></textarea>
<input type="hidden" name="Pnum" value="<%=DB.dto.num%>">
<input type="hidden" name="currentPage" value="<%=request.getParameter("currentPage")%>">
<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
<input type="submit" value="등록">
</form>

</body>
</html>