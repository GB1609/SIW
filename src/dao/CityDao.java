package dao;

import java.util.List;

import tables.City;

public interface CityDao {

	public void save (City c);
	public void delete(String name);
	public List<City> getAllCities ();
}
