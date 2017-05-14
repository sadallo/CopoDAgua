public class Jogador {
	private int id;
	private String nome;
	private Baralho cartas = new Baralho();
	private boolean precisaSegurarCuringa;
	private boolean emJogo;
	
	public boolean isPrecisaSegurarCuringa() {
		return precisaSegurarCuringa;
	}

	public Jogador(String nome, int id)
	{
		this.id = id;
		this.nome = nome;
		this.precisaSegurarCuringa = false;
		this.emJogo = true;
	}

	public String getNome() {
		return nome;
	}
	
	public void receberCarta(Carta carta)
	{
		if(carta.isCoringa())
			this.precisaSegurarCuringa = true;
		else
			this.precisaSegurarCuringa = false;
		
		this.cartas.adicionarCarta(carta);
	}
	
	public Carta passarCarta(int indiceCarta)
	{
		return this.cartas.removerCarta(indiceCarta);
	}

	public String toString()
	{
		return this.getNome() + "(" + (this.getId()+1) + "): "+ this.cartas;
	}
	
	public void bater()
	{
		this.emJogo = false;
	}
	
	public boolean isVencedor()
	{
		int valorCarta;
		
		valorCarta = this.cartas.getCarta(0).getValor();
				
		for (int i=1; i< this.cartas.size(); i++)
		{
			if(this.cartas.getCarta(i).getValor() != valorCarta)
				return false;
		}
		
		return true;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void adicionarCarta(Carta carta)
	{
		this.cartas.adicionarCarta(carta);
	}
	
	public Carta getCarta(int indiceCarta)
	{
		return this.cartas.getCarta(indiceCarta);
	}
	
	public boolean isEmJogo()
	{
		return this.emJogo;
	}
}
