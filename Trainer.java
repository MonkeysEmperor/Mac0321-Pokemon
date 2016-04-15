package c07.controller;

public class Trainer {

	String name;
	Pokemon[] p = new Pokemon[6];
	int potions;
	int pokeball;
	
	Trainer(String nome, int poções, int pokebolas, Pokemon[] pokemons){
		name = nome;
		potions = poções;
		pokeball = pokebolas;
		p = pokemons;
	}
	
	Trainer(String nome, Pokemon[] pokemons){
		name = nome;
		potions = 0;
		pokeball = 0;
		p = pokemons;
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
	
	void setPokeball(int numero){
		pokeball= numero;
	}
	
	int getPokeball(){
		return pokeball;
	}
}
