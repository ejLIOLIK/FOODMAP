package sevletPack;

import java.io.IOException;

import DAO.DAOcrud;
import DTO.DTOres;
import DB.DB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class write
 */
@WebServlet("/write")
public class write extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOcrud dao = new DAOcrud();
		DB.dto = new DTOres(request.getParameter("title"), 
				request.getParameter("id"), 
				request.getParameter("text"), 
				request.getParameter("point"), 
				request.getParameter("adress"), 
				request.getParameter("tel"));

		dao.write();
		response.sendRedirect("board_ALL.jsp");
	}

}
