<%@ page import="PROC.boardProc" %>
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
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">🍕 Food Map</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="index.html"> 전체글 </a></li>
                            <%if(blp.category!=null){ %>
                            <li class="breadcrumb-item active"><%=blp.category %></li> <%} %>
                        </ol>
                        <div class="card mb-4">
                            <div class="card-body">
                                지역별 음식점 평점을 아카이빙합니다.
                            </div>
                        </div>
                        <div class="card mb-4">
                            <div class="card-header">

                            <%if(blp.blSearchCheck()){%>
							<a href="/board/list?category=<%=blp.categoryEcd%>&sort=new&keyword=<%=blp.keywordEcd%>&keywordRange=<%=blp.keywordRange%>">최신순</a>
							<a href="/board/list?category=<%=blp.categoryEcd%>&sort=old&keyword=<%=blp.keywordEcd%>&keywordRange=<%=blp.keywordRange%>">오래된순</a>
							<a href="/board/list?category=<%=blp.categoryEcd%>&sort=high&keyword=<%=blp.keywordEcd%>&keywordRange=<%=blp.keywordRange%>">평점높은순</a>
							<a href="/board/list?category=<%=blp.categoryEcd%>&sort=low&keyword=<%=blp.keywordEcd%>&keywordRange=<%=blp.keywordRange%>">평점낮은순</a>
							<%}
							else{%>
							<a href="/board/list?category=<%=blp.categoryEcd%>&sort=new">최신순</a>
							<a href="/board/list?category=<%=blp.categoryEcd%>&sort=old">오래된순</a>
							<a href="/board/list?category=<%=blp.categoryEcd%>&sort=high">평점높은순</a>
							<a href="/board/list?category=<%=blp.categoryEcd%>&sort=low">평점낮은순</a>
							<%}%>
							
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                    <%if(blp.blSearchCheck() && blp.keywordRange.equals("reply")){%>
                                    	<tr>
                                        <th>no.</th>
                                        <th>지역</th>
                                        <th>평점</th>
                                        <th>title</th>
                                        <th>리플평점</th>
                                        <th>리플내용</th>
                                        <th>리플날짜</th>
                                    	</tr>
                                    <%}else{%>
                                        <tr>
                                            <th>no.</th>
                                            <th>지역</th>
                                            <th>평점</th>
                                            <th>title</th>
                                            <th>조회수</th>
                                            <th>추천수</th>
                                        </tr>
                                        <%}%>
                                    </thead>
<!--                                     <tfoot> -->
<!--                                         <tr> -->
<!--                                             <th>no.</th> -->
<!--                                             <th>지역</th> -->
<!--                                             <th>평점</th> -->
<!--                                             <th>title</th> -->
<!--                                             <th>조회수</th> -->
<!--                                             <th>추천수</th> -->
<!--                                         </tr> -->
<!--                                     </tfoot> -->
                                    <tbody>
<%if(blp.blSearchCheck() && blp.keywordRange.equals("reply")){%>
	<%=blp.htmlBoardListSearchReply()%><%}
 else{ 
 	%><%=blp.htmlBoardList()%>
<%}%>
                                    </tbody>
                                </table>
                            </div>
                            <div class="datatable-bottom">
                            <div class="datatable-info">Showing <%=blp.getCurPage() %> to <%=blp.getCurPagingPage() %> of <%=blp.getTotalPage() %> entries</div> 
                            <%=blp.htmlBoardPage()%>
                            </div>
                        </div>
                    </div>
                </main>
</div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
</body>
</html>