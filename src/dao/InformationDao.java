package dao;
import java.util.Set;

import tables.Information;
public interface InformationDao
{
	public void save(Information i);
	public void delete(Information i);
	public void update(Information i);
	public Set<Information> getAllInfo (int code);
}
