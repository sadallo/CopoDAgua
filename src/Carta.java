
public class Carta {
	private static final String[] Simbolo = { "*", "A", "2", "3", "4", 
				"5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	
	private Naipe naipe;
	private int valor;
	
	public Carta(int valor, Naipe naipe)
	{
		this.valor = valor;
		this.naipe = naipe;
	}
	
	public Naipe getNaipe() {
		return naipe;
	}

	public int getValor() {
		return valor;
	}

	public String toString()
	{
		return ( Simbolo[this.valor] + this.naipe);
	}
	
	public boolean isCoringa()
	{
		return this.naipe == Naipe.J;
	}
	
}
