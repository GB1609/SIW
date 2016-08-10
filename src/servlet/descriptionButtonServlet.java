package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import core.DataSource;
import dao.EventsDao;
import tables.Information;

/**
 * Servlet implementation class descriptionButtonServlet
 */
@WebServlet("/descriptionButtonServlet")
public class descriptionButtonServlet extends HttpServlet {
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
		EventsDao ed = this.daoFactory.getEventsDao();
		int eventCode = Integer.parseInt(request.getParameter("eventcode"));
		Information information = ed.getInformation(eventCode);
		List<String> informations = new ArrayList<>();
		informations.add(information.getName());
		informations.add(information.getDescription());
		informations.add(information.getImg());
		informations.add(ed.getFeedback(eventCode));
		informations.add(String.valueOf(eventCode));
		String gson = new Gson().toJson(informations);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}

}
