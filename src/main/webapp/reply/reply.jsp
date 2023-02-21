<%@ page import="PROC.replyProc" %>
<%@ page import="DTO.DTOreply" %>
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

<% 
replyProc rpp = (replyProc)request.getAttribute("rpp");
int countReply = 0;
%>
                        <div class="card mb-4">
                            <div class="card-header">
                                🖋️ REPLY
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>id</th>
                                            <th>평점</th>
                                            <th>내용</th>
                                            <th>날짜</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                        if(rpp.listR.size()!=0){
                                        for(DTOreply d : rpp.listR){
                                        	%><tr><%
											if(d.Rnum.equals(request.getParameter("editReplyNum"))){
											%>
												<td colspan='5'>
												<form action="/board/replyEdit" method="get">
												<%=d.id %> 
												<input type="number" max="5" min="0" name="point" value="<%=d.point%>">
												<textarea name="text"><%=d.text%></textarea>
												<input type="hidden" name="postNum" value="<%=DB.dto.num%>">
												<input type="hidden" name="editReplyNum" value="<%=request.getParameter("editReplyNum")%>">
												<input type="hidden" name="currentPage" value="<%=request.getParameter("currentPage")%>">
												<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
												<input type="hidden" name="keyword" value="<%=request.getParameter("keyword")%>">
												<input type="submit" value="수정">
												</td>
												</form>
											<%}
											else{%>
												<td><%=d.id %></td>  <td><%=d.point %></td>  <td><%=d.text %></td>  <td><%=d.date %></td>
												<% 
												String checkID = (String)session.getAttribute("id");
												if(checkID==null || checkID.equals("null")){%>
												<td><a href="/board/rightWriteR_login?category=<%=request.getParameter("category")%>&postNum=<%=d.Pnum%>">수정</a>	 / 
												<a href="/board/rightWriteR_login?category=<%=request.getParameter("category")%>&postNum=<%=DB.dto.num%>">삭제</a></td>
												<%}
												else if(checkID.equals(d.id)){%>
												<td><a href="/board/read?category=<%=request.getParameter("category")%>&editReplyNum=<%=d.Rnum%>&postNum=<%=d.Pnum%>&currentPage=<%=request.getParameter("currentPage")%>&sort=<%=request.getParameter("sort")%>&keyword=<%=request.getParameter("keyword")%>">수정</a>	 / 
												<a href="/board/replyDelete?category=<%=request.getParameter("category")%>&delNum=<%=d.Rnum%>&postNum=<%=DB.dto.num%>&currentPage=<%=request.getParameter("currentPage")%>&sort=<%=request.getParameter("sort")%>&keyword=<%=request.getParameter("keyword")%>">삭제</a></td>
												<%}
												else{%> 
												<td><a href="/board/rightWriteR_id?category=<%=request.getParameter("category")%>&postNum=<%=d.Pnum%>">수정</a>	 / 
												<a href="/board/rightWriteR_id?category=<%=request.getParameter("category")%>&postNum=<%=DB.dto.num%>">삭제</a></td>
												<%}%>
												</tr>
												<%
											} countReply++; }
                                        }
                                        else{
                                        	%> <tr><td colspan='5'>작성된 리플이 없습니다.<td></tr> <%
                                        }
                                        
											if(countReply<DB.PAGINGREPLY){
												for(int i=0;i<DB.PAGINGREPLY-countReply;i++){
											%><tr><td></td><td></td><td></td><td></td><td></td></tr><% } 
											}%>
											
											<tr>
											<td colspan='5'>
											<form action="/board/replyWrite" method="get">
											ID: <%@include file = "/loginInfo.jsp" %>
											<input type="number" max="5" min="0" name="point">
											<textarea name="text"></textarea>
											<input type="hidden" name="postNum" value="<%=DB.dto.num%>">
											<input type="hidden" name="currentPage" value="<%=request.getParameter("currentPage")%>">
											<input type="hidden" name="sort" value="<%=request.getParameter("sort")%>">
											<input type="hidden" name="category" value=<%=request.getParameter("category") %>>
											<input type="submit" value="등록">
											</form>
											</td>
											</tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="datatable-bottom">
                            <hr>
                            <div class="datatable-info">Showing <%=rpp.getCurrentPageRN() %> to <%=rpp.getCurrentPageBRN() %> of <%=rpp.getTotalPageR() %> entries</div> 
                            <%=rpp.htmlReplyPage()%>
                            </div>
                        </div>
                        </div>
</body>
</html>