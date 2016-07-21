package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import core.DaoFactory;
import dao.ClientsDao;
@WebServlet("/sellTicketClient")
public class SellTicketClient extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public SellTicketClient()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.getWriter();
		int ticket = Integer.parseInt(request.getParameter("ticket"));
		int price = Integer.parseInt(request.getParameter("price"));
		DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		ClientsDao clientsDao = daoFactory.getClienteDao();
		clientsDao.sellTicket(ticket, price);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}
}
