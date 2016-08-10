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
import dao.TicketDao;
import dao.WishTicketDao;
import tables.Ticket;

/**
 * Servlet implementation class showWishTicket
 */
@WebServlet("/showWishTicket")
public class showWishTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public showWishTicket() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter();
		int listCode = Integer.parseInt(request.getParameter("listcode"));
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		WishTicketDao wtd = dao.getWishTicketDao();
		TicketDao td = dao.getTicketDao();
		EventsDao ed = dao.getEventsDao();
		List<String> list = new ArrayList<>();
		list = wtd.searchByWishList(listCode);
		List<String> wishTicket = new ArrayList<>();
		String gson;
		for (String ticketCode : list) {
			Ticket t = td.getTicket(Integer.parseInt(ticketCode));
			wishTicket.add(t.getType() + "_" + t.getPrice() + "_" + ed.getName(t.getCodeEvent()));
		}
		if (!wishTicket.isEmpty())
			gson = new Gson().toJson(wishTicket);
		else
			gson = new Gson().toJson("EMPTY");
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}

}
