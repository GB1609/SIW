package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.DaoFactory;
import core.DataSource;
import dao.TicketDao;

/**
 * Servlet implementation class UpdatePriceServlet
 */
@WebServlet("/UpdatePriceServlet")
public class UpdatePriceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
 
    public UpdatePriceServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
