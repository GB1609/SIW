package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import core.DaoFactory;
import core.DataSource;
import dao.EventsDao;
import dao.InformationDao;
import tables.Events;
import tables.Information;

@WebServlet("/SearchEvents")
public class SearchEvents extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

	public SearchEvents() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String value="";
		String typeOfRequest = request.getParameter("typeOfResearch");
		if(typeOfRequest!="All")
		value = request.getParameter("value");
		List<String> hy = new ArrayList<String>();
		switch (typeOfRequest) {
		case "Category":
			searchByCategory(value);
			break;
		case "Place":
			searchByPlace(value);
			break;
		case "Partecipants":
			searchByPartecipant(value);
			hy = searchByPartecipant(value);
			break;
		case "Date":
			searchByDate(value);
			break;
		case "Price Min":
			try {
				searchByPriceMin(Double.parseDouble(value));
			} catch (NumberFormatException e) {
				hy.add("formato non valido");
			}
			break;
		case "Price Max":
			try {
				searchByPriceMax(Double.parseDouble(value));
			} catch (NumberFormatException e) {
				hy.add("formato non valido");
			}
			break;
		case "All" :
			hy=returnAllEvents();
			break;
			
		default:
			hy.add("nessun risultato prodotto");
			break;
		}

		if (hy.isEmpty()) {
			hy.add("nessun risultato prodotto");
		}
		
	
		request.setAttribute("eventList", hy);
		RequestDispatcher id = request.getServletContext().getRequestDispatcher("/content/allEvents.jsp");
		id.forward(request,response);
	}

	private List<String> searchByPriceMin(double value) {
		return null;

	}

	private List<String> searchByPriceMax(double i) {
		
		EventsDao ed = daoFactory.getEventoDao();
		Set<Events> events = ed.searchByPrice(i, true);
		List<String> hy = new ArrayList<String>();
		return hy;

	}

	private List<String> searchByDate(String value) {
		return null;

	}

	private List<String> searchByCategory(String value) {
		return null;

	}

	private List<String> searchByPlace(String locality) {
		return null;

	}
	
	private List<String> returnAllEvents()
	{
		Information myInfo = null;
		InformationDao id = daoFactory.getInformationDao();
		EventsDao ed = daoFactory.getEventoDao();
		Set<Events> events = ed.returnAllEvents();
		List<String>result = new ArrayList<String>();
		for (Events e : events)
		{
//			Set<Information> informations = id.getAllInfo(e.getInformation());
//			for (Information i : informations)
//			{
//				myInfo = i;
//				break;
//			}
//			
//			result.add(myInfo.getName() + " "+ myInfo.getDescription() +" "+myInfo.getCity() + " " + myInfo.getLocality() + " "+ myInfo.getImg());
		result.add(" "+e.getEventcode());
		}
		return result;
	}

	private List<String> searchByPartecipant(String value) {
		EventsDao ed = daoFactory.getEventoDao();
		Set<Events> events = ed.searchByPartecipants(Integer.parseInt(value));
		List<String> hy = new ArrayList<String>();
		for (Events e : events) {
			String s = e.getEventcode() + " " + e.getPartecipant() + " " + e.getCategory() + " " + e.getOrganizator()
					+ " " + e.getFeedback();
			hy.add(s);
			// System.out.println(s);
		}
		return hy;
	}
}
