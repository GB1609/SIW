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
		int ticketCode = Integer.parseInt(request.getParameter("ticketcode"));
		List<Ticket> cart = new CopyOnWriteArrayList<>();
		String gson = new Gson().toJson("FAIL");
		cart.addAll((Collection<? extends Ticket>) request.getSession().getAttribute("carrello"));
		for (Ticket ticket : cart)
			if (ticket.getTicketCode() == ticketCode) {
				cart.remove(ticket);
				gson = new Gson().toJson("DONE");
			}
		request.getSession().setAttribute("carrello", cart);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
