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
<%
DAOsearch dao = new DAOsearch();
ArrayList<DTOres> list = new ArrayList<DTOres>();
String sort = ""; // 정렬기준
String keyword = ""; // 검색어
boolean blSearch = false; //검색=true

int currentPage, mountPage;
int currentPagingPage;
int countPost = 0; // 해당 페이지의 게시물을 세어줌. 페이지당 게시물수보다 적으면 빈 공간을 공백으로 채우기 위해서.

if(request.getParameter("currentPage")==null){ // 현재페이지
	currentPage = 1; }// 넘어온 값 없으면 첫 페이지로
else{
	currentPage = Integer.parseInt(request.getParameter("currentPage")); }
if(request.getParameter("currentPagingPage")==null){ //현재 페이징페이지
	currentPagingPage = (currentPage+DB.PAGINGBLOCK-1)/DB.PAGINGBLOCK; }// 넘어온 값 없으면 현재페이지로 계산
else{
	currentPagingPage = Integer.parseInt(request.getParameter("currentPagingPage")); }
if(request.getParameter("sort")!=null){
	sort = request.getParameter("sort");
}

if(request.getParameter("keyword")!=null){
	keyword = request.getParameter("keyword");
	blSearch = true;
}
%>

<form action = "board_ALL.jsp?keyword=<%=keyword%>">
<input type="search" name="keyword" value="<%=keyword%>" required="required">
<input type="submit" value="🔍">
</form>
<hr>
<%if(blSearch){%>
<a href="board_ALL.jsp?sort=최신순&keyword=<%=keyword%>">최신순</a>
<a href="board_ALL.jsp?sort=오래된순&keyword=<%=keyword%>">오래된순</a>
<a href="board_ALL.jsp?sort=평점높은순&keyword=<%=keyword%>">평점높은순</a>
<a href="board_ALL.jsp?sort=평점낮은순&keyword=<%=keyword%>">평점낮은순</a>
<%}
else{%>
<a href="board_ALL.jsp?sort=최신순">최신순</a>
<a href="board_ALL.jsp?sort=오래된순">오래된순</a>
<a href="board_ALL.jsp?sort=평점높은순">평점높은순</a>
<a href="board_ALL.jsp?sort=평점낮은순">평점낮은순</a>
<%}%>
<hr>
no./평점/ title 
<hr>
<%

if(blSearch){
	mountPage = dao.countPageDB(dao.countPostDB(keyword));
	list = dao.list(sort, currentPage, keyword);
}
else{
	mountPage = dao.countPageDB(dao.countPostDB());
	list = dao.list(sort, currentPage);
}

for(DTOres d:list){
	%><%=d.num%>/<%=d.point %>/ 
	<a href="read.jsp?num=<%=d.num%>&currentPage=<%=currentPage%>&sort=<%=sort%>&keyword=<%=keyword%>"><%= d.title %></a><br><%
	countPost++;
}
if(countPost<DB.PAGINGNUM){
	for(int i=0;i<DB.PAGINGNUM-countPost;i++){
		%><br><%	} // 빈 공간 공백 채워줌
}%>
<hr>
<!-- --페이지 부분-------------------------------- -->
<% if(blSearch){
if(currentPagingPage>1){%> 
<a href="board_ALL.jsp?currentPage=<%=currentPage%>&currentPagingPage=<%=currentPagingPage-1%>&sort=<%=sort%>&keyword=<%=keyword%>"> &lt; </a>
<%}
else {%>&lt;<%}
for(int i=(currentPagingPage-1)*DB.PAGINGBLOCK;i<currentPagingPage*DB.PAGINGBLOCK;i++){
	%><a href="board_ALL.jsp?currentPage=<%=i+1%>&sort=<%=sort%>&keyword=<%=keyword%>">[<%=i+1%>]</a><%
	if(i+1==mountPage){ //최대 페이지 도달 시 브레이크
		break;
	}
}
if(currentPagingPage<=mountPage/DB.PAGINGBLOCK){%>
<a href="board_ALL.jsp?currentPage=<%=currentPage%>&currentPagingPage=<%=currentPagingPage+1%>&sort=<%=sort%>&keyword=<%=keyword%>"> &gt; </a>
<%}
else {%>&gt;<%}
} // ------------------------------------------ -- 
else{
if(currentPagingPage>1){%> 
<a href="board_ALL.jsp?currentPage=<%=currentPage%>&currentPagingPage=<%=currentPagingPage-1%>&sort=<%=sort%>"> &lt; </a>
<%}
else {%>&lt;<%}
for(int i=(currentPagingPage-1)*DB.PAGINGBLOCK;i<currentPagingPage*DB.PAGINGBLOCK;i++){
	%><a href="board_ALL.jsp?currentPage=<%=i+1%>&sort=<%=sort%>">[<%=i+1%>]</a><%
	if(i+1==mountPage){ //최대 페이지 도달 시 브레이크
		break;
	}
}
if(currentPagingPage<(mountPage+DB.PAGINGBLOCK-1)/DB.PAGINGBLOCK){%>
<a href="board_ALL.jsp?currentPage=<%=currentPage%>&currentPagingPage=<%=currentPagingPage+1%>&sort=<%=sort%>"> &gt; </a>
<%}
else {%>&gt;<%}
}%>
<!-- ------------------------------------------ -->

<br>
<a href="write.jsp">글쓰기</a>

</body>
</html>