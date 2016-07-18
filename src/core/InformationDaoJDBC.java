package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import dao.InformationDao;
import tables.Information;
public class InformationDaoJDBC implements InformationDao
{
	private DataSource dataSource;
	public InformationDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(Information i)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String delete="delete FROM information WHERE id = ? ";
			PreparedStatement statement=connection.prepareStatement(delete);
			statement.setLong(1,i.getInformationId());
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
	public void save(Information i)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String insert="insert into information (place,date,description,city) values (?,?,?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setString(1,i.getLocality());
			LocalDate dateOfExecution=i.getDate();
			statement.setDate(2,java.sql.Date.valueOf(dateOfExecution));
			statement.setString(3,i.getDescription());
			statement.setString(4,i.getCity());
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
	public void update(Information i)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String update="update informatione SET informazioneid = ?, place = ?, date = ?, description=?, city=?  WHERE informationid=?";
			PreparedStatement statement=connection.prepareStatement(update);
			statement.setLong(1,i.getInformationId());
			statement.setString(2,i.getLocality());
			LocalDate dateOfExecution=i.getDate();
			statement.setDate(3,java.sql.Date.valueOf(dateOfExecution));
			statement.setString(4,i.getDescription());
			statement.setString(5,i.getCity());
			statement.setLong(6,i.getInformationId());
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
