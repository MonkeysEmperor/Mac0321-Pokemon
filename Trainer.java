package Ex1;


public class Trainer {

	private boolean fighting = true;
	private boolean turnCompleted = false;
	
	private int potions;
	private int pokeballs;
	private int alivePokemon = 6;
	private String name;
	private Pokemon activePokemon;
	private Pokemon[] pokemon = new Pokemon[6];
	private Pokemon[] bill = new Pokemon[20];
	
	public Trainer(String name, int potions, int pokeballs){
		this.setName("Pokemon Trainer" + name);
		this.setPotions(potions);
		this.setPokeballs(pokeballs);
	}
	
	public Trainer(String nome, int potions, int pokebolas, int activePokemon){
		this(nome, potions, pokebolas);
		this.setActivePokemon(getStoredPokemon()[activePokemon]);
	}
	
	public Trainer(String name){
		this.setName(name);
		setPotions(50);
		setPokeballs(10);
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

	int getAlivePokemon() {
		return alivePokemon;
	}

	void setAlivePokemon(int pokemon) {
		this.alivePokemon = pokemon;
	}

	Pokemon getActivePokemon() {
		return activePokemon;
	}

	void setActivePokemon(Pokemon activePokemon) {
		this.activePokemon = activePokemon;
	}

	Pokemon[] getStoredPokemon() {
		return pokemon;
	}

	boolean isFighting() {
		return fighting;
	}

	void setFighting(boolean fighting) {
		this.fighting = fighting;
	}
	
	public boolean isTurnCompleted() {
		return turnCompleted;
	}

	public void setTurnCompleted(boolean turnCompleted) {
		this.turnCompleted = turnCompleted;
	}

    void capturaPokemon(Pokemon pokemon){
    	for(int i = 0; i < 6; i++){
    		if(this.pokemon[i] == null){
    		   this.pokemon[i] = pokemon;
    		   return;
    		}
    	}
    	for(int i = 0; i < 20; i++){
    		if(this.bill[i] == null){
     		   this.bill[i] = pokemon;
     		   return;
     		}
    	}
    }
}