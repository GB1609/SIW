package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.PlaceDao;
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
			statement.setString(1,p.getCitta());
			statement.setString(2,p.getNome());
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
			statement.setString(1,p.getNome());
			statement.setString(2,p.getCitta());
			statement.setLong(3,p.getCapienza());
			statement.setString(4,p.getTipo());
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
			statement.setString(1,p.getNome());
			statement.setString(2,p.getCitta());
			statement.setLong(3,p.getCapienza());
			statement.setString(4,p.getTipo());
			statement.setString(5,p.getNome());
			statement.setString(6,p.getCitta());
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
}
