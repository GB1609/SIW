package tables;

public class Order {

	int codiceOrdine;
	int biglietto;
	String utente;

	public Order(int codiceOrdine, int biglietto, String utente) {
		this.codiceOrdine = codiceOrdine;
		this.biglietto = biglietto;
		this.utente = utente;
	}

	public int getBiglietto() {
		return this.biglietto;
	}

	public int getCodiceOrdine() {
		return this.codiceOrdine;
	}

	public String getUtente() {
		return this.utente;
	}

	public void setBiglietto(int biglietto) {
		this.biglietto = biglietto;
	}

	public void setCodiceOrdine(int codiceOrdine) {
		this.codiceOrdine = codiceOrdine;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

}
