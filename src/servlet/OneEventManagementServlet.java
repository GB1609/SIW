package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.DaoFactory;
import core.DataSource;

@WebServlet("/OneEventManagementServlet")
public class OneEventManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);   
	
    public OneEventManagementServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String value = request.getParameter("nome");
		request.setAttribute("name", value);
		RequestDispatcher id = request.getServletContext().getRequestDispatcher("/content/prova.jsp");
		id.forward(request, response);
	}

}
