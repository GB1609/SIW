package dao;

import java.time.LocalDate;
import java.util.Set;

import tables.Events;
import tables.Information;

public interface EventsDao {
	public void delete(int eventCode);

	public Information getInformation(int eventCode);

	public void save(Events e);

	public Set<Events> searchByCategory(int categoryCode);

	public Set<Events> searchByDate(LocalDate l);

	public Set<Events> searchByOrganizator(String organizator);

	public Set<Events> searchByPartecipants(int idpartecipant);

	public Set<Events> searchByPlace(String name, String city);

	public Set<Events> searchByPrice(double price, boolean max);

	public void startSeller(Events e);

	public void stopSeller(Events e);

	public void update(Events e);
}
