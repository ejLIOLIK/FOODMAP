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

<form action = "/board/list?keyword=<%=keyword%>">
<select name="keywordRange">
    <option value="제목">제목</option>
    <option value="내용">내용</option>
    <option value="제목+내용">제목+내용</option>
    <option value="리플">리플</option>
</select>
<input type="search" name="keyword" value="<%=keyword%>" required="required">
<input type="submit" value="🔍">
</form>
<hr>
<%if(blSearch){%>
<a href="/board/list?sort=new&keyword=<%=keyword%>">최신순</a>
<a href="/board/list?sort=old&keyword=<%=keyword%>">오래된순</a>
<a href="/board/list?sort=high&keyword=<%=keyword%>">평점높은순</a>
<a href="/board/list?sort=low&keyword=<%=keyword%>">평점낮은순</a>
<%}
else{%>
<a href="/board/list?sort=new">최신순</a>
<a href="/board/list?sort=old">오래된순</a>
<a href="/board/list?sort=high">평점높은순</a>
<a href="/board/list?sort=low">평점낮은순</a>
<%}%>
<hr>
no./평점/ title 
<hr>
<%
mountPage = (int)request.getAttribute("mountPage");
list = (ArrayList<DTOres>)request.getAttribute("list");

for(DTOres d:list){
	%><%=d.num%>/<%=d.point%>/ 
	<a href="/board/read?num=<%=d.num%>&currentPage=<%=currentPage%>&sort=<%=sort%>&keyword=<%=keyword%>"><%= d.title %></a> [<%=d.reply%>]<br><%
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
<a href="/board/list?currentPage=<%=currentPage%>&currentPagingPage=<%=currentPagingPage-1%>&sort=<%=sort%>&keyword=<%=keyword%>"> &lt; </a>
<%}
else {%>&lt;<%}
for(int i=(currentPagingPage-1)*DB.PAGINGBLOCK;i<currentPagingPage*DB.PAGINGBLOCK;i++){
	%><a href="/board/list?currentPage=<%=i+1%>&sort=<%=sort%>&keyword=<%=keyword%>">[<%=i+1%>]</a><%
	if(i+1==mountPage){ //최대 페이지 도달 시 브레이크
		break;
	}
}
if(currentPagingPage<=mountPage/DB.PAGINGBLOCK){%>
<a href="/board/list?currentPage=<%=currentPage%>&currentPagingPage=<%=currentPagingPage+1%>&sort=<%=sort%>&keyword=<%=keyword%>"> &gt; </a>
<%}
else {%>&gt;<%}
} // ------------------------------------------ -- 
else{
if(currentPagingPage>1){%> 
<a href="/board/list?currentPage=<%=currentPage%>&currentPagingPage=<%=currentPagingPage-1%>&sort=<%=sort%>"> &lt; </a>
<%}
else {%>&lt;<%}
for(int i=(currentPagingPage-1)*DB.PAGINGBLOCK;i<currentPagingPage*DB.PAGINGBLOCK;i++){
	%><a href="/board/list?currentPage=<%=i+1%>&sort=<%=sort%>">[<%=i+1%>]</a><%
	if(i+1==mountPage){ //최대 페이지 도달 시 브레이크
		break;
	}
}
if(currentPagingPage<(mountPage+DB.PAGINGBLOCK-1)/DB.PAGINGBLOCK){%>
<a href="/board/list?currentPage=<%=currentPage%>&currentPagingPage=<%=currentPagingPage+1%>&sort=<%=sort%>"> &gt; </a>
<%}
else {%>&gt;<%}
}%>
<!-- ------------------------------------------ -->

<br>
<a href="/write.jsp">글쓰기</a>

</body>
</html>