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

import com.google.gson.Gson;

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


@WebServlet("/AddObjectServlet")
public class AddObjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected DataSource datSource;
	protected DaoFactory daoFactory = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL); 
    public AddObjectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String type = request.getParameter("type");
		if (type.equals("city"))
		{
			
		String citta = request.getParameter("citta");
		CityDao cd = daoFactory.getCityDao();
		try{
		cd.save(new City(citta));}
		
		catch(Exception e){
			//doNothing, ci sarà un fail nella richiesta ajax notificata, perchè la
			//chiave è gia presente nel db
		}
		}
		
		else if (type.equals("partecipant"))
		{
			String name = request.getParameter("partName");
			String city = request.getParameter("partCity");
			String typeP = request.getParameter("partType");
			PartecipantsDao pd = daoFactory.getPartecipantsDao();
			pd.save(new Partecipants(name, typeP, city));
		}
		
		else if (type.equals("place"))
		{
			CityDao cd = daoFactory.getCityDao();
			String city = request.getParameter("placeCity");
			if(!cd.containCity(city)){
				cd.save(new City(city));
			}
			String name = request.getParameter("placeName");
			String tipe = request.getParameter("placeType");
			try{
			int capacity = Integer.parseInt(request.getParameter("placeCapacity"));
			PlaceDao pd = daoFactory.getPlaceDao();
			pd.save(new Place(capacity, name, city, tipe));
			
			}catch(Exception e)
			{
				//doNothing
			}
			
			
			
		}
		
		
		
		String gson = new Gson().toJson("Dati inseriti correttamente");
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(gson);
	}

	
	
}
