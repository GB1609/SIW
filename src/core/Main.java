package core;

import dao.TicketDao;
import tables.Ticket;

public class Main {
	public static void main(String[] args) {
		System.out.println("main start");
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		TicketDao td = dao.getBigliettoDao();
		for (int i = 0; i < 30; i++)
			td.save(new Ticket(32, 15.00, "Platea", true));
	}
}
