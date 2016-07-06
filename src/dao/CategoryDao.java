package dao;
import tables.Category;
public interface CategoryDao
{
	public void create(Category c);
	public void delete(Category c);
	public void modify(Category c);
}
