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
	private String logged="Utente";
	private String tipe="not";
	@Override
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{
		if (req.getParameter("tipe").equals("logIn"))
		{
			this.doPost(req,resp);
		}
		else if (req.getParameter("tipe").equals("name"))
		{
			this.getLogged();
		}
		else if (req.getParameter("tipe").equals("type"))
		{
			this.getTipe();
		}
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
			this.setLogged(n);
			this.setTipe("client");
		}
		else
		{
			trouve=organizator.verifyOrganizator(n,p);
			if (trouve)
			{
				this.setLogged(n);
				this.setTipe("organizator");
			}
		}
		String toReturn=trouve.toString() + "/" + this.tipe + "/" + this.logged;
		String gson=new Gson().toJson(toReturn);
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson);
	}
	public String getLogged()
	{
		return this.logged;
	}
	public String getTipe()
	{
		return this.tipe;
	}
	public void setLogged(String logged)
	{
		this.logged=logged;
	}
	public void setTipe(String tipe)
	{
		this.tipe=tipe;
	}
}
