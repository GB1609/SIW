package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.ClientsWishDao;
import tables.ClientsWish;
public class ClientsWishDaoJDBC implements ClientsWishDao
{
	private DataSource dataSource;
	public ClientsWishDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(ClientsWish cd)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String delete="delete FROM clientdesired WHERE clientusername= ? and listcode=?";
			PreparedStatement statement=connection.prepareStatement(delete);
			statement.setString(1,cd.getUsernameClient());
			statement.setLong(2,cd.getlistCode());
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
	public void save(ClientsWish cd)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String insert="insert into clientdesired (clientusername,listcode) values (?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setString(1,cd.getUsernameClient());
			statement.setLong(2,cd.getlistCode());
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
			catch (Exception e1)
			{
				e1.printStackTrace();
			}
		}
	}
}
