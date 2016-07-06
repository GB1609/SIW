package tables;

public class Events {
	int eventcode;
	String feedback;
	String organizator;
	int category;
	int information;
	int partecipant;

	public Events(int eventcode, String feedback, String organizator, int category, int information, int partecipant) {
		this.eventcode = eventcode;
		this.feedback = feedback;
		this.organizator = organizator;
		this.category = category;
		this.information = information;
		this.partecipant = partecipant;
	}

	public int getCategory() {
		return this.category;
	}

	public int getEventcode() {
		return this.eventcode;
	}

	public String getFeedback() {
		return this.feedback;
	}

	public int getInformation() {
		return this.information;
	}

	public String getOrganizator() {
		return this.organizator;
	}

	public int getPartecipant() {
		return this.partecipant;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setEventcode(int eventcode) {
		this.eventcode = eventcode;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public void setInformation(int information) {
		this.information = information;
	}

	public void setOrganizator(String organizator) {
		this.organizator = organizator;
	}

	public void setPartecipant(int partecipant) {
		this.partecipant = partecipant;
	}
}