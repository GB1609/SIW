package core;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import dao.EventsDao;
import tables.Category;
import tables.Events;
public class EventDaoJDBC implements EventsDao
{
	private DataSource dataSource;
	public EventDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(int eventCode)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String delete="delete FROM event WHERE eventcode = ? ";
			PreparedStatement statement=connection.prepareStatement(delete);
			statement.setInt(1,eventCode);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
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
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	@Override
	public void save(Events ev)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String insert="insert into event(feedback, organizator, category, information, partecipant) values (?,?,?,?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setString(1,ev.getFeedback());
			statement.setString(2,ev.getOrganizator());
			statement.setInt(3,ev.getCategory());
			statement.setInt(4,ev.getInformation());
			statement.setInt(5,ev.getPartecipant());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
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
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	@Override
	public Set<Events> searchByCategory(Category c)
	{
		return null;
	}
	@Override
	public Set<Events> searchByDate(LocalDate l)
	{
		Set<Events> set=new HashSet<Events>();
		Connection connection=this.dataSource.getConnection();
		try
		{
			String search="select * from event, information WHERE event.information = information.informationid AND information.date = ?";
			PreparedStatement statement=connection.prepareStatement(search);
			statement.setDate(1,Date.valueOf(l));
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result=statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getInt(5),result.getInt(6)));
			connection.commit();
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
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return set;
	}
	@Override
	public Set<Events> searchByOrganizator(String organizator)
	{
		Set<Events> set=new HashSet<Events>();
		Connection connection=this.dataSource.getConnection();
		try
		{
			String search="select * from event WHERE event.organizator = ?";
			PreparedStatement statement=connection.prepareStatement(search);
			statement.setString(1,organizator);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result=statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getInt(5),result.getInt(6)));
			connection.commit();
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
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return set;
	}
	@Override
	public Set<Events> searchByPartecipants(int idpartecipant)
	{
		Set<Events> set=new HashSet<Events>();
		Connection connection=this.dataSource.getConnection();
		try
		{
			String search="select * from event WHERE event.partecipant = ?";
			PreparedStatement statement=connection.prepareStatement(search);
			statement.setInt(1,idpartecipant);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result=statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getInt(5),result.getInt(6)));
			connection.commit();
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
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return set;
	}
	@Override
	public Set<Events> searchByPlace(String name,String city)
	{
		Set<Events> set=new HashSet<Events>();
		Connection connection=this.dataSource.getConnection();
		try
		{
			String search="select * from event, information WHERE event.information = information.informationid AND information.place = ? AND information.city = ?";
			PreparedStatement statement=connection.prepareStatement(search);
			statement.setString(1,name);
			statement.setString(2,city);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result=statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getInt(5),result.getInt(6)));
			connection.commit();
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
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return set;
	}
	@Override
	public Set<Events> searchByPrice(double price,boolean max)
	{
		Set<Events> set=new HashSet<Events>();
		Connection connection=this.dataSource.getConnection();
		try
		{
			String search;
			if (max)
				search="SELECT * FROM event, ticket WHERE ticket.event = event.eventcode AND price < ?";
			else
				search="SELECT * FROM event, ticket WHERE ticket.event = event.eventcode AND price > ?";
			PreparedStatement statement=connection.prepareStatement(search);
			statement.setDouble(1,price);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result=statement.executeQuery();
			while (result.next())
				set.add(new Events(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getInt(5),result.getInt(6)));
			connection.commit();
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
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		return set;
	}
	@Override
	public void startSeller(Events e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void stopSeller(Events e)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void update(Events ev)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String update="update event SET feedback = ?, organizator = ?, category = ?, information = ?, partecipant = ? WHERE eventcode =?";
			PreparedStatement statement=connection.prepareStatement(update);
			statement.setString(1,ev.getFeedback());
			statement.setString(2,ev.getOrganizator());
			statement.setInt(3,ev.getCategory());
			statement.setInt(4,ev.getInformation());
			statement.setInt(5,ev.getPartecipant());
			statement.setInt(6,ev.getEventcode());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
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
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
