package dao;
import tables.WishList;
public interface WishListDao
{
	public void save(WishList l);
	public void update(WishList l);
	public void delete(WishList l);
}
