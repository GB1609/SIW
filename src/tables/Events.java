package tables;
public class Events
{
	int codiceEvento;
	String feedback;
	String organizzatore;
	int categoria;
	int informazioni;
	public Events(int codiceEvento, String feedback, String organizzatore, int categoria, int informazioni)
	{
		this.codiceEvento=codiceEvento;
		this.feedback=feedback;
		this.organizzatore=organizzatore;
		this.categoria=categoria;
		this.informazioni=informazioni;
	}
	public int getCategoria()
	{
		return this.categoria;
	}
	public int getCodiceEvento()
	{
		return this.codiceEvento;
	}
	public String getFeedback()
	{
		return this.feedback;
	}
	public int getInformazioni()
	{
		return this.informazioni;
	}
	public String getOrganizzatore()
	{
		return this.organizzatore;
	}
	public void setCategoria(int categoria)
	{
		this.categoria=categoria;
	}
	public void setCodiceEvento(int codiceEvento)
	{
		this.codiceEvento=codiceEvento;
	}
	public void setFeedback(String feedback)
	{
		this.feedback=feedback;
	}
	public void setInformazioni(int informazioni)
	{
		this.informazioni=informazioni;
	}
	public void setOrganizzatore(String organizzatore)
	{
		this.organizzatore=organizzatore;
	}
}
