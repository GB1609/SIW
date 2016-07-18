package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.DaoFactory;
import dao.WishTicketDao;
import tables.WishTicket;

@WebServlet("/AddWishTicketServlet")
public class AddWishTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter();
		String owner = request.getParameter("owner");
		int listCode = Integer.parseInt(request.getParameter("listcode"));
		int ticketCode = Integer.parseInt(request.getParameter("ticketcode"));
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		WishTicketDao wtd = dao.getBigliettiDesideratiDao();
		wtd.save(new WishTicket(listCode, ticketCode), owner);
	}
}
