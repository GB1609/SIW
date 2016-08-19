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
import dao.TicketDao;
import tables.EventInfo;
import tables.Events;
import tables.Information;

/**
 * Servlet implementation class RemoveEventServlet
 */
@WebServlet("/RemoveEventServlet")
public class RemoveEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

    public RemoveEventServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String delete = request.getParameter("event");
		
		EventsDao ed = this.daoFactory.getEventsDao();
		ed.deleteAllForOne(ed.getCode(delete), delete);
		String nm = request.getSession().getAttribute("name").toString();
		
		List<Events> lista = ed.organizedEvents(nm);
		InformationDao ids = this.daoFactory.getInformationDao();
		List<EventInfo> eifs = new ArrayList<EventInfo>();
		for (int i = 0; i < lista.size(); i++) {
			Set<Information> informations = ids.getAllInfo(lista.get(i).getInformation());
			for (Information info : informations) {
				eifs.add(new EventInfo(lista.get(i).getEventcode(), info.getName(),info.getCity(),lista.get(i).getNumBigl(),lista.get(i).getRemBigl()));
				break;
			}

		}
		request.setAttribute("eventi", eifs);
		RequestDispatcher id = request.getServletContext().getRequestDispatcher("/content/myPage.jsp");
		id.forward(request, response);
	}

}
