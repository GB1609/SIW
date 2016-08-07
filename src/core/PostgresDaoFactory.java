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

public class PostgresDaoFactory extends DaoFactory {
	public static final String DRIVER = "org.postgresql.Driver";
	private static String DBURL = "jdbc:postgresql://52.39.164.176:5432/db_161168";
	private static String USERNAME = "u_161168";
	private static String PASSWORD = "p@wd_161168";
	private static DataSource dataSource;
	static {
		try {
			Class.forName(DRIVER).newInstance();
			dataSource = new DataSource(DBURL, USERNAME, PASSWORD);
		} catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}

	@Override
	public CategoryDao getCategoryDao() {
		return new CategoryDaoJDBC(dataSource);
	}

	@Override
	public CityDao getCityDao() {
		return new CityDaoJDBC(dataSource);
	}

	@Override
	public ClientsDao getClientsDao() {
		return new ClientDaoJDBC(dataSource);
	}

	@Override
	public ClientsWishDao getClientsWishDao() {
		return new ClientsWishDaoJDBC(dataSource);
	}

	@Override
	public EventPartecipantDao getEventPartecipantDao() {
		return new EventPartecipantJDBC(dataSource);
	}

	@Override
	public EventsDao getEventsDao() {
		return new EventDaoJDBC(dataSource);
	}

	@Override
	public InformationDao getInformationDao() {
		return new InformationDaoJDBC(dataSource);
	}

	@Override
	public OrdersDao getOrdersDao() {
		return new OrdersDaoJDBC(dataSource);
	}

	@Override
	public OrganizatorDao getOrganizatorDao() {
		return new OrganizatorDaoJDBC(dataSource);
	}

	@Override
	public OrganizatorEventsDao getOrganizatorEventsDao() {
		return new OrganizatorEventsDaoJDBC(dataSource);
	}

	@Override
	public PartecipantsDao getPartecipantsDao() {
		return new PartecipantsDaoJDBC(dataSource);
	}

	@Override
	public PlaceDao getPlaceDao() {
		return new PlaceDaoJDBC(dataSource);
	}

	@Override
	public ReviewDao getReviewDao() {
		return new ReviewDaoJDBC(dataSource);
	}

	@Override
	public SubCategoryDao getSubCategoryDao() {
		return new SubCategoryDaoJDBC(dataSource);
	}

	@Override
	public TicketDao getTicketDao() {
		return new TicketDaoJDBC(dataSource);
	}

	@Override
	public UserDao getUserDao() {
		return new UserDaoJDBC(dataSource);
	}

	@Override
	public WishListDao getWishListDao() {
		return new WishListDaoJDBC(dataSource);
	}

	@Override
	public WishTicketDao getWishTicketDao() {
		return new WishTicketDaoJDBC(dataSource);
	}
}
