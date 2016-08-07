package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.DaoFactory;
import core.DataSource;
import dao.CityDao;
import dao.PartecipantsDao;
import dao.PlaceDao;
import dao.SubCategoryDao;
import dao.TicketDao;
import tables.City;
import tables.Partecipants;
import tables.Place;

@WebServlet("/DataForOrganizatorServlet")
public class DataForOrganizatorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<String> myList = returnPartecipants();
		List<String> placesList = returnPlaces();
		List<String> cityList = returnCity();
		List<String> tipologyList = tipologyForTickets();
		List<String> category = getAllCategories();
		request.setAttribute("allPartecipants", myList);
		request.setAttribute("allCat", category);
		request.setAttribute("allCities", cityList);
		request.setAttribute("allTickets", tipologyList);
		request.setAttribute("allPlace", placesList);
		RequestDispatcher id = request.getServletContext().getRequestDispatcher("/content/createEvents.jsp");
		id.forward(request, response);

	}

	private List<String> getAllCategories() {
		SubCategoryDao cd = this.daoFactory.getSubCategoryDao();
		List<String> result = cd.getSubCategories();
		return result;
	}

	private List<String> returnCity() {
		CityDao cd = this.daoFactory.getCityDao();
		List<City> cities = cd.getAllCities();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < cities.size(); i++)
			if (!result.contains(cities.get(i).getName()))
				result.add(cities.get(i).getName());
		return result;
	}

	private List<String> returnPartecipants() {
		PartecipantsDao pd = this.daoFactory.getPartecipantsDao();
		List<Partecipants> partecipants = pd.getAllPartecipants();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < partecipants.size(); i++)
			if (!result.contains(partecipants.get(i).getName()))
				result.add(partecipants.get(i).getName());
		return result;
	}

	private List<String> returnPlaces() {
		PlaceDao pd = this.daoFactory.getPlaceDao();
		List<Place> places = pd.returnAllPlace();
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < places.size(); i++)
			if (!result.contains(places.get(i).getCity()))
				result.add(places.get(i).getName());
		return result;
	}

	private List<String> tipologyForTickets() {
		TicketDao td = this.daoFactory.getTicketDao();
		List<String> result = td.getTipology();
		return result;
	}

}
