package tables;
public class Place
{
	int capienza;
	String nome;
	String citta;
	String tipo;
	public Place(int capienza, String nome, String citta, String tipo)
	{
		this.capienza=capienza;
		this.nome=nome;
		this.citta=citta;
		this.tipo=tipo;
	}
	public int getCapienza()
	{
		return this.capienza;
	}
	public String getCitta()
	{
		return this.citta;
	}
	public String getNome()
	{
		return this.nome;
	}
	public String getTipo()
	{
		return this.tipo;
	}
	public void setCapienza(int capienza)
	{
		this.capienza=capienza;
	}
	public void setCitta(String citta)
	{
		this.citta=citta;
	}
	public void setNome(String nome)
	{
		this.nome=nome;
	}
	public void setTipo(String tipo)
	{
		this.tipo=tipo;
	}
}
