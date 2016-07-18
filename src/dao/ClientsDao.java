package dao;
import tables.Clients;
public interface ClientsDao
{
	public void delete(Clients c);
	public boolean existUser(String userToVerify);
	public void save(Clients c);
	public void update(Clients c);
	public boolean verifyClients(String userName,String password);
}
