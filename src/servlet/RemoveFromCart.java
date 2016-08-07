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
 * Servlet implementation class removeFromCart
 */
@WebServlet("/removeFromCart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveFromCart() {
		super();
		// TODO Auto-generated constructor stub
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
	@SuppressWarnings("unchecked")
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
		Ticket t = td.searchTicket(type, eventCode, price);
		List<Ticket> cart = new CopyOnWriteArrayList<>();
		String gson = new Gson().toJson("FAIL");
		cart.addAll((Collection<? extends Ticket>) request.getSession().getAttribute("carrello"));
		for (int i = 0; i < cart.size(); i++)
			if (cart.get(i).getTicketCode() == t.getTicketCode()) {
				cart.remove(i);
				gson = new Gson().toJson("DONE");
			}
		request.getSession().setAttribute("carrello", cart);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
