package Ex1;


public class Pokemon {
 
	final private int HPMAX;
	private int hp;
	private String name;
	private String type1;
	private String type2;
	Ataque[] a = new Ataque[4];
	
	//Construtor de Pokemon
	Pokemon (String nome, String tipo1, String tipo2, int vida){
		setName(nome.toUpperCase());
		setType1(tipo1);
		setType2(tipo2);
		HPMAX = vida;
		hp = HPMAX;
	}
	
	Pokemon (String nome, String tipo1, int vida){
		setName(nome.toUpperCase());
		setType1(tipo1);
		setType2(" ");
		HPMAX = vida;
		hp = HPMAX;
	}
	
	String getName(){
		return name;
	}
	
	int getHp(){
		return hp;
	}
	void setHp (int value){
		hp = value;
	}
	
	void decreaseHp (int value){
		hp -= value;
		if (hp < 0)
			hp = 0;
	}
	void increaseHp (int value){
		hp += value;
		if (hp > HPMAX)
			hp = HPMAX;
	}
	
	int getMaxHp(){
		return HPMAX;
	}

	void setName(String name) {
		this.name = name;
	}

	String getType1() {
		return type1;
	}

	void setType1(String type1) {
		this.type1 = type1;
	}

	String getType2() {
		return type2;
	}

	void setType2(String type2) {
		this.type2 = type2;
	}
}