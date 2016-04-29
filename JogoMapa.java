package Ex1;
import java.util.Arrays;
import java.util.Random;


public class JogoMapa {
	
	boolean jogo= false;

	char local;
	int i = 0, j = 0;

	static int n = 7;
	Trainer wild = new Trainer("WILD");
	
	
	
	char[] comando = { 'd', 'd','s','s','d','d','s','s','a','w','a','s','s','d','d','d','s','a','w','w','d','s','a','a','w','s','d'};
	char[][] map = {  { ' ', ' ', ' ', ' ', ' ' , ' ', ' '}, 
					  { ' ', 'g', 'g', ' ', 'g' , 'g', ' '},
					  { ' ', 'g', 'g', ' ', 'g' , 'g', ' '},			
					  { ' ', ' ', ' ', ' ', ' ' , ' ', ' '},
					  { ' ', 'g', 'g', 'g', 'g' , 'g', ' '},
					  { ' ', 'g', 'g', 'g', 'g' , 'g', ' '},
					  { ' ', ' ', ' ', ' ', ' ' , ' ', ' '}};
	
	void anda( char seta){
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
	
	
	void gramado(double numero){
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
	
	void tela(){
		
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
	
	
	public static void main(String[] args){
		
		
		
		JogoMapa m = new JogoMapa();
		int x = 0;
		while(m.comando[x] != '\0'){
			m.anda(m.comando[x]);
			if(m.local == 'g'){
			   Random gerador = new Random(); 
		       double numero = gerador.nextDouble();
		       if(numero > 40) 
		    	   m.gramado(numero);
			}
			m.tela();	
			x++;
		}
		
	}

}