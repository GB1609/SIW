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

	public int getCodiceOrdine() {
		return codiceOrdine;
	}

	public void setCodiceOrdine(int codiceOrdine) {
		this.codiceOrdine = codiceOrdine;
	}

	public int getBiglietto() {
		return biglietto;
	}

	public void setBiglietto(int biglietto) {
		this.biglietto = biglietto;
	}

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}
	
	
	
	

}
