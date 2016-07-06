package core;
import dao.ClientsWishDao;
import tables.ClientsWish;
public class ClienteDesideraDaoJDBC implements ClientsWishDao
{
	private DataSource dataSource;
	public ClienteDesideraDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void create(ClientsWish cd)
	{
		// TODO Auto-generated method stub
	}
}
