package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import core.DaoFactory;
import dao.ClientsDao;
import tables.Ticket;
@WebServlet("/SearchTicketBuyed")
public class SearchTicketBuyed extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	public SearchTicketBuyed()
	{
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html");
		response.getWriter();
		String userName=request.getParameter("username");
		DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		ClientsDao clientsDao = daoFactory.getClienteDao();
		Set <Ticket> toReturn=clientsDao.ticketBuyed(userName);
		List<String> list = new ArrayList<>();
		for(Ticket t: toReturn){
			list.add(t.getTicketCode() + " " + t.getCodeEvent() + " " + t.getPrice());
		}
		String json=new Gson().toJson(list);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doGet(request, response);
	}
}
