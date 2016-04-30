package Ex1;
import java.util.Random;
import Ex1.BattleControls;

public class JogoMapa extends Controller{
	
	
	private boolean jogo = true;
	private static int n = 7;
	private int i = 0, j = 0, x= 0;
	private char local = ' ';
	private char[] comando = { 
			'd', 'd', 's','s','d','d','s','s','a','w','a','s','s','d','d','d','s','a','w','w','d','s','a','a','w','s','d'};
	private char[][] map = {
			{ 'P', ' ', ' ', ' ', ' ' , ' ', ' '}, 
					  { ' ', 'g', 'g', ' ', 'g' , 'g', ' '},
					  { ' ', 'g', 'g', ' ', 'g' , 'g', ' '},			
					  { ' ', ' ', ' ', ' ', ' ' , ' ', ' '},
					  { ' ', 'g', 'g', 'g', 'g' , 'g', ' '},
					  { ' ', 'g', 'g', 'g', 'g' , 'g', ' '},
					  { ' ', ' ', ' ', ' ', ' ' , ' ', ' '}};
	Trainer wild = trainerWild();
	BattleControls bc = new BattleControls();;
	
	public Trainer trainerWild(){
		Trainer wild = new Trainer("WILD");
		wild.setAlivePokemon(1);
		return wild;
	}
	
	private class Jogo extends Event{
		
		private void anda( char seta){
			if(seta == 'w') 
				if(i > 0){
					map[i][j] = local;
					i-= 1;
				}
			if(seta == 'a')
				if(j > 0) {
					map[i][j] = local;
					j-= 1;
				}
			if(seta == 's')
				if(i < n-1){
					map[i][j] = local;
					i+= 1;
				}
			if(seta == 'd')
				if(j < n-1){
					map[i][j] = local;
					j+= 1;
				}
			local = map[i][j];
			map[i][j] = 'P';		
		}
	
		private void tela(){
			for(int b = 0; b < n; b++)
				System.out.print(" _");
			System.out.print("\n");
			for(int a = 0; a < n; a++){
				for(int b = 0; b < n; b++){
					if(b == 0){
						System.out.print('|');
						if(a == n-1 && map[a][b] == ' ') System.out.print('_');
						else System.out.print(map[a][b]);
					}
					else{
						if(a == n-1 && map[a][b] == ' ') System.out.print(" _");
						else System.out.print(" " + map[a][b]);
					}
				}
				System.out.println("|");
			}
		}
		
		private void gramado(double numero){
			
		    if(numero < 0.6){
		   	 	wild.getStoredPokemon()[0] = new Pokemon("Rattata", "Normal", 30);
		   	 	wild.getStoredPokemon()[0].a[0] = new Ataque("Tackle", "Normal", 35);
		   	 	wild.setActivePokemon(wild.getStoredPokemon()[0]);
			}
			else
				if(numero < 0.8){	
					wild.getStoredPokemon()[0] = new Pokemon("Pidgey", "Normal", "Flying", 40);
			   	 	wild.getStoredPokemon()[0].a[0] = new Ataque("Gust", "Flying", 35);
			   	 	wild.setActivePokemon(wild.getStoredPokemon()[0]);
		    	 }
				else
					if(numero < 0.97){	
						wild.getStoredPokemon()[0] = new Pokemon("Zubat", "Poison", "Flying", 40);
				   	 	wild.getStoredPokemon()[0].a[0] = new Ataque("Leech Life", "Bug", 20);
				   	 	wild.setActivePokemon(wild.getStoredPokemon()[0]);

					}
					else{
						wild.getStoredPokemon()[0] = new Pokemon("Mew", "Psychic", 100);
				   	 	wild.getStoredPokemon()[0].a[0] = new Ataque("Tackle", "Normal", 35);
				   	 	wild.setActivePokemon(wild.getStoredPokemon()[0]);
					}
		}
		
		public Jogo(long eventTime) {
			super(eventTime);
		}
		
		public void action() {
			long tm = System.currentTimeMillis();
			if(comando[x] == '\0'){
				jogo = false;
				return;
			}
			anda(comando[x++]);
			if(local == 'g'){
			   Random gerador = new Random(); 
			   double numero = gerador.nextDouble();
			   if(numero > 0.4){
				   gramado(numero);
				   bc.StartBattle(bc.trainerAsh(),wild);
				   while (bc.battle == true) {
						bc.addEvent(bc.new NewRound(tm));
						bc.run();
					}
			   }
			}
			tela();	
			addEvent(new Jogo(tm + 1000));
			}

		public String description() {
			return "";
		}
	}
	
	public static void main(String[] args){
		JogoMapa m = new JogoMapa();
		long tm = System.currentTimeMillis();
		while(m.jogo == true){
			m.addEvent(m.new Jogo(tm));
			m.run();
		}
	}
}
