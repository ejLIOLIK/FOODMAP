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
<!-- 이미지 출력 -->
<!-- "D:\html_css_js\03_jsp_workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\FOODMAP\upload" -->
<% 
if(DB.dto.img==null || DB.dto.img.equals("null")){
	%><img src="\upload\no.jpg" alt="img" width="200"><%
}
else{
	%><img src="\upload\<%=DB.dto.img%>" alt="img" width="200"><%
}
%> <br>
<!-- - -->
내용 / <%=DB.dto.text%> <br>
평점 / <%=DB.dto.point%> <br>
주소 / <%=DB.dto.adress%> <br>
전화번호 / <%=DB.dto.tel%> <br>
<hr>

<!-- 추천 완료 / 이미 추천한 경우 팝업 메시지 / 추천 취소? -->
<!-- 추천 여부 미리 검사해서 버튼 이모지 다르게 띄우기 -->
추천하시겠습니까? <button type="button" onclick='/board/recommand?category=<%=request.getParameter("category")%>&postNum=<%=request.getParameter("postNum")%>'>👍</button> 

<hr>
<!-- ---리플------------------------------------ -->
<%@include file = "/reply/reply.jsp" %>
<!-- ------------------------------------------ -->
<hr>
<br><a href="/board/list?category=<%=request.getParameter("category")%>">게시판</a>
<br><a href="/board/delete?category=<%=request.getParameter("category")%>&delNum=<%=DB.dto.num%>">삭제</a>
<br><a href="/board/edit_insert?category=<%=request.getParameter("category")%>&editNum=<%=DB.dto.num%>">수정</a>
</body>
</html>