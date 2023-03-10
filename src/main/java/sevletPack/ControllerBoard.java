package sevletPack;

import java.io.IOException;
import java.net.URLEncoder;

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
				if(service.adminRight()) {
					nextPage = "/board/list?category="+request.getParameter("category");
					service.delete(request.getParameter("delNum"));
				}
				else {
					nextPage = "/board/list?category="+request.getParameter("category");
					request.setAttribute("message", "권한이 없습니다.");
				}
				break;
			case "/write":
				if(service.adminRight()) {
					nextPage = "/crud/write.jsp?category="+request.getParameter("category");
				}
				else {
					nextPage = "/board/list?category="+request.getParameter("category");
					request.setAttribute("message", "권한이 없습니다.");
				}
				break;
			case "/read":
				nextPage = "/crud/read.jsp?editReplyNum"+request.getParameter("editReplyNum")+"&currentPageR="+request.getParameter("currentPageR")+"&currentPageBR="+request.getParameter("currentPageBR")+"&category="+request.getParameter("category");
				service.read(request.getParameter("postNum"));
				replyProc rpp = new replyProc(request.getParameter("postNum"), 
						request.getParameter("currentPageR"), 
						request.getParameter("currentPageBR"), 
						service.countPageR(request.getParameter("postNum")));
				request.setAttribute("rpp", rpp);
				if(service.recmdCheck(request.getParameter("postNum"))) {request.setAttribute("blRecmd", "true");}
				else {request.setAttribute("blRecmd", "false");}
				break;
			case "/edit_insert":
				if(service.adminRight()) {
					nextPage = "/crud/edit.jsp";
					service.read(request.getParameter("editNum"));}
				else {
					nextPage = "/board/list?category="+request.getParameter("category");
					request.setAttribute("message", "권한이 없습니다.");
				}
				break;
			case "/list":	
				nextPage="/boardList/board_ALL.jsp?category="+request.getParameter("category");
				boardProc blp = new boardProc(request.getParameter("category"),
						request.getParameter("currentPage"), 
						request.getParameter("currentPagingPage"),
						request.getParameter("sort"),
						request.getParameter("keyword"),
						request.getParameter("keywordRange"));
				request.setAttribute("blp", blp);
				request.setAttribute("message", request.getAttribute("message"));
				break;
			case "/replyDelete":
				nextPage = "/board/read?postNum="+request.getParameter("postNum")+"&category="+request.getParameter("category");
				service.deleteReply(request.getParameter("delNum"), request.getParameter("postNum"));
				break;
			case "/replyEdit":
				nextPage = "/board/read?editReplyNum=null&num="+request.getParameter("postNum")+"&category="+request.getParameter("category");
				DB.dtoR = new DTOreply(request.getParameter("point"), 
						request.getParameter("text"));
				service.editReply(request.getParameter("editReplyNum"), request.getParameter("postNum")); 
				break;
			case "/replyWrite":
				if(service.getIdSession().equals("null")) {
					nextPage = "/board/rightWriteR_login?num="+request.getParameter("postNum")+"&category="+request.getParameter("category");}
				else {
					nextPage = "/board/read?postNum="+request.getParameter("postNum")+"&category="+request.getParameter("category");
					DB.dtoR = new DTOreply(request.getParameter("postNum"), service.getIdSession(), request.getParameter("point"), request.getParameter("text")); 
					service.writeReply(request.getParameter("postNum"));}
				break;
			case "/rightWrite_login":
				nextPage = "/board/list?category="+request.getParameter("category");
				request.setAttribute("message", "로그인 후 이용하세요.");
				break;
			case "/rightWrite_id":
				nextPage = "/board/list?category="+request.getParameter("category");
				request.setAttribute("message", "로그인 후 이용하세요.");
				break;
			case "/rightWriteR_login":
				nextPage = "/board/read?postNum="+request.getParameter("postNum")+"&category="+request.getParameter("category");
				request.setAttribute("message", "로그인 후 이용하세요.");
				break;
			case "/rightWriteR_id":
				nextPage = "/board/read?postNum="+request.getParameter("postNum")+"&category="+request.getParameter("category");
				request.setAttribute("message", "권한이 없습니다.");
				break;
			case "/logout":
				nextPage = "/index.jsp";
				service.logout(); 
				break;
			case "/recommand":
				if(service.getIdSession().equals("null")) {
					System.out.println("recommand id null.");
					nextPage = "/board/rightWriteR_login?num="+request.getParameter("postNum")+"&category="+request.getParameter("category");}
				else {
					System.out.println("recommand id check.");
					nextPage = "/board/read?postNum="+request.getParameter("postNum")+"&category="+request.getParameter("category");
					service.recmdUpDown(service.recmdCheck(request.getParameter("postNum")), request.getParameter("postNum"));}
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

		// 이미지
		String saveFolder;
		MultipartRequest multi;
		String fileNameTemp;

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
				saveFolder = request.getSession().getServletContext().getRealPath("/writeImg");
				System.out.println(saveFolder);
				multi = null;
				fileNameTemp = "";
			    
			    try {
			        multi = new MultipartRequest(request, saveFolder, DB.maxSize,
			        		DB.encType, new DefaultFileRenamePolicy());
			        
			        String fileName = multi.getFilesystemName("fileName");
			        fileNameTemp = fileName; 

			    } catch (IOException ioe) {
			        System.out.println(ioe);
			    } catch (Exception ex) {
			        System.out.println(ex);
			    }
				
				DB.dto = new DTOres(multi.getParameter("title"), 
						service.getIdSession(), 
						multi.getParameter("text"), 
						multi.getParameter("adress_select"),
						multi.getParameter("tel"),
						fileNameTemp);
				service.write();
				request.setAttribute("category", URLEncoder.encode(multi.getParameter("category"), "UTF-8"));
				nextPage = "/listGate.jsp";
				break;
			case "/edit_proc":
				saveFolder = request.getSession().getServletContext().getRealPath("/writeImg"); 
				System.out.println(saveFolder);
				multi = null;
				fileNameTemp = "";
			    
			    try {
			        multi = new MultipartRequest(request, saveFolder, DB.maxSize,
			        		DB.encType, new DefaultFileRenamePolicy());
			        
			        String fileName = multi.getFilesystemName("fileName");
			        
			        if(fileName==null) {
			        	fileNameTemp = multi.getParameter("oldFileName");  } // 수정 안하는 경우
			        else {
			        	fileNameTemp = fileName; }

			    } catch (IOException ioe) {
			        System.out.println(ioe);
			    } catch (Exception ex) {
			        System.out.println(ex);
			    }
				
			    DB.dto = new DTOres(multi.getParameter("title"), 
						multi.getParameter("text"), 
						multi.getParameter("adress_select"), 
						multi.getParameter("tel"), 
						fileNameTemp);
				service.edit(multi.getParameter("editNum"));
				request.setAttribute("category", URLEncoder.encode(multi.getParameter("category"), "UTF-8"));
				nextPage = "/listGate.jsp";
				break;
			}
		}
		RequestDispatcher d = request.getRequestDispatcher(nextPage);
		d.forward(request,response);
	}
}
