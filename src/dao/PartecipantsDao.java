package dao;

import java.util.List;

import tables.Partecipants;

public interface PartecipantsDao {
	public void delete(int partecipantId);

	public void deleteAll();

	public List<Partecipants> getAllPartecipants();

	public int getPartecipantCode(String name);

	public void save(Partecipants i);

	public void update(Partecipants i);
}
