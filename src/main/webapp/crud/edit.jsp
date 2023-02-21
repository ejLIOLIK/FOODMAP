<%@ page import="DB.DB" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
<title>Insert title here</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="/css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">

	<%@include file="/messageAlert.jsp" %>
	<%@include file="/topView.jsp" %>
	<%@include file="/leftView.jsp" %>
	
		            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">	
                        <h1 class="mt-4">글수정</h1>
                        <form action ="/board/edit_proc" method="post" enctype="multipart/form-data">
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item">주소</li>
                            <li class="breadcrumb-item active">
                            <select name="adress_select">
                            <option value=<%=DB.dto.adress%> selected><%=DB.dto.adress%></option>
							<option value="서울">서울</option>
							<option value="인천">인천</option>
							<option value="경기">경기</option>
							<option value="제주">제주</option>
							<option value="강원">강원</option>
							<option value="전북">전북</option>
							<option value="전남">전남</option>
							<option value="충북">충북</option>
							<option value="충남">충남</option>
							<option value="경북">경북</option>
							<option value="경남">경남</option>
							<option value="경남">대구</option>
							<option value="부산">부산</option>
							<option value="울산">울산</option>
							<option value="광주">광주</option>
							<option value="대전">대전</option>
						 </select>
                            </li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-body">
                                <p class="mb-0">
								작성자: <%=session.getAttribute("id")%> <br> <hr>
                                상호명: <input type="text" name="title" value="<%=DB.dto.title%>"> <br> <hr>
								내용: <textarea name="text"><%=DB.dto.text%></textarea> <br> <hr>
								연락처: <input type="text" name="tel" value="<%=DB.dto.tel%>"> <br>
								<input type="hidden" name="category" value="<%=request.getParameter("category")%>">
								<%
								if(DB.dto.img==null || DB.dto.img.equals("null")){
									%><img src="\upload\no.jpg" alt="img" width="200"><%
								}
								else{
									%><img src="\upload\<%=DB.dto.img%>" alt="img" width="200"><%
								}
								%>
								<br> 
								<hr> <input type="file" name="fileName"> <hr>
								<input type="submit" value="수정">
								</form>
                                </p>
                            </div>
                        </div>
                        <div style="height: 100vh"> 
                        <br><a href="/board/list?category=<%=request.getParameter("category")%>">뒤로</a>
                        </div>
                        <div class="card mb-4"><div class="card-body">When scrolling, the navigation stays at the top of the page. This is the end of the static navigation demo.</div></div>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>

</body>
</html>