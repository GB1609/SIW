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
		EventsDao ed = dao.getEventsDao();
		List<Ticket> cart = new CopyOnWriteArrayList<>();
		if (request.getSession().getAttribute("carrello") != null)
			cart.addAll((Collection<? extends Ticket>) request.getSession().getAttribute("carrello"));
		String gson;
		if (!cart.isEmpty()) {
			List<String> list = new CopyOnWriteArrayList<>();
			for (Ticket t : cart)
				list.add(t.getTicketCode() + "_" + t.getType() + "_" + t.getPrice() + "_" + ed.getName(t.getCodeEvent())
				+ "_" + ed.getImg(t.getCodeEvent()));
			gson = new Gson().toJson(list);
		} else
			gson = new Gson().toJson("EMPTY");
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}

}
