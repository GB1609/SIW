package tables;

public class Partecipants {
	String name;
	String type;
	String city;
	int idPartecipant;

	public Partecipants(String name, String type, String city) {
		this.name = name;
		this.type = type;
		this.city = city;
	}

	public Partecipants(String name, String type, String city, int idPartecipant) {
		this.name = name;
		this.type = type;
		this.city = city;
		this.idPartecipant = idPartecipant;
	}

	public String getCity() {
		return this.city;
	}

	public int getIdPartecipant() {
		return this.idPartecipant;
	}

	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setIdPartecipant(int idPartecipant) {
		this.idPartecipant = idPartecipant;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}
}
