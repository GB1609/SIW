package core;
import dao.EventsDao;
import tables.Events;
public class Main
{
	public static void main(String[] args)
	{
		System.out.println("main start");
		DaoFactory dao=DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		EventsDao i=dao.getEventoDao();
		i.save(new Events(1,"stupendo!","gb1609",13,3,1));
		i.save(new Events(2,"stupendo!","gb1609",13,3,2));
		i.save(new Events(3,"stupendo!","gb1609",13,3,3));
		i.save(new Events(5,"stupendo!","gb1609",13,3,4));
		i.save(new Events(4,"stupendo!","gb1609",13,3,5));
		i.save(new Events(5,"assolutamente da non perdere","vic",32,5,7));
	}
}
