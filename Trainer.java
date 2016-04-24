package c07.controller;

public class Trainer {

	int potions;
	int pokeball;
	int pokemon = 6;
	String name;
	Pokemon[] p = new Pokemon[6];
	
	Trainer(String nome, int poções, int pokebolas){
		name = nome;
		potions = poções;
		pokeball = pokebolas;
	}
	
	Trainer(String nome){
		name = nome;
		potions = 0;
		pokeball = 0;
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