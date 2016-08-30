package tables;

public class Ticket {
	int ticketCode;
	int eventCode;
	double price;
	String type;
	boolean sell;
	int quantity;

	public Ticket() {
		this.quantity = 1;
	}

	public Ticket(int eventCode, double price, String type, boolean sell) {
		this.sell = sell;
		this.eventCode = eventCode;
		this.price = price;
		this.type = type;
		this.quantity = 1;
	}

	public Ticket(int eventCode, double price, String type, boolean sell, int quantity) {
		this.sell = sell;
		this.eventCode = eventCode;
		this.price = price;
		this.type = type;
		this.quantity = quantity;
	}

	public Ticket(int ticketCode, int eventCode, double price, String type, boolean sell) {
		this.sell = sell;
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

	public int getQuantity() {
		return this.quantity;
	}

	public int getTicketCode() {
		return this.ticketCode;
	}

	public String getType() {
		return this.type;
	}

	public boolean isSeller() {
		return this.sell;
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

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setSeller(boolean venduto) {
		this.sell = venduto;
	}

	public void setType(String tipo) {
		this.type = tipo;
	}
}
