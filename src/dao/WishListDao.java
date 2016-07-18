package dao;

import java.util.List;

import tables.WishList;

public interface WishListDao {
	public void delete(int listCode);

	public void save(WishList l);

	public List<String> searchByOwner(String owner);

	public void update(WishList l);
}
