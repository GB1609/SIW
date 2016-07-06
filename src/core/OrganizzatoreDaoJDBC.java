package core;
import dao.OrganizatorDao;
public class OrganizzatoreDaoJDBC implements OrganizatorDao
{
	private DataSource dataSource;
	public OrganizzatoreDaoJDBC(DataSource dataSource)
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
