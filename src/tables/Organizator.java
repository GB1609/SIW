package tables;

import java.time.LocalDate;

public class Organizator extends User {
	public Organizator(String username, String password, String cognome, String nome, LocalDate dataDiNascita,
			String indirizzo) {
		super(username, password, cognome, nome, dataDiNascita, indirizzo);
	}
}
