package Ex1;
import java.util.Random;
import Ex1.BattleControls;

public class JogoMapa extends Controller{
	
	boolean jogo= false;
	static int n = 7;
	int i = 0, j = 0, x= 0;
	char local = ' ';
	char[] comando = { 
			'd', 'd', 's','s','d','d','s','s','a','w','a','s','s','d','d','d','s','a','w','w','d','s','a','a','w','s','d'};
	char[][] map = {
			{ 'P', ' ', ' ', ' ', ' ' , ' ', ' '}, 
					  { ' ', 'g', 'g', ' ', 'g' , 'g', ' '},
					  { ' ', 'g', 'g', ' ', 'g' , 'g', ' '},			
					  { ' ', ' ', ' ', ' ', ' ' , ' ', ' '},
					  { ' ', 'g', 'g', 'g', 'g' , 'g', ' '},
					  { ' ', 'g', 'g', 'g', 'g' , 'g', ' '},
					  { ' ', ' ', ' ', ' ', ' ' , ' ', ' '}};
	Trainer wild = new Trainer("WILD");
	BattleControls bc = new BattleControls(wild);;
	
	private class Capture extends Event {
		boolean capture = false;
		boolean pokeballs = false;
		double captureRate;
		
		public Capture(long eventTime) {
			super(eventTime);
		}

		public void action() {
			
			if(bc.t[0].pokeballs == 0)
				return;
			else{
				pokeballs = true;
				captureRate = wild.p[0].getHp() / wild.p[0].getMaxHp(); 
				Random gerador = new Random(); 
				double numero = gerador.nextDouble();
				if(numero < captureRate){
					bc.t[0].bill[0] = wild.p[0];
					capture = true;
					bc.battle = false;
				}
			}	
		}

		public String description() {
			
			if(pokeballs = false){
				return bc.t[0].getName() + " doesn't have any pokeballs";
			}
			else{
				if(capture == true){
					return bc.t[0].getName() + " used a Pokeball. " + wild.p[0].getName() + " was caught!";
				}
				else{
					return bc.t[0].getName() + " used a Pokeball. " + wild.p[0].getName() + " was caught!";
				}
			}
		}
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
		    if(numero < 60){
		    	wild.p[0] = new Pokemon ("Rattata", "Normal", 30);
				wild.p[0].a[0] = new Ataque("Tackle", "Normal", 35);
			}
			else
				if(numero < 80){	
			   		wild.p[0] = new Pokemon ("Pidgey", "Normal", "Flying", 40);
					wild.p[0].a[0] = new Ataque("Gust", "Flying", 35);
		    	 }
				else
					if(numero < 97){	
						wild.p[0] = new Pokemon ("Zubat", "Poison", "Flying", 40);
						wild.p[0].a[0] = new Ataque("Leech Life", "Bug", 20);
					}
					else{
						wild.p[0] = new Pokemon ("Mew", "Psychic", 100);
						wild.p[0].a[0] = new Ataque("Tackle", "Normal", 35);
					}
		    wild.pokemon = 1;
		}
		
		public Jogo(long eventTime) {
			super(eventTime);
		}
		
		public void action() {
			long tm = System.currentTimeMillis();
			anda(comando[x++]);
			if(local == 'g'){
			   Random gerador = new Random(); 
			   double numero = gerador.nextDouble();
			   if(numero > 40){
				   bc.addEvent(new bc.Round(tm + 1000));
				   gramado(numero);
			   }
			}
			tela();	
			addEvent(new Jogo(tm + 2000));
			}

		public String description() {
			return "";
		}
	}
	
	public static void main(String[] args){
		JogoMapa m = new JogoMapa();
		long tm = System.currentTimeMillis();
		m.addEvent(m.new Jogo(tm));
		m.run();
	}
}
