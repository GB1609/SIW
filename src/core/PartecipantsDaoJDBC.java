package core;
import dao.PartecipantsDao;
public class PartecipantsDaoJDBC implements PartecipantsDao
{
	private DataSource dataSource;
	public PartecipantsDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void create(PartecipantsDao i)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void delete(PartecipantsDao i)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void update(PartecipantsDao i)
	{
		// TODO Auto-generated method stub
	}
}
