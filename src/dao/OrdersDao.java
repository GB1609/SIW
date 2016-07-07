package dao;

import java.util.Set;
import tables.Clients;
import tables.Order;

public interface OrdersDao
{
	public void save(Order o);
	public void delete(int key);
	public Set <Order> searchByClients(String username);
	public void update(Order o);
	
}
