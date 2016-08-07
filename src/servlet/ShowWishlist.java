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
import dao.WishListDao;

@WebServlet("/ShowWishlistServlet")
public class ShowWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter();
		String owner = request.getParameter("owner");
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		WishListDao wld = dao.getWishListDao();
		List<String> list = new ArrayList<>();
		list = wld.searchByOwner(owner);
		String gson;
		if (!list.isEmpty())
			gson = new Gson().toJson(list);
		else
			gson = new Gson().toJson("EMPTY");
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
