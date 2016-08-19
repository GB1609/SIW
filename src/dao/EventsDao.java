package dao;

import java.time.LocalDate;
import java.util.List;

import tables.Events;
import tables.Information;

public interface EventsDao {
	public void delete(int eventCode);

	public void deleteAll();

	public int getCode(String name);

	public String getFeedback(int eventCode);

	public String getImg(int eventCode);

	public Information getInfoByName(String value);

	public Information getInformation(int eventCode);

	public String getName(int eventCode);

	public void insertPartecipant(int partecipant, int eventCode);

	public List<Events> organizedEvents(String user);

	public List<Events> returnAllEvents();

	public void save(Events e);

	public List<Events> searchByCategory(String categoryCode);

	public List<Events> searchByDate(LocalDate l);

	public List<Events> searchByName(String value);

	public List<Events> searchByOrganizator(String organizator);

	public List<Events> searchByPartecipants(String partecipant);

	public List<Events> searchByPlace(String city);

	public List<Events> searchByPrice(double price, boolean max);

	public List<Events> searchBySubCategory(String category);

	public void startSeller(Events e);

	public void stopSeller(Events e);

	public void update(Events e);
}
