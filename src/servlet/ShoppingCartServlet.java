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
import dao.TicketDao;
import tables.Ticket;

/**
 * Servlet implementation class ShoppingCartServlet
 */
@WebServlet("/ShoppingCartServlet")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Ticket> cart;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShoppingCartServlet() {
		super();
		this.cart = new CopyOnWriteArrayList<>();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter();
		int eventCode = Integer.parseInt(request.getParameter("eventcode"));
		String type = request.getParameter("type");
		double price = Double.parseDouble(request.getParameter("price"));
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		TicketDao td = dao.getTicketDao();
		String gson;
		boolean find = false;
		Ticket t = td.searchTicket(type, eventCode, price);
		for (Ticket ticket : this.cart)
			if ((ticket.getCodeEvent() == t.getCodeEvent()) && (ticket.getType().equals(t.getType()))
					&& (ticket.getPrice() == t.getPrice()))
				find = true;
		if (!find) {
			this.cart.add(td.searchTicket(type, eventCode, price));
			gson = new Gson().toJson("Biglietto Aggiunto al carrello");
			request.getSession().setAttribute("carrello", this.cart);
		} else
			gson = new Gson().toJson("Errore, biglietto già nel carrello!");
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}