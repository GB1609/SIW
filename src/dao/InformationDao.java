package dao;
import tables.Information;
public interface InformationDao
{
	public void save(Information i);
	public void delete(Information i);
	public void update(Information i);
}
