package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.CategoryDao;
import tables.Category;
public class CategoryDaoJDBC implements CategoryDao
{
	private DataSource dataSource;
	public CategoryDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(Category c)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			int id=c.getCategoryCode();
			String delete="delete FROM category WHERE categorycode = ?";
			PreparedStatement statement=connection.prepareStatement(delete);
			statement.setInt(1,id);
			connection.setAutoCommit(false);
			connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			statement.executeUpdate();
			connection.commit();
		}
		catch (Exception e)
		{}
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
	public void save(Category c)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String insert="insert into category (categorycode, name, father) values(?,?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setLong(1,c.getCategoryCode());
			statement.setString(2,c.getName());
			statement.setInt(3,c.getFather());
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
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	@Override
	public void update(Category c)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			int id=c.getCategoryCode();
			int figlio=c.getFather();
			String nome=c.getName();
			String update="update category SET categorycode=? , name = ?, son = ? WHERE categorycode=?";
			PreparedStatement statement=connection.prepareStatement(update);
			statement.setInt(1,id);
			statement.setString(2,nome);
			statement.setInt(3,figlio);
			statement.setInt(4,id);
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
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
}
