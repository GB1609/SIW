package dao;

import java.time.LocalDate;
import java.util.List;

import tables.Events;
import tables.Information;

public interface EventsDao {
	public void delete(int eventCode);

	public void deleteAll();

	public void deleteAllForOne(int eventCode, String name);

	public int getCode(String name);

	public boolean getEventState(String name);

	public String getFeedback(int eventCode);

	public String getImg(int eventCode);

	public Information getInfoByName(String value);

	public Information getInformation(int eventCode);

	public String getName(int eventCode);

	public int getRemainTicket(int eventCode);

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

	public void startSeller(int e);

	public void stopSeller(int e);

	public void update(Events e);

	public void updateTicketsNumber(int eventCode, int ticketQuantity);
}
