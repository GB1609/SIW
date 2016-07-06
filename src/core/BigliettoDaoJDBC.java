package core;
import java.util.Set;
import dao.TicketDao;
import tables.Events;
import tables.Place;
import tables.Ticket;
public class BigliettoDaoJDBC implements TicketDao
{
	private DataSource dataSource;
	public BigliettoDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(TicketDao bd)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void save(TicketDao bd)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public Set<Ticket> searchByEvents(Events e)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Ticket> searchByLocality(Place l)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Ticket> searchByPrice(double price,boolean max)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Ticket> searchByPrice(double min,double max)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setState(boolean sell)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void update(TicketDao bd)
	{
		// TODO Auto-generated method stub
	}
}
