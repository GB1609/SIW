package dao;

import java.util.List;
import java.util.Set;

import tables.Clients;
import tables.Ticket;
import tables.WishTicket;

public interface WishTicketDao {
	public boolean alreadyExist(WishTicket wt);

	public boolean delete(WishTicket bd);

	public void deleteAll();

	public String getListOwner(WishTicket bd);

	public int save(WishTicket bd, String owner);

	public List<String> searchByWishList(int listCode);

	public Set<Clients> searchInterested(Ticket b); // cerca le persone che
	// hanno
	// interesse nel
	// biglietto(lista)

	public Set<Ticket> searchOwners(Clients c); // cerca coloro che lo
	// posseggono
	// gia
}
