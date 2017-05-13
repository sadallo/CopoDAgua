import java.util.ArrayList;
import java.util.Scanner;

public class Copodagua {

	private static Scanner sc;

	public static void main(String[] args) {
		// jogadores tem id de 6 a 6+N
		
	
		int numeroJogadores;
		ArrayList<Jogador> jogadores = new ArrayList<>();
		
		System.out.print("Numero de jogadores: ");
		sc = new Scanner(System.in);
		
		numeroJogadores = Integer.parseInt(sc.nextLine());
				
		Baralho baralho = new Baralho(numeroJogadores);
		baralho.embaralhar();

		for (int i=0; i < numeroJogadores; i++)
		{
			int id = 6+i;
			System.out.print("Jogador " + id + ": ");
			String nome = sc.nextLine();
			Jogador jogador = new Jogador(nome, id);			
			jogadores.add(jogador);
		}
		
		int cont = 0;
		
		// distribui as cartas
		while(!baralho.isVazio())
		{
			jogadores.get(cont++ % numeroJogadores).receberCarta(baralho.darCarta());
		}		
		
		boolean repeat;
		Jogador jogadorAtual;
		String entrada;
		int comando;
		int i=0;
		while (jogadores.size() > 1)
		{
			for(Jogador j: jogadores)
				System.out.println(j);
			
			jogadorAtual = jogadores.get(i % jogadores.size());
			System.out.println("\nVez de " + jogadorAtual.getNome() + "(" + jogadorAtual.getId() + ")");
			System.out.println("Para passar uma carta digite sua posicao. Para baixar as cartas digite seu id.");
			
			try{
			
			repeat = false;
			do
			{
				System.out.print("- ");
				entrada = sc.nextLine();
				comando = Integer.parseInt(entrada);

				if(comando >=1 && comando <= 5)
				{
					if(jogadorAtual.getNaipeCarta(comando) == Naipe.J && jogadorAtual.isPrecisaSegurarCuringa())
					{
						System.out.println("tentar passar curinga sem segurar uma rodada");
						repeat = true;
						// tentar passar curinga sem segurar uma rodada
					}
					else
					{
						System.out.println("passa carta ao proximo jogador");
	
						// passa carta ao proximo jogador
						Jogador jogadorProx = jogadores.get((jogadores.indexOf(jogadorAtual) + 1) % jogadores.size());
						System.out.println("prox: " + jogadorProx.getNome());
	
						jogadorProx.receberCarta(jogadorAtual.passarCarta(comando));
						
						repeat = false;
					}
				}
				else if(comando >= 6 && comando <= numeroJogadores+6)
				{
					// baixar cartas do jogador cujo id foi digitado
					Jogador jogadorBaixando = Jogador.getJogador(jogadores, comando);
					if(jogadorBaixando.podeBater())
					{
						jogadorBaixando.bater();
						if(jogadorAtual.getId() != comando)
						{
							repeat = true;
						}
					}
					else
					{
						repeat = true;
					}	
				}
			}while(repeat);
			
			}
			catch (Exception e)
			{
				
			}
			
			i++;
		}		
		
		/*while(true)
		{
			for(int i=0; i < numeroJogadores; i++){
				
				for(Jogador j: jogadores)
				{
					System.out.println(j);
				}
				
				Jogador jogadorAtual = jogadores.get(i);
				String comandoExecutado = sc.nextLine();
				
				if(comandoExecutado.length() == 1)
				{
					Carta carta = jogadorAtual.getCarta(Integer.parseInt(comandoExecutado));
					
					while(carta.isCoringa() && jogadorAtual.isPrecisaSegurarCuringa())
					{
						do
						{
							comandoExecutado = sc.nextLine();
						} while (comandoExecutado.length() != 1);
						
						carta = jogadorAtual.getCarta(Integer.parseInt(comandoExecutado));
					}
					jogadorAtual.passarCarta(Integer.parseInt(comandoExecutado));
					jogadores.get((i+1) % numeroJogadores);
				}
				else
				{
					
				}	
			}
		}*/
	}
}
