package tables;

public class OrganizatorEvents {
	int eventCode;
	String organizatorCode;

	public OrganizatorEvents(int codiceEvento, String codiceOrganizzatore) {
		this.eventCode = codiceEvento;
		this.organizatorCode = codiceOrganizzatore;
	}

	public int getCodeEvent() {
		return eventCode;
	}

	public void setCodeEvent(int codiceEvento) {
		this.eventCode = codiceEvento;
	}

	public String getOrganizatorCode() {
		return organizatorCode;
	}

	public void setOrganizatorCode(String codiceOrganizzatore) {
		this.organizatorCode = codiceOrganizzatore;
	}
}
