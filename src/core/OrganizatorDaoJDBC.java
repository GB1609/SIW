package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dao.OrganizatorDao;
import tables.Organizator;
public class OrganizatorDaoJDBC implements OrganizatorDao
{
	private DataSource dataSource;
	public OrganizatorDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}


@Override
	public boolean verifyOrganizator(String userName, String password)
	{
		boolean exsist=false;
		Connection connection=this.dataSource.getConnection();
		try
		{
			String query="select * FROM organizator where username=? and password=?";
			PreparedStatement statement=connection.prepareStatement(query);
			statement.setString(1,userName);
			statement.setString(2,password);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			connection.commit();
			ResultSet result=statement.executeQuery();
			if (result.next())
			{
				exsist=true;
			}
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
		return exsist;
	}


	@Override
	public void delete(int c)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String delete="delete FROM client WHERE username = ? ";
			PreparedStatement statement=connection.prepareStatement(delete);
			statement.setLong(1,c);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			statement.executeUpdate();
			connection.commit();
		}
		catch (SQLException e)
		{}
		finally
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{}
		}
	}
	@Override
	public void save(Organizator c)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String insert="insert into client (username, password, lastname, firstname, birthdate, address) values (?,?,?,?,?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setString(1,c.getUsername());
			statement.setString(2,c.getPassword());
			statement.setString(3,c.getLastName());
			statement.setString(4,c.getFirstName());
			statement.setDate(5,java.sql.Date.valueOf(c.getBirthDate()));
			statement.setString(6,c.getAddress());
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
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
	public void update(Organizator c)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String update="update client SET password = ?, lastname = ?, firstname = ?, birthdate = ?, address = ?,credit=?   WHERE username=?";
			PreparedStatement statement=connection.prepareStatement(update);
			statement.setString(1,c.getPassword());
			statement.setString(2,c.getLastName());
			statement.setString(3,c.getFirstName());
			statement.setDate(4,java.sql.Date.valueOf(c.getBirthDate()));
			statement.setString(5,c.getAddress());
			statement.setString(6,c.getUsername());
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
			{}
		}
	}
}
