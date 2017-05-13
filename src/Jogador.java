import java.util.ArrayList;

public class Jogador {
	private int id;
	private String nome;
	private ArrayList<Carta> cartasMao;
	private boolean precisaSegurarCuringa;
	private boolean bateu;
	
	public boolean isPrecisaSegurarCuringa() {
		return precisaSegurarCuringa;
	}

	public Jogador(String nome, int id)
	{
		this.id = id;
		this.nome = nome;
		this.cartasMao = new ArrayList<Carta>();
		this.precisaSegurarCuringa = false;
		this.bateu = false;
	}

	public String getNome() {
		return nome;
	}

	public ArrayList<Carta> getCartasMao() {
		return cartasMao;
	}
	
	
	public void receberCarta(Carta carta)
	{
		if(carta.isCoringa())
			this.precisaSegurarCuringa = true;
		else
			this.precisaSegurarCuringa = false;
		
		this.cartasMao.add(carta);
	}
	
	public Carta passarCarta(int indiceCarta)
	{
		return this.cartasMao.remove(indiceCarta-1);
	}

	public String toString()
	{
		String s = this.getNome() + "(" + this.id + "): ";
		
		for(Carta c : this.cartasMao)
		{
			s += c.toString() + " ";
		}
		return s;
	}
	
	public boolean podeBater()
	{
		int valorCarta;
		
		valorCarta = this.cartasMao.get(0).getValor();
				
		for (int i=1; i<cartasMao.size(); i++)
		{
			if(this.cartasMao.get(i).getValor() != valorCarta)
				return false;
		}
		
		return true;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Naipe getNaipeCarta(int indiceCarta)
	{
		return this.cartasMao.get(indiceCarta-1).getNaipe();
	}
	
	public static Jogador getJogador(ArrayList<Jogador> jogadores, int id)
	{
		for (Jogador j : jogadores)
		{
			if (j.getId() == id)
				return j;
		}
		
		return null;
	}
	
	public void bater()
	{
		this.bateu = true;
	}
}
