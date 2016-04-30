package Ex1;


public class Ataque {

	String name;
	String type;
	int power;
	int pp;
	double accuracy;
	
	//Contrutores de Ataques
	Ataque(String name, String type, int pp, int power, double accuracy){
		this.name = name;
		this.type = type;
		this.pp = pp;
		this.power = power;
		this.accuracy = accuracy;
	}
	Ataque(String name, String type, int power){
		this.name = name;
		this.type = type;
		this.power = power;
		pp = 0;
		accuracy = 0;
	}
	
	void setName(String nome){
		name = nome;
	}
	
	String getName(){
		return name;
	}
	
}
