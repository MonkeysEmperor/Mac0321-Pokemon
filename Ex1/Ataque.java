package Ex1;


public class Ataque {

	String name;
	String type;
	int power;
	int pp;
	double accuracy;
	
	Ataque(String nome, String tipo, int pp, int poder, double precisao){
		name = nome;
		type = tipo;
		this.pp = pp;
		power = poder;
		accuracy = precisao;
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
