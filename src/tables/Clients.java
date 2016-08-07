package tables;

import java.time.LocalDate;

public class Clients extends User {
	float credit;

	public Clients(String username, String password, String lastName, String firstName, LocalDate birthDate,
			String address, float credit) {
		super(username, password, lastName, firstName, birthDate, address);
		this.credit = credit;
	}

	public float getCredit() {
		return this.credit;
	}

	public void setCredit(float credit) {
		this.credit = credit;
	}
}
