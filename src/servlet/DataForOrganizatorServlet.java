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
import dao.CategoryDao;
import dao.PartecipantsDao;
import dao.PlaceDao;
import dao.TicketDao;
import tables.Partecipants;
import tables.Place;


@WebServlet("/DataForOrganizatorServlet")
public class DataForOrganizatorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String>myList = returnPartecipants();
		List<String> placesList = returnPlaces();
		List<String>cityList = returnCity();
		List<String> tipologyList = tipologyForTickets();
		List<String> category = getAllCategories();
		request.setAttribute("allPartecipants", myList);
		request.setAttribute("allCat", category);
		request.setAttribute("allCities", cityList);
		request.setAttribute("allTickets", tipologyList );
		request.setAttribute("allPlace", placesList );
		RequestDispatcher id = request.getServletContext().getRequestDispatcher("/content/createEvents.jsp");
		id.forward(request,response);

	}

	private List<String> returnPartecipants()
	{
		PartecipantsDao pd = daoFactory.getPartecipanteDao();
		List<Partecipants> partecipants = pd.getAllPartecipants();
		List<String>result = new ArrayList<String>();
		for (int i =0; i<partecipants.size();i++)
		{
			if( !result.contains( partecipants.get(i).getName()) )
			result.add(partecipants.get(i).getName());
		}
		return result;
	}
	
	private List<String> returnCity()
	{
		PlaceDao pd = daoFactory.getPlaceDao();
		List<Place> places = pd.returnAllPlace();
		List<String>result = new ArrayList<String>();
		for (int i =0; i<places.size();i++)
		{
			if (!result.contains(places.get(i).getCity()))
			result.add(places.get(i).getCity());
		}
		return result;
	}
	
	
	private List<String> returnPlaces()
	{
		PlaceDao pd = daoFactory.getPlaceDao();
		List<Place> places = pd.returnAllPlace();
		List<String>result = new ArrayList<String>();
		for (int i =0; i<places.size();i++)
		{
			if (!result.contains(places.get(i).getCity()))
			result.add(places.get(i).getName());
		}
		return result;
	}

	private List<String> tipologyForTickets()
	{
		TicketDao td = daoFactory.getBigliettoDao();
		List<String> result = td.getTipology();
		return result;
	}
	
	private List<String> getAllCategories()
	{
		CategoryDao cd = daoFactory.getCategoriaDao();
		List<String> result = cd.getCategories();
		return result;
	}

}
