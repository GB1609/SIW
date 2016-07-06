package dao;

import tables.Category;

public interface CategoryDao
{
	public void save(Category c);
	public void delete(Category c);
	public void update(Category c);
}
