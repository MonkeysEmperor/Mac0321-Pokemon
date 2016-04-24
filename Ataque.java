package c07.controller;

public class Ataque {

	String name;
	String type;
	int power;
	int pp;
	double accuracy;
	
	Ataque(String nome, String tipo, int PP, int poder, double precisão ){
		name = nome;
		type = tipo;
		pp = PP;
		power = poder;
		accuracy = precisão;
	}
	
	Ataque(String nome, String tipo, int poder){
		name = nome;
		type = tipo;
		pp = 0;
		power = poder;
		accuracy = 0;
	}
	
	
	void setName(String nome){
		name = nome;
	}
	
	String getName(){
		return name;
	}
	
}
