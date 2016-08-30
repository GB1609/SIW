package servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import dao.ReviewDao;

@WebServlet("/ShowReviewsServlet")
public class ShowReviewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.getWriter();
		int eventCode = Integer.parseInt(request.getParameter("eventcode"));
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		ReviewDao rd = dao.getReviewDao();
		List<String> list = new CopyOnWriteArrayList<>();
		double media = 0;
		list = rd.searchByEvents(eventCode);
		if (!list.isEmpty()) {
			for (String str : list) {
				String[] spl = str.split("_");
				media += Double.parseDouble(spl[2]);
			}
			media /= list.size();
			media = new BigDecimal(media).setScale(2, BigDecimal.ROUND_UP).doubleValue();
		}
		list.add("media_" + media);
		String gson = new Gson().toJson(list);
		response.setContentType("application/json");
		response.getWriter().write(gson);
	}
}
