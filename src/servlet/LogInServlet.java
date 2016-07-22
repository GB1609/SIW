package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import core.DaoFactory;
import dao.ClientsDao;
import dao.OrganizatorDao;
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet
{
	private static final long serialVersionUID=1L;
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{
		this.doPost(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
	
		Boolean trouve=false;
		String n=request.getParameter("username");
		String p=request.getParameter("password");
		DaoFactory dao=DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		ClientsDao client=dao.getClienteDao();
		OrganizatorDao organizator=dao.getOrganizzatoreDao();
		trouve=client.verifyClients(n,p);
		if (trouve)
		{
			request.getSession().setAttribute("name",n);
			request.getSession().setAttribute("tipe","client");
		}
		else
		{
			trouve=organizator.verifyOrganizator(n,p);
			if (trouve)
			{
				request.getSession().setAttribute("name",n);
				request.getSession().setAttribute("tipe","organizator");
			}
		}
		String toReturn=trouve.toString() + "/" + request.getSession().getAttribute("tipe") + "/" + request.getSession().getAttribute("name");
		String gson=new Gson().toJson(toReturn);
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson);
	}
}
