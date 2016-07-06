package tables;
public class Ticket
{
	int codiceBiglietto;
	int codiceEvento;
	double prezzo;
	String tipo;
	public Ticket(int codiceBiglietto, int codiceEvento, double prezzo, String tipo)
	{
		this.codiceBiglietto=codiceBiglietto;
		this.codiceEvento=codiceEvento;
		this.prezzo=prezzo;
		this.tipo=tipo;
	}
}
