package core;
import dao.WishListDao;
import tables.WishList;
public class ListaDesideriDaoJDBC implements WishListDao
{
	private DataSource dataSource;
	public ListaDesideriDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void create(WishList l)
	{
		// TODO Auto-generated method stub
	}
}
