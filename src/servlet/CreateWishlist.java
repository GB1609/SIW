package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import dao.WishListDao;
import tables.WishList;

@WebServlet("/CreateWishlistServlet")
public class CreateWishlist extends HttpServlet {
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
		String name = request.getParameter("name");
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		WishListDao wld = dao.getWishListDao();
		String gson;
		if (wld.save(new WishList(owner, name)))
			gson = new Gson().toJson("DONE");
		else
			gson = new Gson().toJson("FAIL");
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
