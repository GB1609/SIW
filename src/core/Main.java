package core;
import java.time.LocalDate;
import dao.ClientsDao;
import tables.Clients;
public class Main
{
	public static void main(String[] args)
	{
		LocalDate.now();
		DaoFactory dao=DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		ClientsDao i=dao.getClienteDao();
		i.save(new Clients("gio","bru","c","a",LocalDate.now(),"Cos",10000));
	}
}
