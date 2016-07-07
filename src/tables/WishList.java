package tables;
public class WishList
{
	String owner;
	int listCode;
	public WishList(String proprietario, int codiceLista)
	{
		this.owner=proprietario;
		this.listCode=codiceLista;
	}
	public String getOwner()
	{
		return owner;
	}
	public void setOwner(String proprietario)
	{
		this.owner=proprietario;
	}
	public int getListCode()
	{
		return listCode;
	}
	public void setListCode(int codiceLista)
	{
		this.listCode=codiceLista;
	}
}
