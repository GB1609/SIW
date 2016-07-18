package tables;

public class WishTicket {
	int listCode;
	int ticketCode;

	// Set<Clients> interessedPerson = new HashSet<Clients>();
	// Set<Ticket> interessedTicket = new HashSet<>();

	public WishTicket(int listCode, int ticketCode) {
		this.listCode = listCode;
		this.ticketCode = ticketCode;
	}

	public int getListCode() {
		return this.listCode;
	}

	public int getTicketCode() {
		return this.ticketCode;
	}

	public void setListCode(int codiceLista) {
		this.listCode = codiceLista;
	}

	public void setTicketCode(int codiceBiglietto) {
		this.ticketCode = codiceBiglietto;
	}
}
