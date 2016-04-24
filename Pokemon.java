package c07.controller;

public class Pokemon {
 
	int HPMAX;
	int hp = HPMAX;
	String name;
	String type1;
	String type2;
	Ataque[] a = new Ataque[4];
	
	//Construtor de Pokemon
	Pokemon (String nome, String tipo1, String tipo2, int vida){
		name = nome;
		type1 = tipo1;
		type2 = tipo2;
		HPMAX = vida;
		hp = HPMAX;
	}
	
	Pokemon (String nome, String tipo1, int vida){
		name = nome;
		type1 = tipo1;
		type2 = " ";
		HPMAX = vida;
		hp = HPMAX;
	}
	
	void setName(String nome){
		name = nome;
	}
	
	String getName(){
		return name;
	}
	
	int gethp(){
		return hp;
	}
	
	int getHP(){
		return HPMAX;
	}
}