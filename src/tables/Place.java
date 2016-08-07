package tables;

public class Place {
	int capacity;
	String name;
	String city;
	String type;

	public Place(int capienza, String nome, String citta, String tipo) {
		this.capacity = capienza;
		this.name = nome;
		this.city = citta;
		this.type = tipo;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public String getCity() {
		return this.city;
	}

	public String getName() {
		return this.name;
	}

	public String getType() {
		return this.type;
	}

	public void setCapacity(int capienza) {
		this.capacity = capienza;
	}

	public void setCity(String citta) {
		this.city = citta;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public void setType(String tipo) {
		this.type = tipo;
	}
}
