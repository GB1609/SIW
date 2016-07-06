package dao;
import java.util.Set;
import tables.Category;
import tables.Events;
import tables.Place;
import tables.Organizator;
import tables.Partecipants;
public interface EventsDao
{
	public void delete(Events e);
	public void save(Events e);
	public Set<Events> searchByCategory(Category c);
	public Set<Events> searchByDate(Place l);
	public Set<Events> searchByLocality(Place l);
	public Set<Events> searchByOrganizator(Organizator o);
	public Set<Events> searchByPartecipants(Partecipants p);
	public Set<Events> searchByPrice(double price,boolean max);
	public void startSeller(Events e);
	public void stopSeller(Events e);
	public void update(Events e);
}
