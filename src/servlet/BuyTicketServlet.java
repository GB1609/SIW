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
import dao.OrdersDao;
import dao.TicketDao;
import tables.Order;
import tables.Ticket;

@WebServlet("/BuyTicketServlet")
public class BuyTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		System.out.println("PERCHé");
		response.getWriter();
		String gson;
		boolean success = true;
		String owner = request.getParameter("owner");
		int ticketCode = Integer.parseInt(request.getParameter("ticket"));
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		TicketDao td = dao.getBigliettoDao();
		OrdersDao od = dao.getOrdineDao();
		// Ticket t = td.getTicket(ticketCode);
		List<Ticket> cart = new CopyOnWriteArrayList<>();
		cart.addAll((Collection<? extends Ticket>) request.getSession().getAttribute("carrello"));
		for (Ticket t : cart)
			if (!td.getState(ticketCode)) {
				Ticket tmp = td.searchTicket(t.getType(), t.getCodeEvent(), t.getPrice());
				if (tmp != null) {
					td.setState(false, tmp.getTicketCode());
					od.save(new Order(t.getTicketCode(), owner));
					cart.remove(t);
				} else
					success = false;
			} else {
				td.setState(false, t.getTicketCode());
				od.save(new Order(t.getTicketCode(), owner));
				cart.remove(t);
			}
		if (success)
			gson = new Gson().toJson("success");
		else
			gson = new Gson().toJson("error");
		request.getSession().setAttribute("carrello", cart);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
