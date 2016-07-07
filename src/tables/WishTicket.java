package tables;
import java.util.HashSet;
import java.util.Set;
public class WishTicket
{
	int listCode;
	int ticketCode;
	Set<Clients> interessedPerson=new HashSet<Clients>();
	Set<Ticket> interessedTicket=new HashSet<>();
	public WishTicket(int codiceLista, int codiceBiglietto)
	{
		this.listCode=codiceLista;
		this.ticketCode=codiceBiglietto;
	}
	public int getListCode()
	{
		return listCode;
	}
	public void setListCode(int codiceLista)
	{
		this.listCode=codiceLista;
	}
	public int getTicketCode()
	{
		return ticketCode;
	}
	public void setTicketCode(int codiceBiglietto)
	{
		this.ticketCode=codiceBiglietto;
	}
	public Set<Clients> getInteressedPerson()
	{
		return interessedPerson;
	}
	public void setInteressedPerson(Set<Clients> personeInteressate)
	{
		this.interessedPerson=personeInteressate;
	}
	public Set<Ticket> getInteressedTicket()
	{
		return interessedTicket;
	}
	public void setInteressedTicket(Set<Ticket> bigliettiInteressati)
	{
		this.interessedTicket=bigliettiInteressati;
	}
}
