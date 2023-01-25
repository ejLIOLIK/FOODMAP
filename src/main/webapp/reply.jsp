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
%> 
<%=d.id %> / <%=d.point %> / <%=d.text %> / <%=d.date %><br>
<%
}
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