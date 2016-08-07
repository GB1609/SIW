package tables;

public class ClientsWish {
	String usernameClient;
	int listCode;

	public ClientsWish(String usernameCliente, int codiceLista) {
		this.usernameClient = usernameCliente;
		this.listCode = codiceLista;
	}

	public int getlistCode() {
		return this.listCode;
	}

	public String getUsernameClient() {
		return this.usernameClient;
	}

	public void setListCode(int codiceLista) {
		this.listCode = codiceLista;
	}

	public void setUsernameClient(String usernameCliente) {
		this.usernameClient = usernameCliente;
	}
}
