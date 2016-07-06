package tables;

public class Category {
	
	int codiceCategoria;
	String nome;
	int figlio;
	
	public Category(int codiceCategoria, String nome, int figlio) {
		this.codiceCategoria = codiceCategoria;
		this.nome = nome;
		this.figlio = figlio;
	}

	public void setCodiceCategoria(int codiceCategoria) {
		this.codiceCategoria = codiceCategoria;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setFiglio(int figlio) {
		this.figlio = figlio;
	}

	public int getCodiceCategoria() {
		return codiceCategoria;
	}

	public String getNome() {
		return nome;
	}

	public int getFiglio() {
		return figlio;
	}
	
	

}
