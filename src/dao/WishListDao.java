package dao;

import java.util.List;

import tables.WishList;

public interface WishListDao {
	public void delete(int listCode);

	public void deleteAll();

	public boolean save(WishList l);

	public List<String> searchByOwner(String owner);

	public void update(WishList l);
}
