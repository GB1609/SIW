package servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.DaoFactory;
import core.DataSource;
import dao.EventsDao;
import dao.InformationDao;
import tables.Events;
import tables.Information;

/**
 * Servlet implementation class ParameterSearchEvents
 */
@WebServlet("/ParameterSearchEvents")
public class ParameterSearchEvents extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

	private List<Information> searchByPriceMin(double value) {
		EventsDao ed = daoFactory.getEventoDao();
		Information myInfo = null;
		InformationDao id = daoFactory.getInformationDao();
		List<Events> events = ed.searchByPrice(value, false);
		List<Information> result = new ArrayList<Information>();
		for (int i = 0; i < events.size(); i++) {
			Set<Information> informations = id.getAllInfo(events.get(i).getInformation());
			for (Information info : informations) {
				myInfo = info;
				result.add(myInfo);
				break;
			}
		}
		return result;


	}

	private List<Information> searchByPriceMax(double value) {
		EventsDao ed = daoFactory.getEventoDao();
		Information myInfo = null;
		InformationDao id = daoFactory.getInformationDao();
		List<Events> events = ed.searchByPrice(value, true);
		List<Information> result = new ArrayList<Information>();
		for (int i = 0; i < events.size(); i++) {
			Set<Information> informations = id.getAllInfo(events.get(i).getInformation());
			for (Information info : informations) {
				myInfo = info;
				result.add(myInfo);
				break;
			}
		}
		return result;

	}

	private List<Information> searchByDate(String value) {
		EventsDao ed = daoFactory.getEventoDao();
		Information myInfo = null;
		InformationDao id = daoFactory.getInformationDao();
		List<Events> events = ed.searchByDate(LocalDate.parse(value));		
		List<Information> result = new ArrayList<Information>();
		for (int i = 0; i < events.size(); i++) {
			Set<Information> informations = id.getAllInfo(events.get(i).getInformation());
			for (Information info : informations) {
				myInfo = info;
				result.add(myInfo);
				break;
			}
		}
		return result;


	}

	private List<Information> searchByCategory(String value) {
		EventsDao ed = daoFactory.getEventoDao();
		Information myInfo = null;
		InformationDao id = daoFactory.getInformationDao();
		List<Events> events = ed.searchByCategory(value);
		List<Information> result = new ArrayList<Information>();
		for (int i = 0; i < events.size(); i++) {
			Set<Information> informations = id.getAllInfo(events.get(i).getInformation());
			for (Information info : informations) {
				myInfo = info;
				result.add(myInfo);
				break;
			}
		}
		return result;

	}

	private List<Information> searchByPlace(String locality) {
		EventsDao ed = daoFactory.getEventoDao();
		Information myInfo = null;
		InformationDao id = daoFactory.getInformationDao();
		List<Events> events = ed.searchByPlace(locality);
		List<Information> result = new ArrayList<Information>();
		for (int i = 0; i < events.size(); i++) {
			Set<Information> informations = id.getAllInfo(events.get(i).getInformation());
			for (Information info : informations) {
				myInfo = info;
				result.add(myInfo);
				break;
			}
		}
		return result;

	}

	private List<Information> searchByOrganizator (String value)
	{
		EventsDao ed = daoFactory.getEventoDao();
		Information myInfo = null;
		InformationDao id = daoFactory.getInformationDao();
		List<Events> events = ed.searchByOrganizator(value);
		List<Information> result = new ArrayList<Information>();
		for (int i = 0; i < events.size(); i++) {
			Set<Information> informations = id.getAllInfo(events.get(i).getInformation());
			for (Information info : informations) {
				myInfo = info;
				result.add(myInfo);
				break;
			}
		}
		return result;
	}
	
	private List<Information> searchByPartecipant(String value) {
		EventsDao ed = daoFactory.getEventoDao();
		Information myInfo = null;
		InformationDao id = daoFactory.getInformationDao();
		List<Events> events = ed.searchByPartecipants(value);
		List<Information> result = new ArrayList<Information>();
		for (int i = 0; i < events.size(); i++) {
			Set<Information> informations = id.getAllInfo(events.get(i).getInformation());
			for (Information info : informations) {
				myInfo = info;
				result.add(myInfo);
				break;
			}
		}
		return result;
	}
	
	
	
	private List<Information> searchByName(String value) {
		EventsDao ed = daoFactory.getEventoDao();
		Information myInfo = null;
		InformationDao id = daoFactory.getInformationDao();
		List<Events> events = ed.searchByName(value);
		List<Information> result = new ArrayList<Information>();
		for (int i = 0; i < events.size(); i++) {
			Set<Information> informations = id.getAllInfo(events.get(i).getInformation());
			for (Information info : informations) {
				myInfo = info;
				result.add(myInfo);
				break;
			}
		}
		return result;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String value = request.getParameter("value");
		String typeOfRequest = request.getParameter("selezione");
		System.out.println(value);
		List<String> hy = new ArrayList<String>();
		List<Information> hy2 = new ArrayList<Information>();
		switch (typeOfRequest) {
		case "Category":
			hy2=searchByCategory(value);
			break;
		case "Organizator":
			hy2=searchByOrganizator(value);
		case "Name":
			hy2=searchByName(value);
			break;
		case "Place":
			hy2 = searchByPlace(value);
			break;
		case "Partecipants":
			hy2 = searchByPartecipant(value);
			break;
		case "Date":
			hy2 = searchByDate(value);
			break;
		case "Price Min":
			try {
				hy2=searchByPriceMin(Double.parseDouble(value));
			} catch (NumberFormatException e) {
				hy.add("Formato non valido: inserire un valore numerico");
			}
			break;
		case "Price Max":
			try {
				hy2=searchByPriceMax(Double.parseDouble(value));
			} catch (NumberFormatException e) {
				hy.add("Formato non valido: inserire un valore numerico");
			}
			break;

		}
		if (hy2.isEmpty())
		{
			hy.add("La ricerca non ha prodotto nessun risultato");
		}
		
		request.setAttribute("eventList", hy2);
		request.setAttribute("emptyList", hy);
		RequestDispatcher id = request.getServletContext().getRequestDispatcher("/content/allEvents.jsp");
		id.forward(request, response);
	}

}
