package tables;

import java.util.HashSet;
import java.util.Set;

public class WishTicket
{
	int codiceLista;
	int codiceBiglietto;
	Set <Clients> personeInteressate= new HashSet<Clients>();
	Set <Ticket> bigliettiInteressati=new HashSet<>();
	public WishTicket(int codiceLista, int codiceBiglietto)
	{
		this.codiceLista=codiceLista;
		this.codiceBiglietto=codiceBiglietto;
	}
	public int getCodiceLista() {
		return codiceLista;
	}
	public void setCodiceLista(int codiceLista) {
		this.codiceLista = codiceLista;
	}
	public int getCodiceBiglietto() {
		return codiceBiglietto;
	}
	public void setCodiceBiglietto(int codiceBiglietto) {
		this.codiceBiglietto = codiceBiglietto;
	}
	public Set<Clients> getPersoneInteressate() {
		return personeInteressate;
	}
	public void setPersoneInteressate(Set<Clients> personeInteressate) {
		this.personeInteressate = personeInteressate;
	}
	public Set<Ticket> getBigliettiInteressati() {
		return bigliettiInteressati;
	}
	public void setBigliettiInteressati(Set<Ticket> bigliettiInteressati) {
		this.bigliettiInteressati = bigliettiInteressati;
	}
	
	
}
