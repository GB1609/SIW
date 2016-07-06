package core;
import java.util.Set;
import dao.OrdersDao;
import tables.Clients;
import tables.Order;
public class OrdineDaoJDBC implements OrdersDao
{
	private DataSource dataSource;
	public OrdineDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void update()
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void add(Order o,Clients c)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void delete(Order o)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public Set<Order> searchByClients(Clients c)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
