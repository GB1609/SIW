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
		this.cart = new ArrayList<>();
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
		TicketDao td = dao.getBigliettoDao();
		EventsDao ed = dao.getEventoDao();
		this.cart.add(td.searchTicket(type, eventCode, price));
		request.getSession().setAttribute("carrello", this.cart);
		List<String> list = new ArrayList<>();
		for (Ticket t : this.cart)
			list.add(t.getTicketCode() + " " + t.getType() + " " + t.getPrice() + " " + ed.getName(t.getCodeEvent()));
		String gson = new Gson().toJson(list);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
