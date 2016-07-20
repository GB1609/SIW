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
import dao.TicketDao;
import tables.Ticket;

@WebServlet("/BuyTicketServlet")
public class BuyTicketServlet extends HttpServlet {
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
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		TicketDao td = dao.getBigliettoDao();
		List<String> list = new ArrayList<>();
		Ticket ticket = td.searchTicket(type, eventCode, price);
		list.add("CODE" + String.valueOf(ticket.getTicketCode()));
		list.add("EVENT" + String.valueOf(ticket.getCodeEvent()));
		list.add("Price" + String.valueOf(ticket.getPrice()));
		for (String str : list)
			System.out.println(str + " ");
		td.setState(false, ticket.getTicketCode());
		String gson = new Gson().toJson(list);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
