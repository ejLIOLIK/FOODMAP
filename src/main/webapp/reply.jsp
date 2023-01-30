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

ArrayList<DTOreply> listR = new ArrayList<DTOreply>();
listR = (ArrayList<DTOreply>)request.getAttribute("listR");
int totalPageR = (int)request.getAttribute("totalPageR");
int currentPageR, currentPageBR;
// 리플 페이지
if(request.getParameter("currentPageR")==null || request.getParameter("currentPageR").equals("null")){
currentPageR = 1;	
}
else{
currentPageR = Integer.parseInt(request.getParameter("currentPageR"));
}
// 리플 페이지 블락 
if(request.getParameter("currentPageBR")==null || request.getParameter("currentPageBR").equals("null")){
	currentPageBR = 1;
}
else{
	currentPageBR = Integer.parseInt(request.getParameter("currentPageBR"));
}

%>

id / 평점 / 내용 / 날짜 <br>
<%
int countReply = 0; // 리플 칸 맞추기용 카운트
for(DTOreply d : listR){
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
<a href="read.jsp?editReplyNum=<%=d.Rnum%>&num=<%=d.Pnum%>&currentPage=<%=request.getParameter("currentPage")%>&sort=<%=request.getParameter("sort")%>&keyword=<%=request.getParameter("keyword")%>">수정</a>
 / 
<a href="/board/replyDelete?delNum=<%=d.Rnum%>&postNum=<%=DB.dto.num%>&currentPage=<%=request.getParameter("currentPage")%>&sort=<%=request.getParameter("sort")%>&keyword=<%=request.getParameter("keyword")%>">삭제</a>
<br>
<%
} countReply++; }
if(countReply<DB.PAGINGREPLY){
	for(int i=0;i<DB.PAGINGREPLY-countReply;i++){
		%><br><% } }

// 페이지블록
if(currentPageBR>1){
%><a href="/board/read?num=<%=DB.dto.num%>&currentPageR=<%=currentPageR%>&currentPageBR=<%=currentPageBR-1%>">&lt;</a><%}
else{
	%>&lt;<%
}
for(int i=0;i<totalPageR && i<DB.PAGINGREPLYBLOCK;i++){
	int pageBN = (currentPageBR-1)*DB.PAGINGREPLYBLOCK+i+1;
	if(pageBN>totalPageR){break;}
	%><a href="/board/read?num=<%=DB.dto.num%>&currentPageR=<%=pageBN%>&currentPageBR=<%=currentPageBR%>">[<%=pageBN%>]</a><%
}
if(currentPageBR>=(totalPageR+DB.PAGINGREPLYBLOCK-1)/DB.PAGINGREPLYBLOCK){
	%>&gt;<%}
else{
%><a href="/board/read?num=<%=DB.dto.num%>&currentPageR=<%=currentPageR%>&currentPageBR=<%=currentPageBR+1%>">&gt;</a><%}%>

<hr>
currentPageBR: <%= currentPageBR %> <br>
totalPageR: <%= totalPageR %> <br>
PAGINGREPLYBLOCK: <%= DB.PAGINGREPLYBLOCK %> <br>
(totalPageR+DB.PAGINGREPLYBLOCK-1)/DB.PAGINGREPLYBLOCK : <%=(totalPageR+DB.PAGINGREPLYBLOCK-1)/DB.PAGINGREPLYBLOCK %> <br>
<hr>
<form action="/board/replyWrite" method="get">
ID:<input type="text" name="id"><br>
<input type="number" max="5" min="0" name="point">
<textarea name="text"></textarea>
<input type="hidden" name="postNum" value="<%=DB.dto.num%>">
<input type="hidden" name="currentPage" value="<%=request.getParameter("currentPage")%>">
<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
<%-- <input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>"> --%>
<input type="submit" value="등록">
</form>

</body>
</html>