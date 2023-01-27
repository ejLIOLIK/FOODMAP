package sevletPack;

import java.io.IOException;

import BOARD.boardProc;
import DB.DB;
import DTO.DTOreply;
import DTO.DTOres;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
		String action = request.getPathInfo();
		System.out.println("action:"+action); //확인용
		
		if(action!=null) {
			switch(action){
			case "/delete":
				nextPage = "/board/list";
				service.delete(request.getParameter("delNum"));
				break;
			case "/write":
				nextPage = "/board/list";
				DB.dto = new DTOres(request.getParameter("title"), 
						request.getParameter("id"), 
						request.getParameter("text"), 
						request.getParameter("adress"), 
						request.getParameter("tel"));
				service.write();
				break;
			case "/read":
				nextPage = "/read.jsp";
				request.setAttribute("listR", service.read(request.getParameter("num")));
				break;
			case "/edit_insert":
				nextPage = "/edit.jsp";
				service.read(request.getParameter("editNum"));
				break;
			case "/edit_proc":
				nextPage = "/board/list";
				DB.dto = new DTOres(request.getParameter("title"), 
						request.getParameter("text"), 
						request.getParameter("adress"), 
						request.getParameter("tel"));
				service.edit(request.getParameter("editNum"));
				break;
			case "/list":				
				nextPage="/board_ALL.jsp";
				boardProc blp = new boardProc(request.getParameter("currentPage"), 
						request.getParameter("currentPagingPage"),
						request.getParameter("sort"),
						request.getParameter("keyword"));
//				request.setAttribute("list", service.list(request.getParameter("keyword"), request.getParameter("sort"), request.getParameter("currentPage")));
//				request.setAttribute("mountPage", service.mountPage(request.getParameter("keyword")));
				request.setAttribute("blp", blp);
				break;
			case "/replyDelete":
				nextPage = "/board/read?num="+request.getParameter("postNum");
				service.deleteReply(request.getParameter("delNum"), request.getParameter("postNum"));
				break;
			case "/replyEdit":
				nextPage = "/board/read?editReplyNum=null&num="+request.getParameter("postNum");
				DB.dtoR = new DTOreply(request.getParameter("point"), 
						request.getParameter("text"));
				service.editReply(request.getParameter("editReplyNum"), request.getParameter("postNum")); 
				break;
			case "/replyWrite":
				nextPage = "/board/read?num="+request.getParameter("postNum");
				DB.dtoR = new DTOreply(request.getParameter("postNum"), request.getParameter("id"), request.getParameter("point"), request.getParameter("text")); 
				service.writeReply(request.getParameter("postNum"));
				break;
			}
		}
		RequestDispatcher d = request.getRequestDispatcher(nextPage);
		d.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
