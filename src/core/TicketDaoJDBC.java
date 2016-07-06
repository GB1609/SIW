package core;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Set;
import dao.TicketDao;
import tables.Events;
import tables.Place;
import tables.Ticket;
public class TicketDaoJDBC implements TicketDao
{
	private DataSource dataSource;
	public TicketDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void delete(Ticket bd)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void save(Ticket bd)
	{
		Connection connection=this.dataSource.getConnection();
		int ticketCode=bd.getCodiceBiglietto();
		double price=bd.getPrezzo();
		String type=bd.getTipo();
		int eventCode=bd.getCodiceEvento();
		try
		{
			String insert="insert into ticket(price,type,event,ticketcode) values (?,?,?,?)";
			PreparedStatement statement=connection.prepareStatement(insert);
			statement.setDouble(1,price);
			statement.setString(2,type);
			statement.setInt(3,eventCode);
			statement.setInt(4,ticketCode);
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
	public Set<Ticket> searchByEvents(Events e)
	{
		// TODO Auto-generated method stub
		return null;
	}
	public Set<Ticket> searchByLocality(Place l)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Ticket> searchByPrice(double price,boolean max)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Ticket> searchByPrice(double min,double max)
	{
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setState(boolean sell)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void update(Ticket bd)
	{
		// TODO Auto-generated method stub
	}
}
