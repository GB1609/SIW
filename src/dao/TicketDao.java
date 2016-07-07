package dao;
import java.util.Set;
import tables.Place;
import tables.Ticket;
public interface TicketDao
{
	public void delete(int code);
	public void save(Ticket bd);
	public Set<Ticket> searchByEvents(int e);
	public Set<Ticket> searchByLocality(Place l);
	public Set<Ticket> searchByPrice(double price,boolean max);
	public Set<Ticket> searchByPrice(double min,double max);
	public void setState(boolean sell,int code);
	public void update(Ticket bd);
}
