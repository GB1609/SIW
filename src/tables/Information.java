package tables;

import java.time.LocalDate;

public class Information {
	int informationid;
	LocalDate date;
	String locality;
	String description;
	String city;
	String name;
	String img;

	public Information(int idInformazione, LocalDate data, String luogo, String descrizione, String citta, String name,
			String img) {
		this.informationid = idInformazione;
		this.date = data;
		this.locality = luogo;
		this.description = descrizione;
		this.city = citta;
		this.name = name;
		this.img = img;
	}

	public Information(LocalDate data, String luogo, String descrizione, String citta, String name, String img) {
		this.date = data;
		this.locality = luogo;
		this.description = descrizione;
		this.city = citta;
		this.name = name;
		this.img = img;
	}

	public String getCity() {
		return this.city;
	}

	public LocalDate getDate() {
		return this.date;
	}

	public String getDescription() {
		return this.description;
	}

	public String getImg() {
		return this.img;
	}

	public int getInformationid() {
		return this.informationid;
	}

	public int getInformationId() {
		return this.informationid;
	}

	public String getLocality() {
		return this.locality;
	}

	public String getName() {
		return this.name;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIdInformazione(int idInformazione) {
		this.informationid = idInformazione;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public void setInformationid(int informationid) {
		this.informationid = informationid;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public void setLuogo(String luogo) {
		this.locality = luogo;
	}

	public void setName(String name) {
		this.name = name;
	}
}
