package tables;
import java.util.HashSet;
import java.util.Set;
public class WishTicket
{
	int codiceLista;
	int codiceBiglietto;
	Set<Clients> personeInteressate=new HashSet<Clients>();
	Set<Ticket> bigliettiInteressati=new HashSet<>();
	public WishTicket(int codiceLista, int codiceBiglietto)
	{
		this.codiceLista=codiceLista;
		this.codiceBiglietto=codiceBiglietto;
	}
}
