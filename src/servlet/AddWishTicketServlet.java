package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import dao.TicketDao;
import dao.WishTicketDao;
import tables.Ticket;
import tables.WishTicket;

@WebServlet("/AddWishTicketServlet")
public class AddWishTicketServlet extends HttpServlet {
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
		String type = request.getParameter("type");
		double price = Double.parseDouble(request.getParameter("price"));
		String owner = request.getParameter("owner");
		int listCode = Integer.parseInt(request.getParameter("listcode"));
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		TicketDao td = dao.getTicketDao();
		Ticket ticket = td.searchTicket(type, eventCode, price);
		WishTicketDao wtd = dao.getWishTicketDao();
		WishTicket t = new WishTicket(listCode, ticket.getTicketCode());
		String gson;
		gson = new Gson().toJson(wtd.save(t, owner));
		System.out.println(gson);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
