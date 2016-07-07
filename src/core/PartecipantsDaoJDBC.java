package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.PartecipantsDao;
import tables.Partecipants;
public class PartecipantsDaoJDBC implements PartecipantsDao
{
	private DataSource dataSource;
	public PartecipantsDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void save(Partecipants i)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String insert="insert into partecipant(name, type, city, partecipantid) values (?,?,?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setString(1,i.getName());
			statement.setString(2,i.getType());
			statement.setString(3,i.getCity());
			statement.setInt(4,i.getIdPartecipant());
			statement.executeUpdate();
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
	public void delete(Partecipants i)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String delete="delete FROM partecipant WHERE partecipantid = ? ";
			PreparedStatement statement=connection.prepareStatement(delete);
			statement.setInt(1,i.getIdPartecipant());
			statement.executeUpdate();
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
	public void update(Partecipants i)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String update="update partecipant SET name = ?, type = ?, city = ?  WHERE partecipantid = ?";
			PreparedStatement statement=connection.prepareStatement(update);
			statement.setString(1,i.getName());
			statement.setString(2,i.getType());
			statement.setString(3,i.getCity());
			statement.setInt(4,i.getIdPartecipant());
			statement.executeUpdate();
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