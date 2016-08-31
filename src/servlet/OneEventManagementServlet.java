package servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.DaoFactory;
import core.DataSource;
import dao.EventsDao;
import dao.TicketDao;
import tables.Information;

@WebServlet("/OneEventManagementServlet")
public class OneEventManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

	public OneEventManagementServlet() {
		super();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		TicketDao td = this.daoFactory.getTicketDao();
		if (request.getParameter("update").equals("yes")) {

			String tipo = request.getParameter("tipB");
			try {
				String tmp =tipo.replace("_", " ");
				String tmp2 = request.getParameter("price").replace(",",".");
				Double price = Double.parseDouble(tmp2);
				td.updatePrice(price, tmp);
			} catch (NumberFormatException | IllegalStateException e) {
			}
		}

		String value = request.getParameter("nome");
		EventsDao ed = this.daoFactory.getEventsDao();

		HashMap<String, Double> tipi = td.allTypeForOneEvent(ed.getCode(value));
		request.setAttribute("tipiBigl", tipi);
		Information i = ed.getInfoByName(value);
		boolean state = ed.getEventState(value);
		request.setAttribute("stato", state);
		request.setAttribute("info", i);

		RequestDispatcher id = request.getServletContext().getRequestDispatcher("/content/oneEventManagement.jsp");
		id.forward(request, response);
	}

}
