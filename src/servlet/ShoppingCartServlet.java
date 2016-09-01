package servlet;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import dao.TicketDao;
import tables.Ticket;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter();
		List<Ticket> cart = new CopyOnWriteArrayList<>();
		int eventCode = Integer.parseInt(request.getParameter("eventcode"));
		String type = request.getParameter("type");
		double price = Double.parseDouble(request.getParameter("price"));
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		TicketDao td = dao.getTicketDao();
		String gson;
		boolean find = false;
		if (request.getSession().getAttribute("carrello") != null)
			cart.addAll((Collection<? extends Ticket>) request.getSession().getAttribute("carrello"));
		Ticket t = td.searchTicket(type, eventCode, price);
		t.setQuantity(1);
		for (Ticket ticket : cart)
			if ((ticket.getCodeEvent() == t.getCodeEvent()) && (ticket.getType().equals(t.getType()))
					&& (ticket.getPrice() == t.getPrice()))
				find = true;
		if (!find) {
			cart.add(t);
			gson = new Gson().toJson("Biglietto Aggiunto al carrello");
			request.getSession().setAttribute("carrello", cart);
		} else
			gson = new Gson().toJson("Errore, biglietto già nel carrello!");
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}