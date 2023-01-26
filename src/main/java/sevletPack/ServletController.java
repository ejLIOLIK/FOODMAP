package sevletPack;

import java.io.IOException;
import java.util.ArrayList;

import DAO.DAOcrud;
import DB.DB;
import DTO.DTOres;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/board/*")
public class ServletController extends HttpServlet {
	String nextPage;
	DAOcrud dao;

	@Override
	public void init() throws ServletException {
		dao = new DAOcrud();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		System.out.println("action:"+action); //확인용
		
		if(action!=null) {
			switch(action){
			case "/delete":
				System.out.println("컨트롤러 : 삭제");
				nextPage = "/board_ALL.jsp";
				dao.delete(request.getParameter("delNum"));
				break;
			case "/write":
				System.out.println("컨트롤러 : 쓰기");
				nextPage = "/board_ALL.jsp";
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
				nextPage = "/board_ALL.jsp";
				DB.dto = new DTOres(request.getParameter("title"), 
						request.getParameter("text"), 
						request.getParameter("adress"), 
						request.getParameter("tel"));
				dao.edit(request.getParameter("editNum"));
				break;
//			case "/list":
//				System.out.println("컨트롤러 : 리스트");
//				nextPage="/list.jsp";
//				ArrayList<DTOres> list = new ArrayList<>();
//				list = dao.list(request.getParameter("sort"), Integer.parseInt(request.getParameter("currentPage")));
//				request.setAttribute("list", list);
//				break;
			}
		}
		RequestDispatcher d = request.getRequestDispatcher(nextPage);
		d.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
