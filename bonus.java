public class bonus{

public String name;						//nom du bonus
public String abrev;					//abreviation qui sera affichée sur le plateau
public String description;				//description des pv que le bonus donne ou des points d'attaques en plus
public int posx;						//position sur l'axe des x
public int posy;						//position sur l'axe des y
public int bonusAttaque;				//points d'attaque en plus grace au bonus
public int bonusVie;					//points de vie en plus grace au bonus


/** CONSTRUCTEUR **/ 

public bonus(int a){ 					//a : type de bonus 
	switch(a){
		case 1:
		this.name = "Potion de vie";
		this.abrev = "♥︎️";
		this.description = " Cette potion régénère 40 de vos points de vie";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 0;
		this.bonusVie = 40;
	break;
		case 2:
		this.name = "Potion de force";
		this.abrev = "f";
		this.description = " Cette potion augmente les dégats de vos attaques de 15 points";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 15;
		this.bonusVie = 0;
	break;
		case 3:
		this.name = "Potion de Super Force";
		this.abrev = "F";
		this.description = " Cette potion augmente les dégats de vos attaques de 30 points";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 30;
		this.bonusVie = 0;
	break;
		case 4:
		this.name = "Sursaut de vie";
		this.abrev = "️H";
		this.description = " Cette potion régénère 20 de vos points de vie";
		this.posx = (int)(Math.random()*9);
		this.posy = (int)(Math.random()*9);
		this.bonusAttaque = 0;
		this.bonusVie = 20;
	break;
	}
	
}

//Bonus qui n'a pas d'effet, permet de remplacer un bonus qui est pris

public bonus(){ 	
	this.name = " ";
		this.abrev = " ";
		this.posx = 4;
		this.posy = 1;
		this.bonusAttaque = 0;
		this.bonusVie = 0;
}

/** DESCRIPTION **/

public String toString(){
	return "bonus : "+this.name+this.description;
}

}
