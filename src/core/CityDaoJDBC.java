package core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CityDao;
import tables.City;

public class CityDaoJDBC implements CityDao {

	private DataSource dataSource;

	public CityDaoJDBC(DataSource dataSource) {
		this.dataSource = dataSource;
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
	public void deleteAll() {
		Connection connection = this.dataSource.getConnection();
		try {
			String remove = "delete FROM city";
			PreparedStatement statement = connection.prepareStatement(remove);
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
				cities.add(new City(result.getString(1)));
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

	@Override
	public void save(City c) {
		Connection connection = this.dataSource.getConnection();
		try {
			String nome = c.getName();
			String insert = "insert into city (name) values (?) ";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, nome);
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
	public boolean containCity(String name) {
		Connection connection = dataSource.getConnection();
		try{
			String query = "SELECT name FROM city WHERE name=?";
			PreparedStatement state = connection.prepareStatement(query);
			state.setString(1, name);
			ResultSet res = state.executeQuery();
			while(res.next())
			{
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
