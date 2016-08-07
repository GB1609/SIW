package tables;

public class EventPartecipant {
	int partecipant;
	int event;

	public EventPartecipant(int partecipant, int event) {
		this.partecipant = partecipant;
		this.event = event;
	}

	public int getEvent() {
		return this.event;
	}

	public int getPartecipant() {
		return this.partecipant;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public void setPartecipant(int partecipant) {
		this.partecipant = partecipant;
	}
}
