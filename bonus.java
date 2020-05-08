public class bonus{

public String name;
public String abrev;
public int posx;
public int posy;
public int bonusAttaque;
public int bonusVie;

public bonus(int a){ //a : type de bonus 
	switch(a){
		case 1:
		this.name = "Potion de vie";
		this.abrev = "♥︎️";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 0;
		this.bonusVie = 30;
	break;
		case 2:
		this.name = "Bonus 2";
		this.abrev = "b";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 1;
		this.bonusVie = 1;
	break;
		case 3:
		this.name = "Bonus 3";
		this.abrev = "c";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 1;
		this.bonusVie = 1;
	break;
		case 4:
		this.name = "Bonus 4";
		this.abrev = "d";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 1;
		this.bonusVie = 1;
	break;
	}
}

public bonus(){ //constructeur pour bonus qui sert a rien
	this.name = " ";
		this.abrev = " ";
		this.posx = 4;
		this.posy = 1;
		this.bonusAttaque = 0;
		this.bonusVie = 0;
}

public String toString(){
	return "bonus : "+this.name+" ce bonus vous accorde "+this.bonusAttaque+" de points d'attaque en plus et "+this.bonusVie+" points de vie";
}

}
