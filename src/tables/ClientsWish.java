package tables;
public class ClientsWish
{
	String usernameCliente;
	int codiceLista;
	public ClientsWish(String usernameCliente, int codiceLista)
	{
		this.usernameCliente=usernameCliente;
		this.codiceLista=codiceLista;
	}
	public int getCodiceLista()
	{
		return this.codiceLista;
	}
	public String getUsernameCliente()
	{
		return this.usernameCliente;
	}
	public void setCodiceLista(int codiceLista)
	{
		this.codiceLista=codiceLista;
	}
	public void setUsernameCliente(String usernameCliente)
	{
		this.usernameCliente=usernameCliente;
	}
}
