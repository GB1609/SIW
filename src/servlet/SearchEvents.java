package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.DaoFactory;
import core.DataSource;
import dao.EventsDao;
import dao.InformationDao;
import tables.Events;
import tables.Information;

@WebServlet("/SearchEvents")
public class SearchEvents extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

	public SearchEvents() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Information>hy2=returnAllEvents();
		request.setAttribute("eventList", hy2);
		RequestDispatcher id = request.getServletContext().getRequestDispatcher("/content/allEvents.jsp");
		id.forward(request,response);
	}

	private List<Information> returnAllEvents()
	{
		Information myInfo = null;
		InformationDao id = this.daoFactory.getInformationDao();
		EventsDao ed = this.daoFactory.getEventoDao();
		List<Events> events = ed.returnAllEvents();
		List<Information>result = new ArrayList<Information>();
		for (int i =0; i<events.size(); i++)
		{
			Set<Information> informations = id.getAllInfo(events.get(i).getInformation());
			for (Information info : informations)
			{
				myInfo = info;
				result.add(myInfo);
				break;
			}

		}
		return result;
	}


}
