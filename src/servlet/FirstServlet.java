package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/prova")
public class FirstServlet extends HttpServlet
{
	private static final long serialVersionUID=1L;
	@Override
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{
		response.setContentType("text/html");
		response.getWriter();
		String n=request.getParameter("username");
		String p=request.getParameter("userpass");
		if (LoginDao.validate(n,p))
		{
			System.out.println("esiste");
		}
		else
		{
			System.out.print("Sorry username or password error");
		}
	}
}
