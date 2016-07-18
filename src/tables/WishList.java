package tables;

public class WishList {
	String owner;
	int listCode;
	String name;

	public WishList(String owner, int listCode, String name) {
		this.owner = owner;
		this.listCode = listCode;
		this.name = name;
	}

	public WishList(String owner, String name) {
		this.owner = owner;
		this.name = name;
	}

	public int getListCode() {
		return this.listCode;
	}

	public String getName() {
		return this.name;
	}

	public String getOwner() {
		return this.owner;
	}

	public void setListCode(int codiceLista) {
		this.listCode = codiceLista;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOwner(String proprietario) {
		this.owner = proprietario;
	}
}
