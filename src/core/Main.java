package core;

import dao.CategoryDao;
import dao.CityDao;
import dao.SubCategoryDao;
import dao.TicketDao;
import tables.Category;
import tables.City;
import tables.SubCategory;
import tables.Ticket;

public class Main {
	public static void main(String[] args) {
		System.out.println("main start");
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);

		/////////////////////

	}

}
