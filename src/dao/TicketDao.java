package dao;
import java.util.Set;
import tables.Events;
import tables.Place;
import tables.Ticket;
public interface TicketDao
{
	public void delete(TicketDao bd);
	public void save(TicketDao bd);
	public Set<Ticket> searchByEvents(Events e);
	public Set<Ticket> searchByLocality(Place l);
	public Set<Ticket> searchByPrice(double price,boolean max);
	public Set<Ticket> searchByPrice(double min,double max);
	public void setState(boolean sell);
	public void update(TicketDao bd);
}
