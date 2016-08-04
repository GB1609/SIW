package dao;

import java.util.List;

import tables.SubCategory;

public interface SubCategoryDao {

	public void save(SubCategory c);
	public void delete(String name);
	public List<String> getSubCategories();
	public int returnCode(String name);
	
}
