package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PlaceDao;
import tables.Partecipants;
import tables.Place;
public class PlaceDaoJDBC implements PlaceDao
{
	private DataSource dataSource;
	public PlaceDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(Place p)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String delete="delete FROM place WHERE city = ?,name=? ";
			PreparedStatement statement=connection.prepareStatement(delete);
			statement.setString(1,p.getCity());
			statement.setString(2,p.getName());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	@Override
	public void save(Place p)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String insert="insert into place (name, city, capacity, type) values (?,?,?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setString(1,p.getName());
			statement.setString(2,p.getCity());
			statement.setLong(3,p.getCapacity());
			statement.setString(4,p.getType());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
			System.out.println("information insert");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (Exception e1)
			{
				System.out.println("ciao1");
				e1.printStackTrace();
			}
		}
	}
	@Override
	public void update(Place p)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String update="update place SET name=?,city=?,capacity=?,type=? where name=? and city=?";
			PreparedStatement statement=connection.prepareStatement(update);
			statement.setString(1,p.getName());
			statement.setString(2,p.getCity());
			statement.setLong(3,p.getCapacity());
			statement.setString(4,p.getType());
			statement.setString(5,p.getName());
			statement.setString(6,p.getCity());
			statement.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				connection.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	@Override
	public List<Place> returnAllPlace() {
		Connection connection = this.dataSource.getConnection();
		List<Place> places = new ArrayList<Place>();
		try {
			String returnAll = "SELECT * FROM place ORDER BY place.name" ;
			PreparedStatement statement = connection.prepareStatement(returnAll);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result = statement.executeQuery();
			while (result.next())
			{
				places.add(new Place(result.getInt(3),result.getString(1), result.getString(2), result.getString(4)));
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
		return places;
	}

}
