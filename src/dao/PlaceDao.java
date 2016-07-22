package dao;
import java.util.List;

import tables.Place;
public interface PlaceDao
{
	public void save(Place i);
	public void delete(Place i);
	public void update(Place i);
	public List<Place> returnAllPlace();

}
