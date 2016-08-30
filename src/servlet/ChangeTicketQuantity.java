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
 * Servlet implementation class ChangeTicketQuantity
 */
@WebServlet("/ChangeTicketQuantity")
public class ChangeTicketQuantity extends HttpServlet {
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
		String gson;
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		int ticketCode = Integer.parseInt(request.getParameter("ticketcode"));
		boolean success = false;
		List<Ticket> cart = new CopyOnWriteArrayList<>();
		Ticket t;
		cart.addAll((Collection<? extends Ticket>) request.getSession().getAttribute("carrello"));
		for (Ticket ticket : cart)
			if (ticket.getTicketCode() == ticketCode) {
				t = ticket;
				cart.remove(ticket);
				t.setQuantity(quantity);
				cart.add(t);
				success = true;
			}
		if (!success)
			gson = new Gson().toJson("FAIL");
		else
			gson = new Gson().toJson("SUCCESS");
		request.getSession().setAttribute("carrello", cart);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
