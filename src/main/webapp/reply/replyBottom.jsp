<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="/board/replyWrite" method="get">

ID: <%@include file = "/loginInfo.jsp" %>
<input type="hidden" name="id" value="<%=id%>"><br>
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