package tables;
public class WishList
{
	String proprietario;
	int codiceLista;
	public WishList(String proprietario, int codiceLista)
	{
		this.proprietario=proprietario;
		this.codiceLista=codiceLista;
	}

	public String getProprietario() {
		return proprietario;
	}

	public void setProprietario(String proprietario) {
		this.proprietario = proprietario;
	}

	public int getCodiceLista() {
		return codiceLista;
	}
	public void setCodiceLista(int codiceLista) {
		this.codiceLista = codiceLista;
	}
	
	
}
