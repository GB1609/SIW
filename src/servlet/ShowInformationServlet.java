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
import dao.EventsDao;
import tables.Information;

@WebServlet("/ShowInformationServlet")
public class ShowInformationServlet extends HttpServlet {
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
		EventsDao ed = dao.getEventoDao();
		Information information = ed.getInformation(eventCode);
		List<String> informations = new ArrayList<>();
		informations.add(information.getName());
		informations.add(information.getDescription());
		informations.add(information.getImg());
		informations.add(ed.getFeedback(eventCode));
		String gson = new Gson().toJson(informations);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
