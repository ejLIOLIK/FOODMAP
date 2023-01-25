package sevletPack;

import java.io.IOException;

import DAO.DAOreply;
import DB.DB;
import DTO.DTOreply;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class replyWrite
 */
@WebServlet("/replyWrite")
public class replyWrite extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB.dtoR = new DTOreply(request.getParameter("Pnum"), request.getParameter("id"), request.getParameter("point"), request.getParameter("text"));
		DAOreply daoR = new DAOreply();
		daoR.write();
		daoR.upReplyNum(DB.dtoR.Pnum);
		daoR.countPointUpdate(DB.dtoR.Pnum, daoR.countPointDB(DB.dtoR.Pnum)/daoR.countReplyDB(DB.dtoR.Pnum));
		
		String query = String.format("read.jsp?num=%s&currentPage=%s&sort=%s&keyword=%s"
				, DB.dtoR.Pnum, request.getParameter("currentPage"), request.getParameter("sort"), request.getParameter("keyword"));
		response.sendRedirect(query);
	}
}
