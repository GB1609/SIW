package dao;

import tables.Organizator;

public interface OrganizatorDao {
	public void delete(String organizator);

	public void deleteAll();

	public void save(Organizator o);

	public void update(Organizator o);

	public boolean verifyOrganizator(String userName, String password);
}
