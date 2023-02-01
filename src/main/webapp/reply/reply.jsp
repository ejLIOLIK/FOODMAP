<%@ page import="PROC.replyProc" %>
<%@ page import="DTO.DTOreply" %>
<%@ page import="DAO.DAOreply" %>
<%@ page import="DB.DB" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="PROC.replyProc" %>
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
replyProc rpp = (replyProc)request.getAttribute("rpp");
%>

<!-- 리스트 바 -->
<%@include file = "/reply/replyTop.jsp" %>
<%
int countReply = 0;

for(DTOreply d : rpp.listR){
if(d.Rnum.equals(request.getParameter("editReplyNum"))){
%>
	<form action="/board/replyEdit" method="get">
	<%=d.id %> <br>
	<input type="number" max="5" min="0" name="point" value="<%=d.point%>">
	<textarea name="text"><%=d.text%></textarea>
	<input type="hidden" name="postNum" value="<%=DB.dto.num%>">
	<input type="hidden" name="editReplyNum" value="<%=request.getParameter("editReplyNum")%>">
	<input type="hidden" name="currentPage" value="<%=request.getParameter("currentPage")%>">
	<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
	<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
	<input type="submit" value="수정">
	</form>
<%}
else{%>
	<%=d.id %> / <%=d.point %> / <%=d.text %> / <%=d.date %>
	<% 
	String checkID = (String)session.getAttribute("id");
	if(checkID==null || checkID.equals("null")){%>
	<a href="/board/rightWriteR_login?adress=<%=request.getParameter("adress")%>&postNum=<%=d.Pnum%>">수정</a>	 / 
	<a href="/board/rightWriteR_login?adress=<%=request.getParameter("adress")%>&postNum=<%=DB.dto.num%>">삭제</a>
	<%}
	else if(checkID.equals(d.id)){%>
	<a href="/board/read?adress=<%=request.getParameter("adress")%>&editReplyNum=<%=d.Rnum%>&postNum=<%=d.Pnum%>&currentPage=<%=request.getParameter("currentPage")%>&sort=<%=request.getParameter("sort")%>&keyword=<%=request.getParameter("keyword")%>">수정</a>	 / 
	<a href="/board/replyDelete?adress=<%=request.getParameter("adress")%>&delNum=<%=d.Rnum%>&postNum=<%=DB.dto.num%>&currentPage=<%=request.getParameter("currentPage")%>&sort=<%=request.getParameter("sort")%>&keyword=<%=request.getParameter("keyword")%>">삭제</a>
	<%}
	else{%> 
	<a href="/board/rightWriteR_id?adress=<%=request.getParameter("adress")%>&postNum=<%=d.Pnum%>">수정</a>	 / 
	<a href="/board/rightWriteR_id?adress=<%=request.getParameter("adress")%>&postNum=<%=DB.dto.num%>">삭제</a>
	<%}%>
	<br>
	<%
} countReply++; }
	if(countReply<DB.PAGINGREPLY){
		for(int i=0;i<DB.PAGINGREPLY-countReply;i++){
	%><br><% } }%>
	
<!-- 페이징블록 -->
<%=rpp.htmlReplyPage()%>

<!-- 리플 입력 -->
<%@include file = "/reply/replyBottom.jsp"%>
</body>
</html>