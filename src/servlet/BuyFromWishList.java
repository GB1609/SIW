package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import dao.TicketDao;
import dao.WishTicketDao;
import tables.Ticket;
import tables.WishTicket;

/**
 * Servlet implementation class BuyFromWishList
 */
@WebServlet("/BuyFromWishList")
public class BuyFromWishList extends HttpServlet {
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
		int ticketcode = Integer.parseInt(request.getParameter("ticketcode"));
		int listCode = Integer.parseInt(request.getParameter("listcode"));
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		WishTicketDao wtd = dao.getWishTicketDao();
		WishTicket t = new WishTicket(listCode, ticketcode);
		TicketDao td = dao.getTicketDao();
		List<Ticket> cart = new ArrayList<>();
		if ((Collection<? extends Ticket>) request.getSession().getAttribute("carrello") != null)
			cart.addAll((Collection<? extends Ticket>) request.getSession().getAttribute("carrello"));
		String gson;
		Ticket tk = td.getTicket(ticketcode);
		tk.setQuantity(1);
		if (cart.add(tk)) {
			wtd.delete(t);
			gson = new Gson().toJson("DONE");
		} else
			gson = new Gson().toJson("FAIL");
		request.getSession().setAttribute("carrello", cart);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
