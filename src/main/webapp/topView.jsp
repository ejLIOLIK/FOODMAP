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
<title>TOP View</title>
<link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
<link href="css/styles.css" rel="stylesheet" />
<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
</head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="/index.jsp">🏠</a>
            <!-- Sidebar Toggle-->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            <!-- Navbar Search-->
            
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" action = "/board/list">
                <div class="input-group">
              	  	<select name="keywordRange">
    				<option value="title">제목</option>
				    <option value="content">내용</option>
				    <option value="titlecontent">제목+내용</option>
				    <option value="reply">리플</option>
				    </select>
				    
				    <input class="form-control" type="text" type="search" name="keyword" required="required" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
<%-- 					<input type="hidden" name="category" value="<%=blp.category%>"> --%>
					<input type="submit" class="btn btn-primary" id="btnNavbarSearch" value="🔍">
<!--                     <button class="btn btn-primary" id="btnNavbarSearch" type="button"></button> -->
                </div>
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="/board/logout">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
</body>
</html>