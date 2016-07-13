package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.DaoFactory;
import dao.InformationDao;
import tables.Information;

@WebServlet("/AddInformationServlet")
public class AddInformationServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter();
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int dayOfMonth = Integer.parseInt(request.getParameter("dayofmonth"));
		String place = request.getParameter("place");
		String description = request.getParameter("description");
		String city = request.getParameter("city");
		Information info = new Information(LocalDate.of(year, month, dayOfMonth), place, description, city);
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		InformationDao id = dao.getInformationDao();
		id.save(info);
	}
}
