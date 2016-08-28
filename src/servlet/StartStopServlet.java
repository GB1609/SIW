package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import core.DaoFactory;
import core.DataSource;
import dao.EventsDao;
import tables.Events;

@WebServlet("/StartStopServlet")
public class StartStopServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
	
    public StartStopServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String messaggio ="";
		String name = request.getParameter("evento");
		EventsDao ed = daoFactory.getEventsDao();
		boolean value = ed.getEventState(name);
		if (value == true)
		{
			ed.stopSeller(ed.getCode(name));
			messaggio = "vendita fermata";
		}
		else
		{
			ed.startSeller(ed.getCode(name));
			messaggio = "vendita avviata";
		}
		

		String s = new Gson().toJson(messaggio);
		response.setContentType("application/json");
		response.getWriter().write(s);
		
	}

}
