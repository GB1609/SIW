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
		response.getWriter();
		String gson;
		boolean success = true;
		String owner = request.getParameter("owner");
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		TicketDao td = dao.getBigliettoDao();
		OrdersDao od = dao.getOrdineDao();
		List<Ticket> cart = new CopyOnWriteArrayList<>();
		cart.addAll((Collection<? extends Ticket>) request.getSession().getAttribute("carrello"));
		for (int i = 0; i < cart.size(); i++)
			if (!td.getState(cart.get(i).getTicketCode())) {
				Ticket tmp = td.searchTicket(cart.get(i).getType(), cart.get(i).getCodeEvent(), cart.get(i).getPrice());
				if (tmp != null) {
					td.setState(false, tmp.getTicketCode());
					od.save(new Order(cart.get(i).getTicketCode(), owner));
					cart.remove(cart.get(i));
				} else
					success = false;
			} else {
				td.setState(false, cart.get(i).getTicketCode());
				od.save(new Order(cart.get(i).getTicketCode(), owner));
				cart.remove(cart.get(i));
			}
		if (success && request.getSession().getAttribute("tipe")=="client")
			gson = new Gson().toJson("DONE");
		else if (request.getSession().getAttribute("tipe")!="client")
			gson = new Gson().toJson("DEVI LOGGARTI PER POTER COMPLETARE L'ACQUISTO");
		else
			gson = new Gson().toJson("FAIL");
		request.getSession().setAttribute("carrello", cart);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
