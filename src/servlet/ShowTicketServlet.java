package servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import dao.EventsDao;
import dao.TicketDao;

@WebServlet("/ShowTicketServlet")
public class ShowTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter();
		int eventCode = Integer.parseInt(request.getParameter("eventcode"));
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		EventsDao ed = dao.getEventsDao();
		TicketDao td = dao.getTicketDao();
		String gson;
		if (ed.getEventState(ed.getName(eventCode))) {
			List<String> list = new CopyOnWriteArrayList<>();
			list = td.searchByEvents(eventCode);
			gson = new Gson().toJson(list);
		} else
			gson = new Gson().toJson("");
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
