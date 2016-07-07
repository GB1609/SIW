package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.PartecipantsOfEventsDao;
import tables.PartecipantsOfEvents;
public class PartecipantsOfEventsJDBC implements PartecipantsOfEventsDao
{
	private DataSource dataSource;
	public PartecipantsOfEventsJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(PartecipantsOfEvents i)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String delete="delete FROM eventpartecipant WHERE partecipant = ? and event=? ";
			PreparedStatement statement=connection.prepareStatement(delete);
			statement.setLong(1,i.getPartecipant());
			statement.setLong(2,i.getEvent());
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
	public void save(PartecipantsOfEvents p)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String insert="insert into eventpartecipant (partecipant,event) values (?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setLong(1,p.getPartecipant());
			statement.setLong(2,p.getEvent());
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
}
