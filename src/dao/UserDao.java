package dao;
import tables.User;
public interface UserDao
{
	public void create(User i);
	public void delete(User i);
	public void update(User i);
}
