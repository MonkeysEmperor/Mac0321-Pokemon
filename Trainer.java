package Ex1;


public class Trainer {

	int potions;
	int pokeballs;
	int pokemon = 6;
	String name;
	Pokemon activePokemon;
	Pokemon[] p = new Pokemon[6];//Vetor de Pokemons do treinador usados para batalha
	Pokemon[] bill = new Pokemon[20];//Vetor de Pokemons do treinador que foram capturados
	
	//Contrutores de Treinador
	Trainer(String nome, int potions, int pokebolas, int activePokemon){
		this(nome, potions, pokebolas);
		this.activePokemon = p[activePokemon];
	}
	Trainer(String name, int potions, int pokeballs){
		this.name = name;
		this.potions = potions;
		this.pokeballs = pokeballs;
		activePokemon = p[0];
	}	
	Trainer(String name){
		this.name = name;
		potions = 0;
		pokeballs = 0;
		activePokemon = p[0];
	}
	
	void setName(String nome){
		name = nome;
	}
	
	String getName(){
		return name;
	}
	
	void setPotions(int numero){
		potions = numero;
	}
	
	int getPotions(){
		return potions;
	}
	
	void setPokeballs(int numero){
		pokeballs = numero;
	}
	
	int getPokeballs(){
		return pokeballs;
	}

	void setactivePokemon(Pokemon poke){
		activePokemon = poke;
	}

}