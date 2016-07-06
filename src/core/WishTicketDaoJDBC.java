package core;
import java.util.Set;
import dao.TicketDao;
import dao.WishTicketDao;
import tables.Clients;
import tables.WishTicket;
public class WishTicketDaoJDBC implements WishTicketDao
{
	public WishTicketDaoJDBC(DataSource dataSource)
	{}
	@Override
	public void delete(WishTicket bd)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void save(WishTicket bd)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public Set<Clients> searchInterested(TicketDao b)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<TicketDao> searchOwners(Clients c)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(WishTicket bd)
	{
		// TODO Auto-generated method stub
	}
}
