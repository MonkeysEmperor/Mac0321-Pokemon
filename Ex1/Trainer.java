package Ex1;


public class Trainer {

	int potions;
	int pokeballs;
	int pokemon = 6;
	String name;
	Pokemon activePokemon;
	Pokemon[] p = new Pokemon[6];
	
	public Trainer(String name, int potions, int pokeballs){
		this.name = name;
		this.potions = potions;
		this.pokeballs = pokeballs;
	}
	
	public Trainer(String nome, int potions, int pokebolas, int activePokemon){
		this(nome, potions, pokebolas);
		this.activePokemon = p[activePokemon];
	}
	
	public Trainer(String name){
		this.name = name;
		potions = 0;
		pokeballs = 0;
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
}