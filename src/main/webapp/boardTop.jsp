<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action = "/board/list?keyword=<%=blp.keyword%>">
<select name="keywordRange">
    <option value="title">제목</option>
    <option value="content">내용</option>
    <option value="titlecontent">제목+내용</option>
    <option value="reply">리플</option>
</select>

<%if(blp.blSearchCheck()){ 
	%><input type="search" name="keyword" value="<%=blp.keywordDcd%>" required="required"><% }
else{
	%><input type="search" name="keyword" required="required"><% }%>

<input type="submit" value="🔍">
</form>
<hr>
<%if(blp.blSearchCheck()){%>
<a href="/board/list?sort=new&keyword=<%=blp.keyword%>&keywordRange=<%=blp.keywordRange%>">최신순</a>
<a href="/board/list?sort=old&keyword=<%=blp.keyword%>&keywordRange=<%=blp.keywordRange%>">오래된순</a>
<a href="/board/list?sort=high&keyword=<%=blp.keyword%>&keywordRange=<%=blp.keywordRange%>">평점높은순</a>
<a href="/board/list?sort=low&keyword=<%=blp.keyword%>&keywordRange=<%=blp.keywordRange%>">평점낮은순</a>
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

</body>
</html>