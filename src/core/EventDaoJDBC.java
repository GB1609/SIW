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
import tables.Organizator;
import tables.Partecipants;
import tables.Place;
public class EventDaoJDBC implements EventsDao
{
	private DataSource dataSource;
	public EventDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(Events ev)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String delete="delete FROM event WHERE eventcode = ? ";
			PreparedStatement statement=connection.prepareStatement(delete);
			statement.setInt(1,ev.getEventcode());
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
			String insert="insert into event(eventcode, feedback, organizator, category, information, partecipant) values (?,?,?,?,?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setInt(1,ev.getEventcode());
			statement.setString(2,ev.getFeedback());
			statement.setString(3,ev.getOrganizator());
			statement.setInt(4,ev.getCategory());
			statement.setInt(5,ev.getInformation());
			statement.setInt(6,ev.getPartecipant());
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
		Set<Events> set=new HashSet();
		Connection connection=this.dataSource.getConnection();
		try
		{
			String search="select * from event, information WHERE event.information = information.informationid AND information.date = ?";
			PreparedStatement statement=connection.prepareStatement(search);
			statement.setDate(1,Date.valueOf(l));
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			ResultSet result=statement.executeQuery();
			for (int i=0;i < result.getFetchSize();i++)
			{
				System.out.println(i);
				set.add(new Events(result.getInt(1),result.getString(2),result.getString(3),result.getInt(4),result.getInt(5),result.getInt(6)));
			}
			System.out.println("END");
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
	public Set<Events> searchByLocality(Place l)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Events> searchByOrganizator(Organizator o)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Events> searchByPartecipants(Partecipants p)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Events> searchByPrice(double price,boolean max)
	{
		// TODO Auto-generated method stub
		return null;
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
