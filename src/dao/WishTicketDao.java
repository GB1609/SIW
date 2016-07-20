package dao;
import java.util.Set;

import tables.Clients;
import tables.Ticket;
import tables.WishTicket;
public interface WishTicketDao
{
	public boolean alreadyExist(WishTicket wt);
	public void delete(WishTicket bd);
	public String getListOwner(WishTicket bd);
	public int save(WishTicket bd, String owner);
	public Set<Clients> searchInterested(Ticket b); // cerca le persone che hanno
	// interesse nel
	// biglietto(lista)
	public Set<Ticket> searchOwners(Clients c); // cerca coloro che lo posseggono
	// gia
}
