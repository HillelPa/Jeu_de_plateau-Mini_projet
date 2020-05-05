import java.util.Scanner;

public class Perso{

	private String nom;
	public int pv; 					//points de vie
	private int type; 				//retrouver chaque perso plus facilement
	public int posx; 				// la position du joueur pos[0] = sur x et pos[1] = sur y
	public int posy;

	//constructeur
	public Perso(int a, int j){		//a : personnage choisi, j = joueur (1 ou 2)
		this.posx = 4;
		
		if(j == 1){
					posy = 0; 		// centre haut du jeu
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
	//Getteurs
	public String getNom(){
        return  this.nom;
	}
	public int getPv() {
		return this.pv;
	}
	// Déplacements
	public void move(personnage J){ // personnage est un type et J est alors le joueur actuellement en train de jouer son tour
        Scanner sc = new Scanner(System.in);
        
        if (posx.J<2){
            if (posy.J<2){
                System.out.println("Ou voulez-vous aller ?");
                System.out.println("D pour aller à droite");
                System.out.println("C pour aller en bas à droite");
                System.out.println("X pour descendre");
                System.out.println("S pour ne pas bouger");
            }if (pos.J>7){
                System.out.println("Ou voulez-vous aller ?");
                System.out.println("D pour aller à droite");
                System.out.println("E pour aller en haut à droite");
                System.out.println("Z pour monter");
                System.out.println("S pour ne pas bouger");
            }else{
                System.out.println("Ou voulez-vous aller ?");
                System.out.println("D pour aller à droite");
                System.out.println("C pour aller en bas à droite");
                System.out.println("X pour descendre");
                System.out.println("E pour aller en haut à droite");
                System.out.println("Z pour monter");
                System.out.println("S pour ne pas bouger");
            }
        }if (posx.J>7){
            if (posy.J<2){
                System.out.println("Ou voulez-vous aller ?");
                System.out.println("Q pour aller à gauche");
                System.out.println("W pour aller en bas à gauche");
                System.out.println("X pour descendre");
                System.out.println("S pour ne pas bouger");
            }if (posy.J>7){
                System.out.println("Ou voulez-vous aller ?");
                System.out.println("Q pour aller à gauche");
                System.out.println("A pour aller en haut à gauchez");
                System.out.println("Z pour monter");
                System.out.println("S pour ne pas bouger");
            }else{
                System.out.println("Ou voulez-vous aller ?");
                System.out.println("Z pour aller en haut");
                System.out.println("A pour aller en haut à gauche");
                System.out.println("Q pour aller à gauche");
                System.out.println("W pour aller en bas à gauche");
                System.out.println("X pour descendre");
        }else{
            if (posy.J<2){
                System.out.println("Ou voulez-vous aller ?");
                System.out.println("Q pour aller à gauche");
                System.out.println("W pour aller en bas à gauche");
                System.out.println("X pour descendre");
                System.out.println("C pour aller en bas à droite");
                System.out.println("D pour aller à droite");
                System.out.println("S pour ne pas bouger");
            }
            if (posy.J>7e){
                System.out.println("Ou voulez-vous aller ?");
                System.out.println("Q pour aller à gauche");
                System.out.println("A pour aller en haut à gauche");
                System.out.println("Z pour monter");
                System.out.println("E pour aller en haut à droite");
                System.out.println("D pour aller à droite");
                System.out.println("S pour ne pas bouger");
            }else{
                System.out.println("A pour aller en haut à gauche");
                System.out.println("Z pour monter");
                System.out.println("E pour aller en haut à droite");
                System.out.println("D pour aller à droite");
                System.out.println("C pour aller en bas à droite");
                System.out.println("X pour descendre");
                System.out.println("W pour aller en bas à gauche");
                System.out.println("Q pour aller à gauche");
                System.out.println("S pour ne pas bouger");
                

        String mouv = T;//initialisation différente du pavé de déplacement
        String mouv = sc.nextLine();
        While ((mouv!='A')||(mouv!='Z')||(mouv!='E')||(mouv!='D')||(mouv!='C')||(mouv!='X')||(mouv!='W')||(mouv!='Q')||(mouv!='S')){
            System.out.println("Ce déplacement n'est pas valide ; chosissez en un autre :");
            String mouv = sc.nextLine(); // ici ce ne serait pas plutot J.posy ou J.posx???
        }
        if(mouv == "A"){
            this.posy = this.posy-1;
            this.posx = this.posx-1;
        }
        if(mouv == "Z"){
            this.posy = this.posy-1;
        }
        if(mouv == "E"){
            this.posy = this.posy-1;
            this.posx = this.posx+1;
        }
        if(mouv == "D"){
            this.posx = this.posx+1;
        }
        if(mouv == "C"){
            this.posy = this.posy+1;
            this.posx = this.posx+1;
        }
        if(mouv == "X"){
            this.posx = this.posy+1;
        }
        if(mouv == "W"){
            this.posy = this.posy+1;
            this.posx = this.posx-1;
        }
        if(mouv == "Q"){
            this.posx = this.posx-1;
        }
}