import java.util.ArrayList;
import java.util.Collections;

public class Baralho {
	private ArrayList<Carta> cartas;
	
	public Baralho (int tamanhoBaralho)
	{
		this.cartas = new ArrayList<Carta>();
		
		for(Naipe naipe : Naipe.values())
		{
			if (naipe == Naipe.J)
			{
				cartas.add(new Carta(0, naipe));
			}
			else
			{
				for(int valor = 1; valor <= tamanhoBaralho; valor++)
				{
					cartas.add(new Carta(valor, naipe));
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
			return this.cartas.remove(0);
		else
			return null;
	}
	
	public void embaralhar()
	{
		Collections.shuffle(this.cartas);
	}
}
