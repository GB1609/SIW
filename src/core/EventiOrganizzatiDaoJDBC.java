package core;
import dao.OrganizatorEventsDao;
public class EventiOrganizzatiDaoJDBC implements OrganizatorEventsDao
{
	private DataSource dataSource;
	public EventiOrganizzatiDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void save()
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void update()
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void delete()
	{
		// TODO Auto-generated method stub
	}
}
