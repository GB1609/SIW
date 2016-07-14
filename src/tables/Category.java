package tables;
public class Category
{
	int categoryCode;
	String name;
	int son;
	public Category(int codiceCategoria, String nome, int figlio)
	{
		this.categoryCode=codiceCategoria;
		this.name=nome;
		this.son=figlio;
	}
	public void setCategoryCode(int codiceCategoria)
	{
		this.categoryCode=codiceCategoria;
	}
	public void setName(String nome)
	{
		this.name=nome;
	}
	public void setSon(int figlio)
	{
		this.son=figlio;
	}
	public int getCategoryCode()
	{
		return categoryCode;
	}
	public String getName()
	{
		return name;
	}
	public int getSon()
	{
		return son;
	}
}
