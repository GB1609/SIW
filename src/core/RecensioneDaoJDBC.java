package core;
import dao.ReviewDao;
public class RecensioneDaoJDBC implements ReviewDao
{
	private DataSource dataSource;
	public RecensioneDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void create(ReviewDao r)
	{
		// TODO Auto-generated method stub
	}
}
