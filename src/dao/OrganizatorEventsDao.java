package dao;

import tables.OrganizatorEvents;

public interface OrganizatorEventsDao {
	public void delete(OrganizatorEvents oe);

	public void deleteAll();

	public void save(OrganizatorEvents oe);
}
