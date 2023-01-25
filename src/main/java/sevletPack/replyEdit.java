package sevletPack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.DAOcrud;
import DAO.DAOreply;
import DB.DB;
import DTO.DTOreply;
import DTO.DTOres;

/**
 * Servlet implementation class replyEdit
 */
@WebServlet("/replyEdit")
public class replyEdit extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOreply daoR = new DAOreply();
		DB.dtoR = new DTOreply(request.getParameter("point"), 
				request.getParameter("text"));
		daoR.edit(request.getParameter("editReplyNum"));
		daoR.countPointUpdate(request.getParameter("num"));
		
		String query = String.format("read.jsp?num=%s&currentPage=%s&sort=%s&keyword=%s"
				, request.getParameter("num"), request.getParameter("currentPage"), request.getParameter("sort"), request.getParameter("keyword"));
		response.sendRedirect(query);
	}
}
