package sevletPack;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import DB.DB;
import DTO.DTOreply;
import DTO.DTOres;
import PROC.boardProc;
import PROC.replyProc;
import servicePack.serviceBoard;

@WebServlet("/board/*") 
public class ControllerBoard extends HttpServlet {
	String nextPage;
	serviceBoard service; 
	@Override
	public void init() throws ServletException {
		service = new serviceBoard();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getPathInfo();
		System.out.println("action:"+action); //확인용
		
		if(action!=null) {
			switch(action){
			case "/delete":
				nextPage = "/board/list?adress="+request.getParameter("adress");
				service.delete(request.getParameter("delNum"));
				break;
//			case "/write":
//				nextPage = "/board/list?adress="+request.getParameter("adress");
//				DB.dto = new DTOres(request.getParameter("title"), 
//						request.getParameter("id"), 
//						request.getParameter("text"), 
//						request.getParameter("adress"), 
//						request.getParameter("tel"),
//						request.getParameter("fileName"));
//				service.write();
//				break;
			case "/read":
				nextPage = "/crud/read.jsp?editReplyNum"+request.getParameter("editReplyNum")+"&currentPageR="+request.getParameter("currentPageR")+"&currentPageBR="+request.getParameter("currentPageBR")+"&adress="+request.getParameter("adress");
				service.read(request.getParameter("postNum"));
				replyProc rpp = new replyProc(request.getParameter("postNum"), 
						request.getParameter("currentPageR"), 
						request.getParameter("currentPageBR"), 
						service.countPageR(request.getParameter("postNum")));
				request.setAttribute("rpp", rpp);
				break;
			case "/edit_insert":
				nextPage = "/crud/edit.jsp";
				service.read(request.getParameter("editNum"));
				break;
			case "/edit_proc":
				nextPage = "/board/list?adress="+request.getParameter("adress");
				DB.dto = new DTOres(request.getParameter("title"), 
						request.getParameter("text"), 
						request.getParameter("adress"), 
						request.getParameter("tel"), 
						request.getParameter("fileName"));
				service.edit(request.getParameter("editNum"));
				break;
			case "/list":		
				nextPage="/boardList/board_ALL.jsp?adress="+request.getParameter("adress");
				boardProc blp = new boardProc(request.getParameter("adress"),
						request.getParameter("currentPage"), 
						request.getParameter("currentPagingPage"),
						request.getParameter("sort"),
						request.getParameter("keyword"),
						request.getParameter("keywordRange"));
				request.setAttribute("blp", blp);
				break;
			case "/replyDelete":
				nextPage = "/board/read?postNum="+request.getParameter("postNum")+"&adress="+request.getParameter("adress");
				service.deleteReply(request.getParameter("delNum"), request.getParameter("postNum"));
				break;
			case "/replyEdit":
				nextPage = "/board/read?editReplyNum=null&num="+request.getParameter("postNum")+"&adress="+request.getParameter("adress");
				DB.dtoR = new DTOreply(request.getParameter("point"), 
						request.getParameter("text"));
				service.editReply(request.getParameter("editReplyNum"), request.getParameter("postNum")); 
				break;
			case "/replyWrite":
				if(request.getParameter("id").equals("null") || request.getParameter("id")==null) {
					nextPage = "/board/rightWriteR_login?num="+request.getParameter("postNum")+"&adress="+request.getParameter("adress");}
				else {
					nextPage = "/board/read?postNum="+request.getParameter("postNum")+"&adress="+request.getParameter("adress");
					DB.dtoR = new DTOreply(request.getParameter("postNum"), request.getParameter("id"), request.getParameter("point"), request.getParameter("text")); 
					service.writeReply(request.getParameter("postNum"));}
				break;
			case "/rightWrite_login":
				nextPage = "/board/list?adress="+request.getParameter("adress");
				request.setAttribute("message", "로그인 후 이용하세요.");
				break;
			case "/rightWrite_id":
				nextPage = "/board/list?adress="+request.getParameter("adress");
				request.setAttribute("message", "로그인 후 이용하세요.");
				break;
			case "/rightWriteR_login":
				nextPage = "/board/read?postNum="+request.getParameter("postNum")+"&adress="+request.getParameter("adress");
				request.setAttribute("message", "로그인 후 이용하세요.");
				break;
			case "/rightWriteR_id":
				nextPage = "/board/read?postNum="+request.getParameter("postNum")+"&adress="+request.getParameter("adress");
				request.setAttribute("message", "권한이 없습니다.");
				break;
			case "/logout":
				nextPage = "/index.jsp";
				service.logout();
				break;
			}
		}
		RequestDispatcher d = request.getRequestDispatcher(nextPage);
		d.forward(request,response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getPathInfo();
		System.out.println("action:"+action); //확인용
		
		if(action!=null) {
			switch(action){
			case "/login":
				if(service.login(request, request.getParameter("id"), request.getParameter("pw"))){
					request.setAttribute("id", request.getParameter("id"));
					nextPage = "/index.jsp";}
				else {
					request.setAttribute("message", "로그인 실패");
					nextPage = "/login.jsp";}
				break;
			case "/memjoin":
				int message = service.memJoin(request.getParameter("email"), request.getParameter("id"), request.getParameter("pw1"), request.getParameter("pw2"));
				service.MemJoinMessage(message);
				request.setAttribute("message", service.MemJoinMessage(message));
				if(message==4) { // 가입성공
					nextPage = "/login.jsp";}
				else {
					nextPage = "/memjoin.jsp";}
				break;
			case "/write":
				MultipartRequest multi = null;
				
			    String saveFolder = request.getServletContext().getRealPath("/upload"); // 경로 상수화 ?
			    String encType = "UTF-8"; // 상수화 ? 
			    String fileNameTemp = "";
			    
			    int maxSize = 50 * 1024 * 1024; // 50mb //상수화 ? 
			    
			    try {
			        multi = new MultipartRequest(request, saveFolder, maxSize,
			                encType, new DefaultFileRenamePolicy());
			        
			        String fileName = multi.getFilesystemName("fileName");
			        fileNameTemp = fileName; //"\\upload\\" 파일명만 저장하고 가져올 때 가공.

			    } catch (IOException ioe) {
			        System.out.println(ioe);
			    } catch (Exception ex) {
			        System.out.println(ex);
			    }
				
				DB.dto = new DTOres(multi.getParameter("title"), 
						multi.getParameter("id"), 
						multi.getParameter("text"), 
						multi.getParameter("adress_select"),
						multi.getParameter("tel"),
						fileNameTemp);
				service.write();
				nextPage = "/listGate.jsp?adress="+multi.getParameter("adress");
				break;
			}
		}
		RequestDispatcher d = request.getRequestDispatcher(nextPage);
		d.forward(request,response);
	}
}
