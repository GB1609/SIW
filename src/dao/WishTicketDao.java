package dao;
import java.util.Set;
import tables.Clients;
import tables.Ticket;
import tables.WishTicket;
public interface WishTicketDao
{
	public void delete(WishTicket bd);
	public void save(WishTicket bd);
	public Set<Clients> searchInterested(Ticket b); // cerca le persone che hanno
																	// interesse nel
																	// biglietto(lista)
	public Set<Ticket> searchOwners(Clients c); // cerca coloro che lo posseggono
																// gia
}
