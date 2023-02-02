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
<%@include file="/homeButton.jsp" %>
<%@include file="/messageAlert.jsp" %>

번호 / <%=DB.dto.num%> <br>
제목 / <%=DB.dto.title%> [<%=DB.dto.reply%>] <br>
조회수 / <%=DB.dto.hit %> <br>
추천 / <%=DB.dto.recmd %> <br>
내용 / <%=DB.dto.text%> <br>
평점 / <%=DB.dto.point%> <br>
주소 / <%=DB.dto.adress%> <br>
전화번호 / <%=DB.dto.tel%> <br>
<hr>

<!-- 추천 완료 / 이미 추천한 경우 팝업 메시지 / 추천 취소? -->
추천하시겠습니까? <button type="button" onclick='/board/recommand?adress= &postNum= &recmdNum'>👍</button> 

<hr>
<!-- ---리플------------------------------------ -->
<%@include file = "/reply/reply.jsp" %>
<!-- ------------------------------------------ -->
<hr>
<br><a href="/board/list?adress=<%=request.getParameter("adress")%>">게시판</a>
<br><a href="/board/delete?adress=<%=request.getParameter("adress")%>&delNum=<%=DB.dto.num%>">삭제</a>
<br><a href="/board/edit_insert?adress=<%=request.getParameter("adress")%>&editNum=<%=DB.dto.num%>">수정</a>
</body>
</html>