package dao;
import java.util.List;

import tables.Category;
public interface CategoryDao
{
	public void save(Category c);
	public void delete(Category c);
	public void update(Category c);
	public List<String> getCategories();
	public int returnCode(String name);
}
