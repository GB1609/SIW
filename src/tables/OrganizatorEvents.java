package tables;

public class OrganizatorEvents {
	
	int codiceEvento;
	String codiceOrganizzatore;
	
	public OrganizatorEvents(int codiceEvento, String codiceOrganizzatore) {
		this.codiceEvento = codiceEvento;
		this.codiceOrganizzatore = codiceOrganizzatore;
	}

	public int getCodiceEvento() {
		return codiceEvento;
	}

	public void setCodiceEvento(int codiceEvento) {
		this.codiceEvento = codiceEvento;
	}

	public String getCodiceOrganizzatore() {
		return codiceOrganizzatore;
	}

	public void setCodiceOrganizzatore(String codiceOrganizzatore) {
		this.codiceOrganizzatore = codiceOrganizzatore;
	}
	
	
	
	
}
