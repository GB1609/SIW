package core;
import dao.UserDao;
import tables.User;
public class UtenteDaoJDBC implements UserDao
{
	private DataSource dataSource;
	public UtenteDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void create(User i)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void delete(User i)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void update(User i)
	{
		// TODO Auto-generated method stub
	}
}
