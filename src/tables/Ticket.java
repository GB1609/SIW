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
	public Ticket() {
	}
	public int getCodiceBiglietto() {
		return codiceBiglietto;
	}
	public void setCodiceBiglietto(int codiceBiglietto) {
		this.codiceBiglietto = codiceBiglietto;
	}
	public int getCodiceEvento() {
		return codiceEvento;
	}
	public void setCodiceEvento(int codiceEvento) {
		this.codiceEvento = codiceEvento;
	}
	public double getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	
}
