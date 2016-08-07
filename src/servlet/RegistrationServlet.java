package servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import dao.ClientsDao;
import tables.Clients;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistrationServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String result = "exist";
		response.setContentType("text/html");
		response.getWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String address = request.getParameter("address");
		String birthdate = request.getParameter("birth");

		DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		ClientsDao clientsDao = daoFactory.getClientsDao();
		if (!clientsDao.existUser(username)) {
			result = "notExist";
			clientsDao.save(
					new Clients(username, password, last_name, first_name, LocalDate.parse(birthdate), address, 0));
		}
		String json = new Gson().toJson(result);
		response.setContentType("application/json");
		response.getWriter().write(json);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}
}
