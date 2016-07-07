package tables;
public class Order
{
	int orderCode;
	int ticket;
	String user;
	public Order(int codiceOrdine, int biglietto, String utente)
	{
		this.orderCode=codiceOrdine;
		this.ticket=biglietto;
		this.user=utente;
	}
	public int getOrderCode()
	{
		return orderCode;
	}
	public void setOrderCode(int codiceOrdine)
	{
		this.orderCode=codiceOrdine;
	}
	public int getTicket()
	{
		return ticket;
	}
	public void setTicket(int biglietto)
	{
		this.ticket=biglietto;
	}
	public String getUser()
	{
		return user;
	}
	public void setUser(String utente)
	{
		this.user=utente;
	}
}
