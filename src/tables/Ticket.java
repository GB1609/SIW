package tables;
public class Ticket
{
	int ticketCode;
	int eventCode;
	double price;
	String type;
	boolean venduto;
	public Ticket(int codiceBiglietto, int codiceEvento, double prezzo, String tipo, boolean venduto)
	{
		this.venduto=venduto;
		this.ticketCode=codiceBiglietto;
		this.eventCode=codiceEvento;
		this.price=prezzo;
		this.type=tipo;
	}
	public Ticket()
	{}
	public boolean isSeller()
	{
		return venduto;
	}
	public void setSeller(boolean venduto)
	{
		this.venduto=venduto;
	}
	public int getTicketCode()
	{
		return ticketCode;
	}
	public void setCodeTicket(int codiceBiglietto)
	{
		this.ticketCode=codiceBiglietto;
	}
	public int getCodeEvent()
	{
		return eventCode;
	}
	public void setCodeEvent(int codiceEvento)
	{
		this.eventCode=codiceEvento;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double prezzo)
	{
		this.price=prezzo;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String tipo)
	{
		this.type=tipo;
	}
}
