package dao;

import tables.EventPartecipant;

public interface EventPartecipantDao {
	public void delete(EventPartecipant p);

	public void deleteAll();

	public void save(EventPartecipant p);
}
