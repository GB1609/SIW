package tables;

public class Order {
	int orderCode;
	int ticket;
	String user;

	public Order(int codiceOrdine, int biglietto, String utente) {
		this.orderCode = codiceOrdine;
		this.ticket = biglietto;
		this.user = utente;
	}

	public Order(int biglietto, String utente) {
		this.ticket = biglietto;
		this.user = utente;
	}

	public int getOrderCode() {
		return this.orderCode;
	}

	public int getTicket() {
		return this.ticket;
	}

	public String getUser() {
		return this.user;
	}

	public void setOrderCode(int codiceOrdine) {
		this.orderCode = codiceOrdine;
	}

	public void setTicket(int biglietto) {
		this.ticket = biglietto;
	}

	public void setUser(String utente) {
		this.user = utente;
	}
}
