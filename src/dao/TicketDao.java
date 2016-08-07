package dao;

import java.util.List;
import java.util.Set;

import tables.Place;
import tables.Ticket;

public interface TicketDao {
	public void delete(int code);

	public void deleteAll();

	public boolean getState(int code);

	public Ticket getTicket(int ticketcode);

	public List<String> getTipology();

	public void save(Ticket bd);

	public List<String> searchByEvents(int e);

	public Set<Ticket> searchByLocality(Place l);

	public Set<Ticket> searchByPrice(double price, boolean max);

	public Set<Ticket> searchByPrice(double min, double max);

	public Ticket searchTicket(String type, int event, double price);

	public void setState(boolean sell, int code);

	public void update(Ticket bd);
}
