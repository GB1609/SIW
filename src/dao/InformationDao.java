package dao;

import java.util.Set;

import tables.Information;

public interface InformationDao {
	public void delete(int informationId);

	public void deleteAll();

	public Set<Information> getAllInfo(int code);

	public void save(Information i);

	public void update(Information i);
}
