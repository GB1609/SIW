package servlet;

import java.io.IOException;
import java.util.ArrayList;
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
import dao.EventsDao;
import dao.TicketDao;
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
		String errorMessage = "";
		boolean success = true;
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		TicketDao td = dao.getTicketDao();
		EventsDao ed = dao.getEventsDao();
		List<Ticket> cart = new CopyOnWriteArrayList<>();
		cart.addAll((Collection<? extends Ticket>) request.getSession().getAttribute("carrello"));
		List<Ticket> removeTicket = new ArrayList<>();
		for (int i = 0; i < cart.size(); i++) {
			for (int quant = 0; quant < cart.get(i).getQuantity(); quant++) {
				Ticket tmp = td.searchTicket(cart.get(i).getType(), cart.get(i).getCodeEvent(), cart.get(i).getPrice());
				if ((tmp != null) && (ed.getRemainTicket(cart.get(i).getCodeEvent()) > cart.get(i).getQuantity())) {
					removeTicket.add(tmp);
					td.setState(false, tmp.getTicketCode());
				} else
					success = false;
			}
			if (success) {
				int eventCode = cart.get(i).getCodeEvent();
				int ticketQuantity = cart.get(i).getQuantity();
				ed.updateTicketsNumber(eventCode, ticketQuantity);
				cart.remove(cart.get(i));
			} else {
				errorMessage = "Non ci sono abbastanza biglietti selezionati";
				for (Ticket ticket : removeTicket)
					td.setState(true, ticket.getTicketCode());
			}
		}
		if (success && (request.getSession().getAttribute("tipe") == "client"))
			gson = new Gson().toJson("DONE");
		else if (request.getSession().getAttribute("tipe") != "client")
			gson = new Gson().toJson("DEVI LOGGARTI PER POTER COMPLETARE L'ACQUISTO");
		else
			gson = new Gson().toJson(errorMessage);
		request.getSession().setAttribute("carrello", cart);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
