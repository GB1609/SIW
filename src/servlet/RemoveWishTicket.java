package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import dao.WishTicketDao;
import tables.WishTicket;

/**
 * Servlet implementation class RemoveWishTicket
 */
@WebServlet("/RemoveWishTicket")
public class RemoveWishTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter();
		int ticketCode = Integer.parseInt(request.getParameter("ticketcode"));
		int listCode = Integer.parseInt(request.getParameter("listcode"));
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		WishTicketDao wtd = dao.getWishTicketDao();
		WishTicket t = new WishTicket(listCode, ticketCode);
		String gson;
		if (wtd.delete(t))
			gson = new Gson().toJson("DONE");
		else
			gson = new Gson().toJson("FAIL");
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}