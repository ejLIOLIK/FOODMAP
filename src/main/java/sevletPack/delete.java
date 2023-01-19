package sevletPack;

import java.io.IOException;

import DAO.DAOcrud;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class delete
 */
@WebServlet("/delete")
public class delete extends HttpServlet {
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOcrud dao = new DAOcrud();
		dao.delete(request.getParameter("num"));
		response.sendRedirect("board_ALL.jsp");
	}
}
