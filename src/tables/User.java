package tables;
import java.time.LocalDate;
public class User
{
	String username;
	String password;
	String lastName;
	String firstName;
	LocalDate birthDate;
	String address;
	public User(String username, String password, String lastName, String firstName, LocalDate birthDate, String address)
	{
		this.username=username;
		this.password=password;
		this.lastName=lastName;
		this.firstName=firstName;
		this.birthDate=birthDate;
		this.address=address;
	}
	public String getAddress()
	{
		return this.address;
	}
	public LocalDate getBirthDate()
	{
		return this.birthDate;
	}
	public String getFirstName()
	{
		return this.firstName;
	}
	public String getLastName()
	{
		return this.lastName;
	}
	public String getPassword()
	{
		return this.password;
	}
	public String getUsername()
	{
		return this.username;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	public void setBirthDate(LocalDate birthDate)
	{
		this.birthDate=birthDate;
	}
	public void setFirstName(String firstName)
	{
		this.firstName=firstName;
	}
	public void setLastName(String lastName)
	{
		this.lastName=lastName;
	}
	public void setPassword(String password)
	{
		this.password=password;
	}
	public void setUsername(String username)
	{
		this.username=username;
	}
}