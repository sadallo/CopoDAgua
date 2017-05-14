import java.util.ArrayList;
import java.util.Collections;

public class Baralho {
	private ArrayList<Carta> cartas = new ArrayList<Carta>();
	
	public Baralho()
	{		
		
	}
	
	public void construirBaralhoJogo(int valorInicial, int valorFinal)
	{
		for(Naipe naipe : Naipe.values())
		{
			if (naipe == Naipe.J)
			{
				this.adicionarCarta(new Carta(0, naipe));
			}
			else
			{
				for(int valor = valorInicial; valor <= valorFinal; valor++)
				{
					this.adicionarCarta(new Carta(valor, naipe));
				}
			}
		}
	}

	public boolean isVazio()
	{
		return this.cartas.size() == 0;
	}
	
	public Carta darCarta()
	{
		if(!this.isVazio())
			return this.removerCarta(0);
		else
			return null;
	}
	
	public void embaralhar()
	{
		Collections.shuffle(this.cartas);
	}
	
	public void adicionarCarta(Carta carta)
	{
		this.cartas.add(carta);
	}
	
	public Carta removerCarta(int indiceCarta)
	{
		return this.cartas.remove(indiceCarta);
	}
	
	public Carta getCarta(int indiceCarta)
	{
		return this.cartas.get(indiceCarta);
	}
	
	public String toString()
	{
		String s = "";
		for(Carta c: this.cartas)
		{
			s += c + " ";
		}
		
		return s;
	}
	
	public int size()
	{
		return this.cartas.size();
	}
}
