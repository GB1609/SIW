package tables;

public class EventInfo {
	
	int code;
	String name;
	String city;
	int bigl;
	int rim;
	
	public EventInfo(int eventCode, String name, String city, int bigl , int rim) {
		this.code = eventCode;
		this.name = name;
		this.city = city;
		this.bigl = bigl;
		this.rim = rim;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int eventcode) {
		this.code = eventcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getBigl() {
		return bigl;
	}

	public void setBigl(int bigl) {
		this.bigl = bigl;
	}

	public int getRim() {
		return rim;
	}

	public void setRim(int rim) {
		this.rim = rim;
	}
	
	

}
