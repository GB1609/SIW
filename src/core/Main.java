package core;

import java.time.LocalDate;
import java.time.Month;

import dao.ClientsDao;
import tables.Clients;

public class Main {
	public static void main(String[] args) {
		System.out.println("main start");
		DaoFactory dao = DaoFactory.getDAOFactory(DaoFactory.POSTGRESQL);
		ClientsDao cd = dao.getClientsDao();
		cd.save(new Clients("gio", "prova", "b", "gio", LocalDate.of(1994, Month.JANUARY, 8), "unical", 0));
	}
}