package core;
import dao.*;
public abstract class DaoFactory
{
	public static final int HSQLDB=1;
	public static final int POSTGRESQL=2;
	public static DaoFactory getDAOFactory(int whichFactory)
	{
		switch (whichFactory)
		{
			case HSQLDB:
				return null;// new HsqldbDAOFactory();
			case POSTGRESQL:
				return new PostgresDaoFactory();
			default:
				return null;
		}
	}
	public abstract WishTicketDao getBigliettiDesideratiDao();
	public abstract TicketDao getBigliettoDao();
	public abstract CategoryDao getCategoriaDao();
	public abstract ClientsDao getClienteDao();
	public abstract ClientsWishDao getClienteDesideraDao();
	public abstract OrganizatorEventsDao getEventiOrganizzatiDao();
	public abstract EventsDao getEventoDao();
	public abstract InformationDao getInformationDao();
	public abstract WishListDao getListaDesideriDao();
	public abstract PlaceDao getPlaceDao();
	public abstract OrdersDao getOrdineDao();
	public abstract OrganizatorDao getOrganizzatoreDao();
	public abstract PartecipantsDao getPartecipanteDao();
	public abstract PartecipantsOfEventsDao getPartecipantiAdEventi();
	public abstract ReviewDao getRecensioneDao();
	public abstract UserDao getUtenteDao();
	public abstract CityDao getCityDao();
	public abstract SubCategoryDao getSubCategoryDao();
}
