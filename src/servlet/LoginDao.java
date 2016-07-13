package servlet;
import core.DaoFactory;
import dao.ClientsDao;
public class LoginDao
{
	public static boolean validate(String name,String pass)
	{
		boolean status=false;
		try
		{
			DaoFactory dao=DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
			ClientsDao i=dao.getClienteDao();
			i.verifyClients(name,pass);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		return status;
	}
}
