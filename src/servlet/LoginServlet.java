package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import core.DaoFactory;
import dao.ClientsDao;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet
{
	private static final long serialVersionUID=1L;
	@Override
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		response.getWriter();
		String n=request.getParameter("username");
		String p=request.getParameter("userpass");
		DaoFactory dao=DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		ClientsDao i=dao.getClienteDao();
		if (i.verifyClients(n,p))
		{
			System.out.println("esiste");
		}
		else
		{
			System.out.print("Sorry username or password error");
		}
	}
	@Override
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		this.doGet(request,response);
	}
}
