package dao;
import java.util.List;

import tables.Partecipants;
public interface PartecipantsDao
{
	public void save(Partecipants i);
	public void delete(Partecipants i);
	public void update(Partecipants i);
	public List<Partecipants> getAllPartecipants();
	public int getPartecipantCode(String name);
}
