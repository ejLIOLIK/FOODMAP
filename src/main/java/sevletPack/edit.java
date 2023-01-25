package sevletPack;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import DAO.DAOcrud;
import DB.DB;
import DTO.DTOres;

/**
 * Servlet implementation class edit
 */
@WebServlet("/edit")
public class edit extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOcrud dao = new DAOcrud();
		DB.dto = new DTOres(request.getParameter("title"), 
				request.getParameter("text"), 
				request.getParameter("adress"), 
				request.getParameter("tel"));

		dao.edit(request.getParameter("num"));
		response.sendRedirect("board_ALL.jsp");
	}

}
