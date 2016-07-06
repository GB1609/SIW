package dao;
import java.util.Set;
import tables.Clients;
import tables.Order;
public interface OrdersDao
{
	public void add(Order o,Clients c);
	public void delete(Order o);
	public Set<Order> searchByClients(Clients c);
	public void update();
}
