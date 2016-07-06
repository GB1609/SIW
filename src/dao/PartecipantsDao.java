package dao;

import tables.Partecipants;

public interface PartecipantsDao {
	public void save(Partecipants i);

	public void delete(Partecipants i);

	public void update(Partecipants i);
}
