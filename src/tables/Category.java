package tables;
public class Category
{
	int categoryCode;
	String name;
	int father;
	public Category(int codiceCategoria, String nome, int father)
	{
		this.categoryCode=codiceCategoria;
		this.name=nome;
		this.father=father;
	}
	public int getCategoryCode()
	{
		return this.categoryCode;
	}
	public int getFather()
	{
		return this.father;
	}
	public String getName()
	{
		return this.name;
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
		this.father=figlio;
	}
}
