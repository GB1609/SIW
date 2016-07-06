package tables;
public class PartecipantsOfEvents
{
	int partecipante;
	int evento;
	public PartecipantsOfEvents(int partecipante, int evento)
	{
		this.partecipante=partecipante;
		this.evento=evento;
	}
	public int getEvento()
	{
		return this.evento;
	}
	public int getPartecipante()
	{
		return this.partecipante;
	}
	public void setEvento(int evento)
	{
		this.evento=evento;
	}
	public void setPartecipante(int partecipante)
	{
		this.partecipante=partecipante;
	}
}
