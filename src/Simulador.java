import java.util.ArrayList;
import java.util.Scanner;


public class Simulador {
	public static Scanner sc = new Scanner(System.in);
	private ArrayList<Jogador> jogadores = new ArrayList<Jogador>();
	private int numeroJogadores;
	private int numJogadoresEmJogo;
	private Jogador vencedor;
	private Jogador perdedor;
	private Jogador jogadorAtual;
	private Jogador jogadorProx;
	private Baralho baralhoJogo = new Baralho();
	
	public Simulador()
	{
	}
	
	public void prepararJogo()
	{
		System.out.print("Numero de jogadores: ");
		this.numeroJogadores = Integer.parseInt(Simulador.sc.nextLine());
		this.numJogadoresEmJogo = this.numeroJogadores;

		for(int i=0; i < this.numeroJogadores; i++)
		{
			System.out.print("Jogador " + (i+1) + ": ");
			String nome = Simulador.sc.nextLine();
			this.jogadores.add(new Jogador(nome, i));
		}
		
		this.baralhoJogo.construirBaralhoJogo(1, this.numeroJogadores);
		this.baralhoJogo.embaralhar();
		
		int aux = 0;
		while(!baralhoJogo.isVazio())
		{
			jogadores.get(aux).adicionarCarta(baralhoJogo.darCarta());
			aux = (aux + 1) % numeroJogadores;
		}
		
		this.jogadorAtual = this.jogadores.get(0);
		this.jogadorProx = this.jogadores.get(1);
	}
	
	public void jogar()
	{
		System.out.println("\nInstruções:\n"
				+ "1 - Para passar uma carta, digite a posicao da mesma (1-5) em sua mão\n"
				+ "2 - Para bater digite Bx, sendo x o seu id, a qualquer momento\n"
				+ "3 - Lembre-se que você deve segurar o J por, no mínimo, uma rodada"
				+ "4 - Não trapaceie");
		while(this.perdedor == null) 
		{
			if(this.numJogadoresEmJogo == 1)
			{
				this.perdedor = this.jogadorAtual;
				break;
			}
			
			System.out.println("\nVez de " + this.jogadorAtual);
			
			System.out.print("- ");
			String comando = Simulador.sc.nextLine();
			
			
			if(comando.startsWith("B") || comando.startsWith("b"))
			{ // caso alguem tenha batido
				int idJogadorBatendo = Integer.parseInt(comando.substring(1))-1;
				Jogador jogadorBatendo = this.jogadores.get(idJogadorBatendo);
				jogadorBatendo.bater();
				this.numJogadoresEmJogo--;
				if(this.vencedor == null)
					this.vencedor = jogadorBatendo;
				
				if(this.jogadorAtual.getId() == idJogadorBatendo)
					this.passarVez();
			}
			else
			{
				// caso o jogadorAtual esteja passando uma carta
				int indiceCarta = Integer.parseInt(comando) - 1;
				Carta c = this.jogadorAtual.getCarta(indiceCarta);

				if(!c.isCoringa() || !this.jogadorAtual.isPrecisaSegurarCuringa())
				{
					System.out.println("Passando a carta " + c);
					this.jogadorProx.receberCarta(this.jogadorAtual.passarCarta(indiceCarta));
					this.passarVez();
				}
			}
		}
		
		this.anunciarPerdedor();
	}
	
	public void passarVez()
	{
		Jogador aux = this.jogadorProx;
		do
		{ // procura um jogador que nao houver batido
			this.jogadorProx = this.jogadores.get((this.jogadorProx.getId()+1)%this.numeroJogadores);
		} while (!(this.jogadorProx.isEmJogo()));
		this.jogadorAtual = aux;
	}
	
	public void anunciarPerdedor()
	{
		// se o primeiro a baixar tiver roubado, ele se torna o perdedor
		if(!this.vencedor.isVencedor())
		{
			System.out.println("\nParece que alguém trapaceou ao bater primeiro!");
			this.perdedor = this.vencedor;
		}
		System.out.println("\nVocê bebe água, " + this.perdedor.getNome() + "!!!");
	}
}
