package tables;
public class Events
{
	int eventcode;
	String feedback;
	String organizator;
	int category;
	int information;

	public Events(int eventcode, String feedback, String organizator, int category, int information)
	{
		this.eventcode=eventcode;
		this.feedback=feedback;
		this.organizator=organizator;
		this.category=category;
		this.information=information;
	}
	public int getCategory()
	{
		return this.category;
	}
	public int getEventcode()
	{
		return this.eventcode;
	}
	public String getFeedback()
	{
		return this.feedback;
	}
	public int getInformation()
	{
		return this.information;
	}
	public String getOrganizator()
	{
		return this.organizator;
	}

	public void setCategory(int category)
	{
		this.category=category;
	}
	public void setEventcode(int eventcode)
	{
		this.eventcode=eventcode;
	}
	public void setFeedback(String feedback)
	{
		this.feedback=feedback;
	}
	public void setInformation(int information)
	{
		this.information=information;
	}
	public void setOrganizator(String organizator)
	{
		this.organizator=organizator;
	}

}