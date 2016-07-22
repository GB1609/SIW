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
import tables.Ticket;

/**
 * Servlet implementation class ShowCartServlet
 */
@WebServlet("/ShowCartServlet")
public class ShowCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowCartServlet() {
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
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		dao.getBigliettoDao();
		EventsDao ed = dao.getEventoDao();
		List<Ticket> cart = new ArrayList<>();
		if (request.getSession().getAttribute("carrello") != null)
			cart.addAll((Collection<? extends Ticket>) request.getSession().getAttribute("carrello"));
		List<String> list = new CopyOnWriteArrayList<>();
		for (Ticket t : cart)
			list.add(t.getTicketCode() + " " + t.getType() + " " + t.getPrice() + " " + ed.getName(t.getCodeEvent()));
		String gson = new Gson().toJson(list);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}

}
