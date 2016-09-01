package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import tables.Information;

@WebServlet("/ShowInformationServlet")
public class ShowInformationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected DataSource dataSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		String value = request.getParameter("nominativo");
		EventsDao ed = this.daoFactory.getEventsDao();
		int eventCode = ed.getCode(value);
		Information information = ed.getInformation(eventCode);
		String city = information.getCity();
		String place = information.getLocality();
		List<String> informations = new ArrayList<>();
		informations.add(information.getName());
		informations.add(information.getDescription());
		informations.add(information.getImg());
		informations.add(ed.getFeedback(eventCode));
		informations.add(String.valueOf(eventCode));
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("eventcode", eventCode);
		request.setAttribute("cittaEvento", city);
		request.setAttribute("luogoEvento", place);
		request.setAttribute("list", informations);
		RequestDispatcher id = request.getServletContext().getRequestDispatcher("/content/event.jsp");
		id.forward(request, response);
	}
}
