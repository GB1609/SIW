package core;

import dao.CategoryDao;
import dao.CityDao;
import dao.ClientsDao;
import dao.ClientsWishDao;
import dao.EventPartecipantDao;
import dao.EventsDao;
import dao.InformationDao;
import dao.OrdersDao;
import dao.OrganizatorDao;
import dao.OrganizatorEventsDao;
import dao.PartecipantsDao;
import dao.PlaceDao;
import dao.ReviewDao;
import dao.SubCategoryDao;
import dao.TicketDao;
import dao.UserDao;
import dao.WishListDao;
import dao.WishTicketDao;

public abstract class DaoFactory {
	public static final int HSQLDB = 1;
	public static final int POSTGRESQL = 2;

	public static DaoFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case HSQLDB:
			return null;// new HsqldbDAOFactory();
		case POSTGRESQL:
			return new PostgresDaoFactory();
		default:
			return null;
		}
	}

	public abstract CategoryDao getCategoryDao();

	public abstract CityDao getCityDao();

	public abstract ClientsDao getClientsDao();

	public abstract ClientsWishDao getClientsWishDao();

	public abstract EventPartecipantDao getEventPartecipantDao();

	public abstract EventsDao getEventsDao();

	public abstract InformationDao getInformationDao();

	public abstract OrdersDao getOrdersDao();

	public abstract OrganizatorDao getOrganizatorDao();

	public abstract OrganizatorEventsDao getOrganizatorEventsDao();

	public abstract PartecipantsDao getPartecipantsDao();

	public abstract PlaceDao getPlaceDao();

	public abstract ReviewDao getReviewDao();

	public abstract SubCategoryDao getSubCategoryDao();

	public abstract TicketDao getTicketDao();

	public abstract UserDao getUserDao();

	public abstract WishListDao getWishListDao();

	public abstract WishTicketDao getWishTicketDao();
}
