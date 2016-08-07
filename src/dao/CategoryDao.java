package dao;

import java.util.List;

import tables.Category;

public interface CategoryDao {
	public void delete(int categoryCode);

	public void deleteAll();

	public List<String> getCategories();

	public int returnCode(String name);

	public void save(Category c);
}
