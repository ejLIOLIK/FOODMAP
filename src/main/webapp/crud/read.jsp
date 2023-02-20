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
                        <h1 class="mt-4"><%=DB.dto.title%> [<%=DB.dto.reply%>]</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.html"><%=DB.dto.adress%></a></li>
                            <li class="breadcrumb-item active"><%=DB.dto.point%></li>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-body">
                                <p class="mb-0">
                                조회수 / <%=DB.dto.hit %> <br>
								추천 / <%=DB.dto.recmd %> <br>    
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
								주소 / <%=DB.dto.adress%> <br>
								전화번호 / <%=DB.dto.tel%> <br>
                                </p>
                            </div>
                        </div>
                        <div style="height: 100vh"> 
                        <br><a href="/board/delete?category=<%=request.getParameter("category")%>&delNum=<%=DB.dto.num%>">삭제</a>
						<br><a href="/board/edit_insert?category=<%=request.getParameter("category")%>&editNum=<%=DB.dto.num%>">수정</a>
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