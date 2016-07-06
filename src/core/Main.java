package core;
import java.time.LocalDate;
public class Main
{
	public static void main(String[] args)
	{
		LocalDate.now();
		DaoFactory dao=DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		dao.getClienteDao();
	}
}
