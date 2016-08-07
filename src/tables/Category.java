package tables;

public class Category {
	int categoryCode;
	String name;

	public Category(int codiceCategoria, String nome) {
		this.categoryCode = codiceCategoria;
		this.name = nome;

	}

	public void setCategoryCode(int codiceCategoria) {
		this.categoryCode = codiceCategoria;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public String getName() {
		return name;
	}

}
