package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private static final long serialVersionUID = 1L;
	private String logged = "Utente";
	private String tipe = "not";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{


		if (req.getParameter("tipe").equals("logIn"))
			doPost(req, resp);
		else if (req.getParameter("tipe").equals("name"))
			getLogged();
		else if (req.getParameter("tipe").equals("type"))
			getTipe();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{

		Boolean trouve = false;
		String n = request.getParameter("username");
		String p = request.getParameter("password");

		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		ClientsDao client = dao.getClienteDao();
		OrganizatorDao organizator = dao.getOrganizzatoreDao();
		trouve = client.verifyClients(n, p);
		if (trouve)
		{
			setLogged(n);
			setTipe("client");
		}
		else
		{
			trouve = organizator.verifyOrganizator(n, p);
			if (trouve)
			{
				setLogged(n);
				setTipe("organizator");
			}
		}

		List<String>test = new ArrayList<>();
		test.add(this.tipe);
		test.add(this.logged);

		String gson = new Gson().toJson(test);

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
		this.logged = logged;
	}
	public void setTipe(String tipe)
	{
		this.tipe = tipe;
	}
}
