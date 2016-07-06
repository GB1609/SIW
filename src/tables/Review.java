package tables;
public class Review
{
	int codiceRecensione;
	int voto;
	String utente;
	int evento;
	public Review(int codiceRecensione, int voto, String utente, int evento)
	{
		this.codiceRecensione=codiceRecensione;
		this.voto=voto;
		this.utente=utente;
		this.evento=evento;
	}
}
