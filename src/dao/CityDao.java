package dao;

import java.util.List;

import tables.City;

public interface CityDao {

	public void delete(String name);

	public void deleteAll();

	public List<City> getAllCities();

	public void save(City c);
}
