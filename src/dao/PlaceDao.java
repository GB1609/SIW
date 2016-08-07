package dao;

import java.util.List;

import tables.Place;

public interface PlaceDao {
	public void delete(String name);

	public void deleteAll();

	public List<Place> returnAllPlace();

	public void save(Place i);

	public void update(Place i);

}
