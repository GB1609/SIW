package dao;
import java.util.Set;
import tables.Clients;
import tables.Ticket;
public interface ClientsDao
{
	public void delete(Clients c);
	public boolean existUser(String userToVerify);
	public void save(Clients c);
	public void update(Clients c);
	public boolean verifyClients(String userName,String password);
	public void sellTicket(int ticket, int priceSelect);
	public Set <Ticket> ticketBuyed(String userName);
}
