package dao;
import java.util.Set;
import tables.Order;
public interface OrdersDao
{
	public void delete(int key);
	public void save(Order o);
	public Set<Order> searchByClients(String username);
	public void update(Order o);
}
