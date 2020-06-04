public class bonus{

public String name;
public String abrev;
public String description;
public int posx;
public int posy;
public int bonusAttaque;
public int bonusVie;

public bonus(int a){ //a : type de bonus 
	switch(a){
		case 1:
		this.name = "Potion de vie";
		this.abrev = "♥︎️";
		this.description = " Cette potion regenere 40 de vos points de vie";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 0;
		this.bonusVie = 40;
	break;
		case 2:
		this.name = "Potion de force";
		this.abrev = "f";
		this.description = " Cette potion augmente vos ataques de 15 points";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 15;
		this.bonusVie = 0;
	break;
		case 3:
		this.name = "Potion de Super Force";
		this.abrev = "F";
		this.description = " Cette potion augmente vos attaques de 30 points";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 30;
		this.bonusVie = 0;
	break;
		case 4:
		this.name = "Sursaut de vie";
		this.abrev = "️H";
		this.description = " Cette potion regenere 20 de vos points de vie";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 0;
		this.bonusVie = 20;
	break;
	}
	
}

//Bonus qui n'a pas d'effet, permet de remplacer un bonus qui est prit
public bonus(){ 	
	this.name = " ";
		this.abrev = " ";
		this.posx = 4;
		this.posy = 1;
		this.bonusAttaque = 0;
		this.bonusVie = 0;
}

public String toString(){
	return "bonus : "+this.name+this.description;
}

}
