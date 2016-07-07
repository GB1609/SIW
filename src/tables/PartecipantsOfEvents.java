package tables;
public class PartecipantsOfEvents
{
	int partecipant;
	int event;
	public PartecipantsOfEvents(int partecipante, int evento)
	{
		this.partecipant=partecipante;
		this.event=evento;
	}
	public int getEvent()
	{
		return this.event;
	}
	public int getPartecipant()
	{
		return this.partecipant;
	}
	public void setEvent(int evento)
	{
		this.event=evento;
	}
	public void setPartecipant(int partecipante)
	{
		this.partecipant=partecipante;
	}
}
