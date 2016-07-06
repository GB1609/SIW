package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dao.ReviewDao;
import tables.Review;
public class ReviewDaoJDBC implements ReviewDao
{
	private DataSource dataSource;
	public ReviewDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(Review r)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String delete="delete FROM review WHERE reviewcode = ? ";
			PreparedStatement statement=connection.prepareStatement(delete);
			statement.setInt(1,r.getReviewCode());
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
	public void save(Review r)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String insert="insert into review(reviewcode, vote, users, event) values (?,?,?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setInt(1,r.getReviewCode());
			statement.setInt(2,r.getVote());
			statement.setString(3,r.getUser());
			statement.setInt(4,r.getEvent());
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
	public void update(Review r)
	{
		Connection connection=this.dataSource.getConnection();
		try
		{
			String update="update review SET vote = ?, users = ?, event= ? WHERE reviewcode =?";
			PreparedStatement statement=connection.prepareStatement(update);
			statement.setInt(1,r.getVote());
			statement.setString(2,r.getUser());
			statement.setInt(3,r.getEvent());
			statement.setInt(4,r.getReviewCode());
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
