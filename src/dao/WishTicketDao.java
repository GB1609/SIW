package dao;
import java.util.Set;
import tables.Clients;
import tables.WishTicket;
public interface WishTicketDao
{
	public void delete(WishTicket bd);
	public void save(WishTicket bd);
	public Set<Clients> searchInterested(TicketDao b); // cerca le persone che
																		// hanno interesse nel
																		// biglietto(lista)
	public Set<TicketDao> searchOwners(Clients c); // cerca coloro che lo
																	// posseggono gia
	public void update(WishTicket bd);
}
