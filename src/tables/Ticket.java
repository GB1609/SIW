package tables;

public class Ticket {
	int ticketCode;
	int eventCode;
	double price;
	String type;
	boolean venduto;

	public Ticket() {
	}

	public Ticket(int eventCode, double price, String type, boolean sell) {
		this.venduto = sell;
		this.eventCode = eventCode;
		this.price = price;
		this.type = type;
	}

	public Ticket(int ticketCode, int eventCode, double price, String type, boolean sell) {
		this.venduto = sell;
		this.ticketCode = ticketCode;
		this.eventCode = eventCode;
		this.price = price;
		this.type = type;
	}

	public int getCodeEvent() {
		return this.eventCode;
	}

	public double getPrice() {
		return this.price;
	}

	public int getTicketCode() {
		return this.ticketCode;
	}

	public String getType() {
		return this.type;
	}

	public boolean isSeller() {
		return this.venduto;
	}

	public void setCodeEvent(int codiceEvento) {
		this.eventCode = codiceEvento;
	}

	public void setCodeTicket(int codiceBiglietto) {
		this.ticketCode = codiceBiglietto;
	}

	public void setPrice(double prezzo) {
		this.price = prezzo;
	}

	public void setSeller(boolean venduto) {
		this.venduto = venduto;
	}

	public void setType(String tipo) {
		this.type = tipo;
	}
}
