import java.util.Scanner;

public class Perso{

private String nom;
public int pv; //points de vie
private int type; //retrouver chaque perso plus facilement
public int posx; // la position du joueur pos[0] = sur x et pos[1] = sur y
public int posy;

//constructeur
public Perso(int a, int j){ //a : personnage choisi, j = joueur (1 ou 2)
	this.posx = 4;
	
	if(j == 1){
				posy = 0; // centre haut du jeu
			}else{
				posy = 8;
			}
	
	switch(a){
		case 1 :
		this.nom = "Archer";
		this.pv = 150;
		this.type = 1;
		
		break;
		
		case 2 :
		this.nom = "Barbare";
		this.pv = 250;
		this.type = 2;
		break;
		
		case 3 :
		this.nom = "Canonier";
		this.pv = 100;
		this.type = 3;
		break;
		
		case 4 :
		this.nom = "Diable";
		this.pv = 200;
		this.type = 4;
		break;
	}
}

//deplacement
public void deplacement(){
	Scanner sc = new Scanner(System.in);
	
	System.out.println("Ou voulez-vous aller ?");
	System.out.println("Z pour monter");
	System.out.println("Q pour aller à gauche");
	System.out.println("S pour descendre");
	System.out.println("D pour aller à droite");
	String mouv = sc.nextLine();
	if(mouv == "Z"){
		this.posy = this.posy - 1;
	}
	if(mouv == "Q"){
		this.posx = this.posx -1;
	}
	if(mouv == "S"){
		this.posy = this.posy +1;
	}
	if(mouv == "D"){
		this.posx = this.posx+1;
	}
}
}
