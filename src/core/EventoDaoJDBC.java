package core;
import java.util.Set;
import dao.EventsDao;
import tables.Category;
import tables.Events;
import tables.Place;
import tables.Organizator;
import tables.Partecipants;
public class EventoDaoJDBC implements EventsDao
{
	private DataSource dataSource;
	public EventoDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(Events e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void save(Events e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public Set<Events> searchByCategory(Category c)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Events> searchByDate(Place l)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Events> searchByLocality(Place l)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Events> searchByOrganizator(Organizator o)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Events> searchByPartecipants(Partecipants p)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Events> searchByPrice(double price,boolean max)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void startSeller(Events e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void stopSeller(Events e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void update(Events e)
	{
		// TODO Auto-generated method stub
	}
}
