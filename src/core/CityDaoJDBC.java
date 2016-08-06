package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.CityDao;
import tables.City;
import tables.Place;

public class CityDaoJDBC implements CityDao {

	private DataSource dataSource;
	
	public CityDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void save(City c) {
		Connection connection = this.dataSource.getConnection();
		try {
			String nome = c.getName();
			String insert = "insert into city (name) values (?) ";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setString(1,nome);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
	@Override
	public void delete(String name) {
		Connection connection = this.dataSource.getConnection();
		try {
			String remove = "delete FROM city WHERE name=?";
			PreparedStatement statement = connection.prepareStatement(remove);
			statement.setString(1, name);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public List<City> getAllCities() {
		List<City> cities = new ArrayList<City>();
		Connection connection = this.dataSource.getConnection();
		try {
			String query = "SELECT * FROM city ORDER BY city.name";
			PreparedStatement statement = connection.prepareStatement(query);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.setAutoCommit(false);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				cities.add(new City(result.getString(1)));
			}
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cities;
	}

}