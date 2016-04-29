package Ex1;
//Cada ação é um evento: ex lighton = usa item
//



public class BattleControls extends Controller {
	private boolean battle = false;
	private boolean round = true;
	private int x = 0, y = 1; // controladores do treinador (x = treinador atacante, y = treinador atacado)
	//private int i = 0, j = 0; // controladores do vetor de pokemon (i = treinador 1, j = treinador 2)
	private int w = 0;// controlador do vetor de ataques
	Trainer[] t = new Trainer[2];
	public BattleControls (){
		
		t[0]    = new Trainer("Ash");
		t[0].p[0] = new Pokemon ("Tauros", "Normal", 75);
		t[0].p[1] = new Pokemon ("Pikachu", "Electric", 80);
		t[0].p[2] = new Pokemon ("Muk", "Poison", 105);
		t[0].p[3] = new Pokemon ("Bulbasaur","Grass", "Poison", 60);
		t[0].p[4] = new Pokemon ("Snorlax", "Normal", 160);
		t[0].p[5] = new Pokemon ("Charizard", "Fire", "Flying", 78);			
		
		t[1]   = new Trainer("Gary");
		t[1].p[0] = new Pokemon ("Nidoqueen", "Poison", "Ground", 90);
		t[1].p[1] = new Pokemon ("Magmar", "Fire", 65);
		t[1].p[2] = new Pokemon ("Blastoise","Water", 79);
		t[1].p[3] = new Pokemon ("Arcanine", "Fire", 90);
		t[1].p[4] = new Pokemon ("Scyther", "Bug", "Flying", 70);
		t[1].p[5] = new Pokemon ("Golem", "Rock", "Ground", 80);
		
		
		t[0].p[0].a[0] = new Ataque("Horn Attack", "Normal", 25);
		t[0].p[0].a[1] = new Ataque("Tackle", "Normal", 35);
		
		t[0].p[1].a[0] = new Ataque("Thunderbolt", "Electric", 15);
		
		t[0].p[2].a[0] = new Ataque("Sludge Bomb", "Poison", 10);

		t[0].p[3].a[0] = new Ataque("Razor Leaf", "Grass", 25);
		t[0].p[3].a[1] = new Ataque("Solar Beam", "Grass", 10);
		
		t[0].p[4].a[0] = new Ataque("Body Slam", "Normal", 15);
		t[0].p[4].a[1] = new Ataque("Hyper Beam", "Normal", 5);

		t[0].p[5].a[0] = new Ataque("Flamethrower", "Fire", 15);
		t[0].p[5].a[1]= new Ataque("Seismic Toss", "Fighting", 25);
		
		t[1].p[0].a[0] = new Ataque("Double Kick", "Fighting", 30);
		t[1].p[0].a[1] = new Ataque("Hyper Beam", "Normal", 5);

		t[1].p[1].a[0] = new Ataque("Flamethrower", "Fire", 15);
		t[1].p[1].a[1] = new Ataque("Fire Blast", "Fire", 5);

		t[1].p[2].a[0] = new Ataque("Rapid Spin", "Normal", 40);
		t[1].p[2].a[1] = new Ataque("Hydro Pump", "Water", 5);

		t[1].p[3].a[0] = new Ataque("Take Down", "Normal", 20);
		t[1].p[3].a[1] = new Ataque("Ember", "Fire", 25);
		
		t[1].p[4].a[0] = new Ataque("Quick Attack", "Normal", 30);
		t[1].p[4].a[1] = new Ataque("Wing Attack", "Flying", 35);
	
		t[1].p[5].a[0] = new Ataque("Rock Throw", "Rock", 15);
		t[1].p[5].a[1] = new Ataque("Earthquake", "Ground", 10);
	
		battle = true;
		
		//System.out.println("Pokemon Trainer " + t[0].getName() + " wants to battle!");
		System.out.printf("Pokemon Trainer %s wants to battle!\r", t[0].getName());
		System.out.println("Pokemon Trainer " + t[0].getName() + " sent out " + t[0].activePokemon.name.toUpperCase() + "!");
	}
	
	//Comeca a batalha inicializando os treinadores, seus Pokemons e seus Ataques 
	private class StartBattle extends Event {
		public StartBattle(long eventTime) {
			super(eventTime);
		}

		public void action() {
			
			
		}

		public String description() {
			return t[x].getName() + " chooses " + t[x].activePokemon.getName() +
				   "\n" + t[y].getName() + " chooses " + t[y].activePokemon.getName();
		}
	}
	
	//Termina o Round e Mostra o estado dos Pokemons
	private class EndRound extends Event {
		public EndRound(long eventTime) {
			super(eventTime);
		}

		public void action() {
			round = false;
		}

		public String description() {
			return t[x].activePokemon.getName() + " HP" + t[x].activePokemon.getHp() + "/" + t[x].activePokemon.getMaxHp() + "  vs  " +
				   t[y].activePokemon.getName() + " HP" + t[y].activePokemon.getHp() + "/" + t[y].activePokemon.getMaxHp(); 
		}
	}
	
	//Escolha de Ataques 
	private class Fight extends Event {
		double effect = 1;
		public Fight(long eventTime) {
			super(eventTime);
		}
		
		double effective(){
			double effect = 1;

			  if(t[x].activePokemon.a[w].type.equals(t[x].activePokemon.type1) || t[x].activePokemon.a[w].type.equals(t[x].activePokemon.type2)) effect *= 1.5;

			  if(t[x].activePokemon.a[w].type.equals("Normal")){
			     if(t[y].activePokemon.type1.equals("Rock")  || t[y].activePokemon.type2.equals("Rock")) effect *= 0.5; 
			     if(t[y].activePokemon.type1.equals("Ghost") || t[y].activePokemon.type2.equals("Ghost")) effect *= 0.0; 
			     return effect;
			  }
			  
			  if(t[x].activePokemon.a[w].type.equals("Fighting")){
			     if(t[y].activePokemon.type1.equals("Normal")  || t[y].activePokemon.type2.equals("Normal"))  effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Rock")    || t[y].activePokemon.type2.equals("Rock"))    effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Ice")     || t[y].activePokemon.type2.equals("Ice"))     effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Flying")  || t[y].activePokemon.type2.equals("Flying"))  effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Poison")  || t[y].activePokemon.type2.equals("Poison"))  effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Bug")     || t[y].activePokemon.type2.equals("Bug"))     effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Psychic") || t[y].activePokemon.type2.equals("Psychic")) effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Ghost")   || t[y].activePokemon.type2.equals("Ghost"))   effect *= 0.0;
			     return effect; 
			  } 
			  if(t[x].activePokemon.a[w].type.equals("Flying")){
			     if(t[y].activePokemon.type1.equals("Fighting") || t[y].activePokemon.type2.equals("Fighting")) effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Bug")      || t[y].activePokemon.type2.equals("Bug"))      effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Grass")    || t[y].activePokemon.type2.equals("Grass"))    effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Rock")     || t[y].activePokemon.type2.equals("Rock"))     effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Electric") || t[y].activePokemon.type2.equals("Electric")) effect *= 0.5;
			     return effect;
			  } 
			  if(t[x].activePokemon.a[w].type.equals("Poison")){
			     if(t[y].activePokemon.type1.equals("Grass")  || t[y].activePokemon.type2.equals("Grass"))  effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Poison") || t[y].activePokemon.type2.equals("Poison")) effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Ground") || t[y].activePokemon.type2.equals("Ground")) effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Rock")   || t[y].activePokemon.type2.equals("Rock"))   effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Ghost")  || t[y].activePokemon.type2.equals("Ghost"))  effect *= 0.5;
			     return effect;
			  } 
			  if(t[x].activePokemon.a[w].type.equals("Ground")){
			     if(t[y].activePokemon.type1.equals("Poison")   || t[y].activePokemon.type2.equals("Poison"))   effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Rock")     || t[y].activePokemon.type2.equals("Rock"))     effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Fire")     || t[y].activePokemon.type2.equals("Fire"))     effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Electric") || t[y].activePokemon.type2.equals("Electric")) effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Grass")    || t[y].activePokemon.type2.equals("Grass"))    effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Bug")      || t[y].activePokemon.type2.equals("Bug"))      effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Flying")   || t[y].activePokemon.type2.equals("Flying"))   effect *= 0.0;
			     return effect;
			  } 
			  
			  if(t[x].activePokemon.a[w].type.equals("Rock")){
			     if(t[y].activePokemon.type1.equals("Flying")   || t[y].activePokemon.type2.equals("Flying"))   effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Bug")      || t[y].activePokemon.type2.equals("Bug"))      effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Fire")     || t[y].activePokemon.type2.equals("Fire"))     effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Ice")      || t[y].activePokemon.type2.equals("Ice"))      effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Ground")   || t[y].activePokemon.type2.equals("Ground"))   effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Fighting") || t[y].activePokemon.type2.equals("Fighting")) effect *= 0.5;
			     return effect;
			  } 
			  if(t[x].activePokemon.a[w].type.equals("Bug")){
			     if(t[y].activePokemon.type1.equals("Grass")    || t[y].activePokemon.type2.equals("Grass"))    effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Psychic")  || t[y].activePokemon.type2.equals("Psychic"))  effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Flying")   || t[y].activePokemon.type2.equals("Flying"))   effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Poison")   || t[y].activePokemon.type2.equals("Poison"))   effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Fire")     || t[y].activePokemon.type2.equals("Fire"))     effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Fighting") || t[y].activePokemon.type2.equals("Fighting")) effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Ghost")    || t[y].activePokemon.type2.equals("Ghost"))    effect *= 0.5;
			     return effect;
			  } 
			  if(t[x].activePokemon.a[w].type.equals("Ghost")){
			     if(t[y].activePokemon.type1.equals("Ghost")   || t[y].activePokemon.type2.equals("Ghost"))   effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Psychic") || t[y].activePokemon.type2.equals("Psychic")) effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Normal")  || t[y].activePokemon.type2.equals("Normal"))  effect *= 0.0; 
			     return effect;
			  } 
			  if(t[x].activePokemon.a[w].type.equals("Fire")){
			     if(t[y].activePokemon.type1.equals("Bug")    || t[y].activePokemon.type2.equals("Bug"))    effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Grass")  || t[y].activePokemon.type2.equals("Grass"))  effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Ice")    || t[y].activePokemon.type2.equals("Ice"))    effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Rock")   || t[y].activePokemon.type2.equals("Rock"))   effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Fire")   || t[y].activePokemon.type2.equals("Fire"))   effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Water")  || t[y].activePokemon.type2.equals("Water"))  effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Dragon") || t[y].activePokemon.type2.equals("Dragon")) effect *= 0.5;
			     return effect;
			  } 
			  if(t[x].activePokemon.a[w].type.equals("Water")){
			     if(t[y].activePokemon.type1.equals("Ground") || t[y].activePokemon.type2.equals("Ground")) effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Rock")   || t[y].activePokemon.type2.equals("Rock"))   effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Fire")   || t[y].activePokemon.type2.equals("Fire"))   effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Water")  || t[y].activePokemon.type2.equals("Water"))  effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Grass")  || t[y].activePokemon.type2.equals("Grass"))  effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Dragon") || t[y].activePokemon.type2.equals("Dragon")) effect *= 0.5;
			     return effect;
			  } 
			  if(t[x].activePokemon.a[w].type.equals("Grass")){
			     if(t[y].activePokemon.type1.equals("Ground") || t[y].activePokemon.type2.equals("Ground")) effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Rock")   || t[y].activePokemon.type2.equals("Rock"))   effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Water")  || t[y].activePokemon.type2.equals("Water"))  effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Flying") || t[y].activePokemon.type2.equals("Flying")) effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Poison") || t[y].activePokemon.type2.equals("Poison")) effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Bug")    || t[y].activePokemon.type2.equals("Bug"))    effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Fire")   || t[y].activePokemon.type2.equals("Fire"))   effect *= 0.5; 
			     if(t[y].activePokemon.type1.equals("Grass")  || t[y].activePokemon.type2.equals("Grass"))  effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Dragon") || t[y].activePokemon.type2.equals("Dragon")) effect *= 0.5;
			     return effect;
			  }
			  if(t[x].activePokemon.a[w].type.equals("Electric")){
			     if(t[y].activePokemon.type1.equals("Flying")   || t[y].activePokemon.type2.equals("Flying"))   effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Water")    || t[y].activePokemon.type2.equals("Water"))    effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Electric") || t[y].activePokemon.type2.equals("Electric")) effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Grass")    || t[y].activePokemon.type2.equals("Grass"))    effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Dragon")   || t[y].activePokemon.type2.equals("Dragon"))   effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Ground")   || t[y].activePokemon.type2.equals("Ground"))   effect *= 0.0;
			     return effect;
			  }  
			  if(t[x].activePokemon.a[w].type.equals("Psychic")){
			     if(t[y].activePokemon.type1.equals("Fighting") || t[y].activePokemon.type2.equals("Fighting")) effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Poison")   || t[y].activePokemon.type2.equals("Poison"))   effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Psychic")  || t[y].activePokemon.type2.equals("Psychic"))  effect *= 0.5;
			     return effect;
			  } 
			  if(t[x].activePokemon.a[w].type.equals("Ice")){
			     if(t[y].activePokemon.type1.equals("Flying") || t[y].activePokemon.type2.equals("Flying")) effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Ground") || t[y].activePokemon.type2.equals("Ground")) effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Grass")  || t[y].activePokemon.type2.equals("Grass"))  effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Dragon") || t[y].activePokemon.type2.equals("Dragon")) effect *= 2.0;
			     if(t[y].activePokemon.type1.equals("Fire")   || t[y].activePokemon.type2.equals("Fire"))   effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Water")  || t[y].activePokemon.type2.equals("Water"))  effect *= 0.5;
			     if(t[y].activePokemon.type1.equals("Ice")    || t[y].activePokemon.type2.equals("Ice"))    effect *= 0.5;
			     return effect; 
			  } 
			  if(t[x].activePokemon.a[w].type.equals("Dragon")){
			     if(t[y].activePokemon.type1.equals("Dragon") || t[y].activePokemon.type2.equals("Dragon")) effect *= 2.0; 
			     return effect;
			  }	
		 return 1;
		}
		
		public void action() {
			effect = effective();
			t[y].activePokemon.decreaseHp((int)effect*t[x].activePokemon.a[w].power);
			if(t[y].activePokemon.hp <= 0){
				t[y].activePokemon.setHp(0);
				t[y].pokemon -= 1;
			}
		}

		public String description() {
			if(effect > 1.5) return t[x].activePokemon.getName() + " used " + t[x].activePokemon.a[w].getName()+
								  "\nit's super effective";
			if(effect < 1 && effect != 0) return t[x].activePokemon.getName() + " used " + t[x].activePokemon.a[w].getName()+
					  							 "\nit's not very effective";
			if(effect == 0) return t[x].activePokemon.getName() + " used " + t[x].activePokemon.a[w].getName()+
						           "\nit doesn't effect " + t[y].activePokemon.getName( )+ " at all"; 
			else return t[x].activePokemon.getName() + " used " + t[x].activePokemon.a[w].getName();
		}
	}

	//Escolha de Itens
	private class Pack extends Event {
		public Pack(long eventTime) {
			super(eventTime);
		}

		public void action() {
			if(t[x].activePokemon.getHp() + 20 > t[x].activePokemon.getMaxHp())
				t[x].activePokemon.setHp(t[x].activePokemon.getMaxHp());
			else
				t[x].activePokemon.increaseHp(20);
			t[x].potions -= 1;
		}

		public String description() {
			return t[x].getName() + " used a Potion";
		}
	}
		
	//Foge da Batalha
	private class Run extends Event {
		public Run(long eventTime) {
			super(eventTime);
		}

		public void action() {
			//se battle = false a batalha acabou
			battle = false;
		}

		public String description() {
			return "Got away safely";
		}
	}

	//Troca de Pokemon trocando o numero do indice do vetor (i = treinador 1; j = treinador 2)
	private class Switch extends Event {
		public Switch(long eventTime) {
			super(eventTime);
		}

		public void action() {
			i += 1;
		}

		public String description() {
			return  t[x].p[i-1].getName() + " comeback!" +
					"\nGo " + t[x].activePokemon.getName();
		}
	}

	//Finaliza a batalha e mostra o vencedor
	private class EndBattle extends Event {
		public EndBattle(long eventTime) {
			super(eventTime);
		}

		public void action() {
				battle = false;
		}

		public String description() {
			if(t[x].pokemon == 0)
			   return  t[x].getName() + " is out of usable pokemon\n" +
				       t[y].getName() + " won the battle";
			else
			   return  t[y].getName() + " is out of usable pokemon\n" +
			           t[x].getName() + " won the battle";
		}
	}

	//Criar um para Rounds
	private class Restart extends Event {
		public Restart(long eventTime) {
			super(eventTime);
		}

		public void action() {
			long tm = System.currentTimeMillis();
			// Instead of hard-wiring, you could parse
			// configuration information from a text
			// file here:
			
			addEvent(new StartBattle(tm));
			addEvent(new Fight(tm + 1000));   
			addEvent(new Switch(tm + 1500));
			addEvent(new EndRound(tm + 2000));
			addEvent(new Pack(tm + 2500));
			addEvent(new Switch(tm + 3000));
			addEvent(new Fight(tm + 4000));
			addEvent(new EndRound(tm + 4500));
			//addEvent(new Run(tm + 5000));
			//Caso um dos treinadores esteja sem pokemons, a batalha acaba
			addEvent(new EndBattle(tm + 6000));
			
			
		}

		public String description() {
			return "Start the Battle";
		}
	}

	public static void main(String[] args) {
		BattleControls bc = new BattleControls();
		long tm = System.currentTimeMillis();
		bc.addEvent(bc.new Restart(tm));
		bc.run();
	}
} 
