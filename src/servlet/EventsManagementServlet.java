package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.DaoFactory;
import core.DataSource;
import dao.EventsDao;
import tables.Events;

@WebServlet("/EventsManagementServlet")
public class EventsManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

	public EventsManagementServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nm = request.getSession().getAttribute("name").toString();
		EventsDao ed = this.daoFactory.getEventsDao();
		List<Events> lista = ed.organizedEvents(nm);
		request.setAttribute("eventi", lista);
		RequestDispatcher id = request.getServletContext().getRequestDispatcher("/content/eventsManagement.jsp");
		id.forward(request, response);
	}

}
