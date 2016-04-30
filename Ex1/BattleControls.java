package Ex1;


public class BattleControls extends Controller {
	private boolean battle = false;
	private int x = 0, y = 1;
	// controladores do treinador (x = treinador
	// atacante, y = treinador atacado)
	// private int i = 0, j = 0; // controladores do vetor de pokemon (i =
	// treinador 1, j = treinador 2)
	private int w = 0;// controlador do vetor de ataques
	Trainer[] t = new Trainer[2];

	// Comeca a batalha inicializando os treinadores, seus Pokemons e seus
	// Ataques
	public void StartBattle() {
		battle = true;

		t[0] = new Trainer("Ash");
		t[0].getStoredPokemon()[0] = new Pokemon("Tauros", "Normal", 75);
		t[0].getStoredPokemon()[1] = new Pokemon("Pikachu", "Electric", 80);
		t[0].getStoredPokemon()[2] = new Pokemon("Muk", "Poison", 105);
		t[0].getStoredPokemon()[3] = new Pokemon("Bulbasaur", "Grass", "Poison", 60);
		t[0].getStoredPokemon()[4] = new Pokemon("Snorlax", "Normal", 160);
		t[0].getStoredPokemon()[5] = new Pokemon("Charizard", "Fire", "Flying", 78);

		t[1] = new Trainer("Gary");
		t[1].getStoredPokemon()[0] = new Pokemon("Nidoqueen", "Poison", "Ground", 90);
		t[1].getStoredPokemon()[1] = new Pokemon("Magmar", "Fire", 65);
		t[1].getStoredPokemon()[2] = new Pokemon("Blastoise", "Water", 79);
		t[1].getStoredPokemon()[3] = new Pokemon("Arcanine", "Fire", 90);
		t[1].getStoredPokemon()[4] = new Pokemon("Scyther", "Bug", "Flying", 70);
		t[1].getStoredPokemon()[5] = new Pokemon("Golem", "Rock", "Ground", 80);

		t[0].getStoredPokemon()[0].a[0] = new Ataque("Horn Attack", "Normal", 25);
		t[0].getStoredPokemon()[0].a[1] = new Ataque("Tackle", "Normal", 35);

		t[0].getStoredPokemon()[1].a[0] = new Ataque("Thunderbolt", "Electric", 15);

		t[0].getStoredPokemon()[2].a[0] = new Ataque("Sludge Bomb", "Poison", 10);

		t[0].getStoredPokemon()[3].a[0] = new Ataque("Razor Leaf", "Grass", 25);
		t[0].getStoredPokemon()[3].a[1] = new Ataque("Solar Beam", "Grass", 10);

		t[0].getStoredPokemon()[4].a[0] = new Ataque("Body Slam", "Normal", 15);
		t[0].getStoredPokemon()[4].a[1] = new Ataque("Hyper Beam", "Normal", 5);

		t[0].getStoredPokemon()[5].a[0] = new Ataque("Flamethrower", "Fire", 15);
		t[0].getStoredPokemon()[5].a[1] = new Ataque("Seismic Toss", "Fighting", 25);

		t[1].getStoredPokemon()[0].a[0] = new Ataque("Double Kick", "Fighting", 30);
		t[1].getStoredPokemon()[0].a[1] = new Ataque("Hyper Beam", "Normal", 5);

		t[1].getStoredPokemon()[1].a[0] = new Ataque("Flamethrower", "Fire", 15);
		t[1].getStoredPokemon()[1].a[1] = new Ataque("Fire Blast", "Fire", 5);

		t[1].getStoredPokemon()[2].a[0] = new Ataque("Rapid Spin", "Normal", 40);
		t[1].getStoredPokemon()[2].a[1] = new Ataque("Hydro Pump", "Water", 5);

		t[1].getStoredPokemon()[3].a[0] = new Ataque("Take Down", "Normal", 20);
		t[1].getStoredPokemon()[3].a[1] = new Ataque("Ember", "Fire", 25);

		t[1].getStoredPokemon()[4].a[0] = new Ataque("Quick Attack", "Normal", 30);
		t[1].getStoredPokemon()[4].a[1] = new Ataque("Wing Attack", "Flying", 35);

		t[1].getStoredPokemon()[5].a[0] = new Ataque("Rock Throw", "Rock", 15);
		t[1].getStoredPokemon()[5].a[1] = new Ataque("Earthquake", "Ground", 10);

		t[0].setActivePokemon(t[0].getStoredPokemon()[0]);
		t[1].setActivePokemon(t[1].getStoredPokemon()[0]);

		String line1 = ("Pokemon Trainer " + t[0].getName() + " wants to battle!" + "\n");
		String line2 = (t[0].getName() + " sent out " + t[0].getActivePokemon().getName() + "!\n");
		System.out.println(line1 + line2 + "Go! " + t[x].getActivePokemon().getName() + "!");
	}

	// Finaliza a batalha e mostra o vencedor
	private class EndBattle extends Event {

		public EndBattle(long eventTime) {
			super(eventTime);
			priority = 1;
		}

		public void action() {
			battle = false;
			clearEvents();

		}

		public String description() {
			if (t[x].getAlivePokemon() == 0)
				return t[x].getName() + " is out of usable pokemon\n" + t[y].getName() + " won the battle!";
			else if (t[y].getAlivePokemon() == 0)
				return t[y].getName() + " is out of usable pokemon\n" + t[x].getName() + " won the battle!";
			else if (!t[x].isFighting())
				return t[y].getName() + " won the battle!";
			else
				return t[x].getName() + " won the battle!";
		}
	}

	// Foge da Batalha
	private class Run extends Event {
		Trainer deserter;
		long eventTime;

		public Run(long eventTime, Trainer deserter) {
			super(eventTime);
			this.deserter = deserter;
			this.eventTime = eventTime;
			priority = 0.9;
		}

		public void action() {
			// se battle = false a batalha acabou

			deserter.setFighting(false);
			deserter.setTurnCompleted(true);
			addEvent(new EndBattle(eventTime));
		}

		public String description() {
			return deserter.getName() + " fled the battle!";
		}
	}

	private class Switch extends Event {
		private int pokemonNumber = 0;
		Trainer trainer;
		Pokemon oldPokemon;

		public Switch(long eventTime, Trainer trainer) {
			super(eventTime);
			priority = 0.8;
			oldPokemon = trainer.getActivePokemon();
			this.trainer = trainer;
			while (trainer.getStoredPokemon()[pokemonNumber].getHp() == 0) {
				double randomPokemon = Math.random();
				if (randomPokemon <= 0.167)
					pokemonNumber = 0;
				else if (randomPokemon <= 0.33)
					pokemonNumber = 1;
				else if (randomPokemon <= 0.5)
					pokemonNumber = 2;
				else if (randomPokemon <= 0.667)
					pokemonNumber = 3;
				else if (randomPokemon <= 0.83)
					pokemonNumber = 4;
				else
					pokemonNumber = 5;
			}

		}

		public Switch(long eventTime, Trainer trainer, int pokemonNumber) {
			super(eventTime);
			this.trainer = trainer;
			this.pokemonNumber = pokemonNumber;
			priority = 0.8;
			oldPokemon = trainer.getActivePokemon();
			// adicionar excecao para quando escolher um pokemon com hp = 0
		}

		public void action() {
			trainer.setActivePokemon(trainer.getStoredPokemon()[pokemonNumber]);
			trainer.setTurnCompleted(true);
		}

		public String description() {
			if (oldPokemon.getHp() == 0)
				return oldPokemon.getName() + " fainted..." + "\n" + oldPokemon.getName() + " comeback!" + "\nGo "
						+ trainer.getActivePokemon().getName() + "!";
			else
				return oldPokemon.getName() + " comeback!" + "\nGo " + trainer.getActivePokemon().getName() + "!";
		}
	}

	// Escolha de Itens
	private class UsePotion extends Event {
		Trainer user;

		public UsePotion(long eventTime, Trainer user) {
			super(eventTime);
			this.user = user;
			priority = 0.7;
		}

		public void action() {
			user.getActivePokemon().increaseHp(20);
			user.setPotions(user.getPotions() - 1);
			user.setTurnCompleted(true);
		}

		public String description() {
			return user.getName() + " used a Potion";
		}
	}

	// Escolha de Ataques
	private class Fight extends Event {
		double effect = 1;
		Trainer attacker, defender;
		long eventTime;

		public Fight(long eventTime, Trainer attacker) {
			super(eventTime);
			this.attacker = attacker;
			this.eventTime = eventTime;
			if (t[0].equals(attacker))
				defender = t[1];
			else
				defender = t[0];
			priority = 0.6;
		}

		double effective() {
			double effect = 1;

			if (attacker.getActivePokemon().a[w].type.equals(attacker.getActivePokemon().getType1())
					|| attacker.getActivePokemon().a[w].type.equals(attacker.getActivePokemon().getType2()))
				effect *= 1.5;

			if (attacker.getActivePokemon().a[w].type.equals("Normal")) {
				if (defender.getActivePokemon().getType1().equals("Rock")
						|| defender.getActivePokemon().getType2().equals("Rock"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Ghost")
						|| defender.getActivePokemon().getType2().equals("Ghost"))
					effect *= 0.0;
				return effect;
			}

			if (attacker.getActivePokemon().a[w].type.equals("Fighting")) {
				if (defender.getActivePokemon().getType1().equals("Normal")
						|| defender.getActivePokemon().getType2().equals("Normal"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Rock")
						|| defender.getActivePokemon().getType2().equals("Rock"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Ice")
						|| defender.getActivePokemon().getType2().equals("Ice"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Flying")
						|| defender.getActivePokemon().getType2().equals("Flying"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Poison")
						|| defender.getActivePokemon().getType2().equals("Poison"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Bug")
						|| defender.getActivePokemon().getType2().equals("Bug"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Psychic")
						|| defender.getActivePokemon().getType2().equals("Psychic"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Ghost")
						|| defender.getActivePokemon().getType2().equals("Ghost"))
					effect *= 0.0;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Flying")) {
				if (defender.getActivePokemon().getType1().equals("Fighting")
						|| defender.getActivePokemon().getType2().equals("Fighting"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Bug")
						|| defender.getActivePokemon().getType2().equals("Bug"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Grass")
						|| defender.getActivePokemon().getType2().equals("Grass"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Rock")
						|| defender.getActivePokemon().getType2().equals("Rock"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Electric")
						|| defender.getActivePokemon().getType2().equals("Electric"))
					effect *= 0.5;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Poison")) {
				if (defender.getActivePokemon().getType1().equals("Grass")
						|| defender.getActivePokemon().getType2().equals("Grass"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Poison")
						|| defender.getActivePokemon().getType2().equals("Poison"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Ground")
						|| defender.getActivePokemon().getType2().equals("Ground"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Rock")
						|| defender.getActivePokemon().getType2().equals("Rock"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Ghost")
						|| defender.getActivePokemon().getType2().equals("Ghost"))
					effect *= 0.5;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Ground")) {
				if (defender.getActivePokemon().getType1().equals("Poison")
						|| defender.getActivePokemon().getType2().equals("Poison"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Rock")
						|| defender.getActivePokemon().getType2().equals("Rock"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Fire")
						|| defender.getActivePokemon().getType2().equals("Fire"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Electric")
						|| defender.getActivePokemon().getType2().equals("Electric"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Grass")
						|| defender.getActivePokemon().getType2().equals("Grass"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Bug")
						|| defender.getActivePokemon().getType2().equals("Bug"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Flying")
						|| defender.getActivePokemon().getType2().equals("Flying"))
					effect *= 0.0;
				return effect;
			}

			if (attacker.getActivePokemon().a[w].type.equals("Rock")) {
				if (defender.getActivePokemon().getType1().equals("Flying")
						|| defender.getActivePokemon().getType2().equals("Flying"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Bug")
						|| defender.getActivePokemon().getType2().equals("Bug"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Fire")
						|| defender.getActivePokemon().getType2().equals("Fire"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Ice")
						|| defender.getActivePokemon().getType2().equals("Ice"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Ground")
						|| defender.getActivePokemon().getType2().equals("Ground"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Fighting")
						|| defender.getActivePokemon().getType2().equals("Fighting"))
					effect *= 0.5;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Bug")) {
				if (defender.getActivePokemon().getType1().equals("Grass")
						|| defender.getActivePokemon().getType2().equals("Grass"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Psychic")
						|| defender.getActivePokemon().getType2().equals("Psychic"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Flying")
						|| defender.getActivePokemon().getType2().equals("Flying"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Poison")
						|| defender.getActivePokemon().getType2().equals("Poison"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Fire")
						|| defender.getActivePokemon().getType2().equals("Fire"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Fighting")
						|| defender.getActivePokemon().getType2().equals("Fighting"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Ghost")
						|| defender.getActivePokemon().getType2().equals("Ghost"))
					effect *= 0.5;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Ghost")) {
				if (defender.getActivePokemon().getType1().equals("Ghost")
						|| defender.getActivePokemon().getType2().equals("Ghost"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Psychic")
						|| defender.getActivePokemon().getType2().equals("Psychic"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Normal")
						|| defender.getActivePokemon().getType2().equals("Normal"))
					effect *= 0.0;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Fire")) {
				if (defender.getActivePokemon().getType1().equals("Bug")
						|| defender.getActivePokemon().getType2().equals("Bug"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Grass")
						|| defender.getActivePokemon().getType2().equals("Grass"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Ice")
						|| defender.getActivePokemon().getType2().equals("Ice"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Rock")
						|| defender.getActivePokemon().getType2().equals("Rock"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Fire")
						|| defender.getActivePokemon().getType2().equals("Fire"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Water")
						|| defender.getActivePokemon().getType2().equals("Water"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Dragon")
						|| defender.getActivePokemon().getType2().equals("Dragon"))
					effect *= 0.5;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Water")) {
				if (defender.getActivePokemon().getType1().equals("Ground")
						|| defender.getActivePokemon().getType2().equals("Ground"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Rock")
						|| defender.getActivePokemon().getType2().equals("Rock"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Fire")
						|| defender.getActivePokemon().getType2().equals("Fire"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Water")
						|| defender.getActivePokemon().getType2().equals("Water"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Grass")
						|| defender.getActivePokemon().getType2().equals("Grass"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Dragon")
						|| defender.getActivePokemon().getType2().equals("Dragon"))
					effect *= 0.5;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Grass")) {
				if (defender.getActivePokemon().getType1().equals("Ground")
						|| defender.getActivePokemon().getType2().equals("Ground"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Rock")
						|| defender.getActivePokemon().getType2().equals("Rock"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Water")
						|| defender.getActivePokemon().getType2().equals("Water"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Flying")
						|| defender.getActivePokemon().getType2().equals("Flying"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Poison")
						|| defender.getActivePokemon().getType2().equals("Poison"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Bug")
						|| defender.getActivePokemon().getType2().equals("Bug"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Fire")
						|| defender.getActivePokemon().getType2().equals("Fire"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Grass")
						|| defender.getActivePokemon().getType2().equals("Grass"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Dragon")
						|| defender.getActivePokemon().getType2().equals("Dragon"))
					effect *= 0.5;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Electric")) {
				if (defender.getActivePokemon().getType1().equals("Flying")
						|| defender.getActivePokemon().getType2().equals("Flying"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Water")
						|| defender.getActivePokemon().getType2().equals("Water"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Electric")
						|| defender.getActivePokemon().getType2().equals("Electric"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Grass")
						|| defender.getActivePokemon().getType2().equals("Grass"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Dragon")
						|| defender.getActivePokemon().getType2().equals("Dragon"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Ground")
						|| defender.getActivePokemon().getType2().equals("Ground"))
					effect *= 0.0;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Psychic")) {
				if (defender.getActivePokemon().getType1().equals("Fighting")
						|| defender.getActivePokemon().getType2().equals("Fighting"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Poison")
						|| defender.getActivePokemon().getType2().equals("Poison"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Psychic")
						|| defender.getActivePokemon().getType2().equals("Psychic"))
					effect *= 0.5;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Ice")) {
				if (defender.getActivePokemon().getType1().equals("Flying")
						|| defender.getActivePokemon().getType2().equals("Flying"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Ground")
						|| defender.getActivePokemon().getType2().equals("Ground"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Grass")
						|| defender.getActivePokemon().getType2().equals("Grass"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Dragon")
						|| defender.getActivePokemon().getType2().equals("Dragon"))
					effect *= 2.0;
				if (defender.getActivePokemon().getType1().equals("Fire")
						|| defender.getActivePokemon().getType2().equals("Fire"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Water")
						|| defender.getActivePokemon().getType2().equals("Water"))
					effect *= 0.5;
				if (defender.getActivePokemon().getType1().equals("Ice")
						|| defender.getActivePokemon().getType2().equals("Ice"))
					effect *= 0.5;
				return effect;
			}
			if (attacker.getActivePokemon().a[w].type.equals("Dragon")) {
				if (defender.getActivePokemon().getType1().equals("Dragon")
						|| defender.getActivePokemon().getType2().equals("Dragon"))
					effect *= 2.0;
				return effect;
			}
			return 1;
		}

		public void action() {
			if (attacker.getActivePokemon().getHp() == 0) {
				es.removeCurrent();
				return;
			}
			effect = effective();
			defender.getActivePokemon().decreaseHp((int) effect * attacker.getActivePokemon().a[w].power);
			if (defender.getActivePokemon().getHp() == 0) {
				if (defender.getAlivePokemon() > 0) {
					defender.setAlivePokemon(defender.getAlivePokemon() - 1);
					addEvent(new Switch(eventTime, defender));
				} else
					battle = false;

			}
			attacker.setTurnCompleted(true);
		}

		public String description() {
			if (effect > 1.5)
				return attacker.getActivePokemon().getName() + " used " + attacker.getActivePokemon().a[w].getName()
						+ "\nIt's super effective!";
			if (effect < 1 && effect != 0)
				return attacker.getActivePokemon().getName() + " used " + attacker.getActivePokemon().a[w].getName()
						+ "\nit's not very effective...";
			if (effect == 0)
				return attacker.getActivePokemon().getName() + " used " + attacker.getActivePokemon().a[w].getName()
						+ "\nIt doesn't effect " + defender.getActivePokemon().getName() + " at all...";
			else
				return attacker.getActivePokemon().getName() + " used " + attacker.getActivePokemon().a[w].getName();
		}
	}

	// Termina o Round e Mostra o estado dos Pokemons
	private class EndRound extends Event {
		long eventTime;

		public EndRound(long eventTime) {
			super(eventTime);
			this.eventTime = eventTime;
			priority = 0.5;
		}

		public void action() {
			if (battle == false)
				addEvent(new EndBattle(eventTime));
		}

		public String description() {
			return t[x].getActivePokemon().getName() + " HP:" + t[x].getActivePokemon().getHp() + "/"
					+ t[x].getActivePokemon().getMaxHp() + "  vs  " + t[y].getActivePokemon().getName() + " HP:"
					+ t[y].getActivePokemon().getHp() + "/" + t[y].getActivePokemon().getMaxHp();
		}
	}

	// Criar um para Rounds
	private class NewRound extends Event {
		public NewRound(long eventTime) {
			super(eventTime);
		}

		public void action() {
			long tm = System.currentTimeMillis();
			// Instead of hard-wiring, you could parse
			// configuration information from a text
			// file here:
			for (int i = 0; i < 2; i++) {
				double n = Math.random();
				if (n < 0.25)
					addEvent(new Fight(tm + 1000 + 4000 * i, t[i]));
				else if (n < 0.5)
					addEvent(new Switch(tm + 1000 + 4000 * i, t[i]));
				else if (n < 0.75)
					addEvent(new Run(tm + 1000 + 4000 * i, t[i]));
				else
					addEvent(new UsePotion(tm + 1000 + 4000 * i, t[i]));
			}
			addEvent(new EndRound(tm + 8000));

		}

		public String description() {
			return " ";
		}
	}

	public static void main(String[] args) {
		BattleControls bc = new BattleControls();
		long tm = System.currentTimeMillis();
		bc.StartBattle();
		while (bc.battle == true) {
			bc.addEvent(bc.new NewRound(tm));
			bc.run();
		}
	}
}
