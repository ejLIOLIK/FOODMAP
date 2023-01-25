package sevletPack;

import java.io.IOException;

import DAO.DAOreply;
import DB.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class replyDelete
 */
@WebServlet("/replyDelete")
public class replyDelete extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOreply daoR = new DAOreply();
		String postNum = request.getParameter("num");
		daoR.delete(request.getParameter("delNum"));
		daoR.downReplyNum(postNum);
		daoR.countPointUpdate(postNum);
		
		String query = String.format("read.jsp?num=%s&currentPage=%s&sort=%s&keyword=%s", 
				postNum, request.getParameter("currentPage"), request.getParameter("sort"), request.getParameter("keyword"));
		response.sendRedirect(query);
	}
}
