package dao;

import tables.ClientsWish;

public interface ClientsWishDao {
	public void delete(ClientsWish cd);

	public void deleteAll();

	public void save(ClientsWish cd);
}
