package core;
import dao.CategoryDao;
import dao.ClientsDao;
import dao.ClientsWishDao;
import dao.EventsDao;
import dao.InformationDao;
import dao.OrdersDao;
import dao.OrganizatorDao;
import dao.OrganizatorEventsDao;
import dao.PartecipantsDao;
import dao.PartecipantsOfEventsDao;
import dao.PlaceDao;
import dao.ReviewDao;
import dao.TicketDao;
import dao.UserDao;
import dao.WishListDao;
import dao.WishTicketDao;
public class PostgresDaoFactory extends DaoFactory
{
	public static final String DRIVER="org.postgresql.Driver";
	private static String DBURL="jdbc:postgresql://52.39.164.176:5432/db_161168";
	private static String USERNAME="u_161168";
	private static String PASSWORD="p@wd_161168";
	private static DataSource dataSource;
	static
	{
		try
		{
			Class.forName(DRIVER).newInstance();
			dataSource=new DataSource(DBURL,USERNAME,PASSWORD);
		}
		catch (Exception e)
		{
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}
	@Override
	public WishTicketDao getBigliettiDesideratiDao()
	{
		return new WishTicketDaoJDBC(dataSource);
	}
	@Override
	public TicketDao getBigliettoDao()
	{
		return new TicketDaoJDBC(dataSource);
	}
	@Override
	public CategoryDao getCategoriaDao()
	{
		return new CategoryDaoJDBC(dataSource);
	}
	@Override
	public ClientsDao getClienteDao()
	{
		return new ClientDaoJDBC(dataSource);
	}
	@Override
	public ClientsWishDao getClienteDesideraDao()
	{
		return new ClientsWishDaoJDBC(dataSource);
	}
	@Override
	public OrganizatorEventsDao getEventiOrganizzatiDao()
	{
		return new OrganizatorEventsDaoJDBC(dataSource);
	}
	@Override
	public EventsDao getEventoDao()
	{
		return new EventDaoJDBC(dataSource);
	}
	@Override
	public InformationDao getInformationDao()
	{
		return new InformationDaoJDBC(dataSource);
	}
	@Override
	public WishListDao getListaDesideriDao()
	{
		return new WishListDaoJDBC(dataSource);
	}
	@Override
	public OrdersDao getOrdineDao()
	{
		return new OrdersDaoJDBC(dataSource);
	}
	@Override
	public OrganizatorDao getOrganizzatoreDao()
	{
		return new OrganizatorDaoJDBC(dataSource);
	}
	@Override
	public PartecipantsDao getPartecipanteDao()
	{
		return new PartecipantsDaoJDBC(dataSource);
	}
	@Override
	public PartecipantsOfEventsDao getPartecipantiAdEventi()
	{
		return new PartecipantsOfEventsJDBC(dataSource);
	}
	@Override
	public PlaceDao getPlaceDao()
	{
		return new PlaceDaoJDBC(dataSource);
	}
	@Override
	public ReviewDao getRecensioneDao()
	{
		return new ReviewDaoJDBC(dataSource);
	}
	@Override
	public UserDao getUtenteDao()
	{
		return new UserDaoJDBC(dataSource);
	}
}
