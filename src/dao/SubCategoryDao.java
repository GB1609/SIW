package dao;

import java.util.List;

import tables.SubCategory;

public interface SubCategoryDao {

	public void delete(String name);

	public void deleteAll();

	public List<String> getSubCategories();

	public int returnCode(String name);

	public void save(SubCategory c);
	
	public String getName(int code);

}
