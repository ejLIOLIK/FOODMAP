package sevletPack;

import java.io.IOException;
import java.util.ArrayList;

import DAO.DAOreply;
import DAO.DAOsearch;
import DB.DB;
import DTO.DTOreply;
import DTO.DTOres;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/*") 
public class ControllerBoard extends HttpServlet {
	String nextPage;
	DAOsearch dao;
	DAOreply daoR;
	ArrayList<DTOres> list;
	ArrayList<DTOreply> listR;

	@Override
	public void init() throws ServletException {
		dao = new DAOsearch();
		daoR = new DAOreply();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		System.out.println("action:"+action); //확인용
		
		String postNum;
		
		if(action!=null) {
			switch(action){
			case "/delete":
				System.out.println("컨트롤러 : 삭제");
				nextPage = "/board/list";
				dao.delete(request.getParameter("delNum"));
				break;
			case "/write":
				System.out.println("컨트롤러 : 쓰기");
				nextPage = "/board/list";
				DB.dto = new DTOres(request.getParameter("title"), 
						request.getParameter("id"), 
						request.getParameter("text"), 
						request.getParameter("adress"), 
						request.getParameter("tel"));
				dao.write();
				break;
			case "/read":
				System.out.println("컨트롤러 : 읽기");
				nextPage = "/read.jsp";
				dao.read(request.getParameter("num"));
				break;
			case "/edit_insert":
				System.out.println("컨트롤러 : 수정 입력");
				nextPage = "/edit.jsp";
				dao.read(request.getParameter("editNum"));
				break;
			case "/edit_proc":
				System.out.println("컨트롤러 : 수정 Proc");
				nextPage = "/board/list";
				DB.dto = new DTOres(request.getParameter("title"), 
						request.getParameter("text"), 
						request.getParameter("adress"), 
						request.getParameter("tel"));
				dao.edit(request.getParameter("editNum"));
				break;
			case "/list":				
				System.out.println("컨트롤러 : 리스트");
				nextPage="/board_ALL.jsp";
				ArrayList<DTOres> list = new ArrayList<>();
				int mountPage;
				
				if(request.getParameter("keyword")==null){ // ====== 검색X
					mountPage = dao.countPageDB(dao.countPostDB());
					list = dao.list(request.getParameter("sort"), 
							request.getParameter("currentPage"));}
				else {	// ========================================= 검색O
					mountPage = dao.countPageDB(dao.countPostDB(request.getParameter("keyword")));
					list = dao.list(request.getParameter("sort"), 
							request.getParameter("currentPage"), 
							request.getParameter("keyword"));}
				request.setAttribute("list", list);
				request.setAttribute("mountPage", mountPage);
				break;
			case "/replyDelete":
				postNum = request.getParameter("postNum");
				System.out.println("컨트롤러 : 리플삭제");
				nextPage = "/board/read?num="+postNum;
				daoR.delete(request.getParameter("delNum"));
				daoR.downReplyNum(postNum);
				daoR.countPointUpdate(postNum);
				break;
			case "/replyEdit":
				postNum = request.getParameter("postNum");
				System.out.println("컨트롤러 : 리플수정");
				nextPage = "/board/read?editReplyNum=null&num="+postNum;
				DB.dtoR = new DTOreply(request.getParameter("point"), 
						request.getParameter("text"));
				daoR.edit(request.getParameter("editReplyNum"));
				daoR.countPointUpdate(postNum);
				break;
			case "/replyWrite":
				postNum = request.getParameter("postNum");
				System.out.println("컨트롤러 : 리플입력");
				nextPage = "/board/read?num="+postNum;
				DB.dtoR = new DTOreply(postNum, request.getParameter("id"), request.getParameter("point"), request.getParameter("text")); 
				daoR.write();
				daoR.upReplyNum(postNum);
				daoR.countPointUpdate(postNum);
				break;
			}
		}
		RequestDispatcher d = request.getRequestDispatcher(nextPage);
		d.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
