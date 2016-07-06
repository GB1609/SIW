package dao;
import tables.Organizator;
public interface OrganizatorDao
{
	public void delete(int o);
	public void save(Organizator o);
	public void update(Organizator o);
}
