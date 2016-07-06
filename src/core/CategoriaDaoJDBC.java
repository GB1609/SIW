package core;
import dao.CategoryDao;
import tables.Category;
public class CategoriaDaoJDBC implements CategoryDao
{
	private DataSource dataSource;
	public CategoriaDaoJDBC(DataSource dataSource)
	{
		this.dataSource=dataSource;
	}
	@Override
	public void create(Category c)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void delete(Category c)
	{
		// TODO Auto-generated method stub
	}
	@Override
	public void modify(Category c)
	{
		// TODO Auto-generated method stub
	}
}
