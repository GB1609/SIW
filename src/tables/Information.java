package tables;

import java.time.LocalDate;

public class Information {
	int informationid;
	LocalDate date;
	String locality;
	String description;
	String city;

	public Information(int idInformazione, LocalDate data, String luogo, String descrizione, String citta) {
		this.informationid = idInformazione;
		this.date = data;
		this.locality = luogo;
		this.description = descrizione;
		this.city = citta;
	}

	public Information(LocalDate data, String luogo, String descrizione, String citta) {
		this.date = data;
		this.locality = luogo;
		this.description = descrizione;
		this.city = citta;
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

	public int getInformationId() {
		return this.informationid;
	}

	public String getLocality() {
		return this.locality;
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

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public void setLuogo(String luogo) {
		this.locality = luogo;
	}
}
