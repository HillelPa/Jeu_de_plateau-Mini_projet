import java.util.Scanner;

public class perso{

	public String nom;
	public int pv; 					//points de vie
	public int type; 				//retrouver chaque perso plus facilement
	public int posx; 				// la position du joueur sur x 
	public int posy;				// la position du joueur sur y 

	//constructeur
	public perso(int a, int j){		//a : personnage choisi, j = joueur (1 ou 2)
		this.posx = 4;
		
		if(j == 1){
					posy = 0; 		// centre haut du jeu
				}else{
					posy = 8;		// centre bas du jeu
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
	
	/*   POUR L'INSTANT ON MET TOUT EN PUBLIC PAS BESOIN DE SET ni de GET
	//Getteurs
	public String getNom(){
        return  this.nom;
	}
	public int getPv() {
		return this.pv;
	}
	* */     
	
	//description
	public String toString(){
		return this.nom+", vous avez "+this.pv+" points de vie";
		}

	//Déplacements 
	public void move(){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Où voulez-vous aller ?");
		System.out.println();
			if(this.posy > 0){
			System.out.println("z pour monter");
			}
			if(this.posx > 0){
			System.out.println("q pour aller à gauche");
			}	
			if(this.posy<8){
			System.out.println("s pour descendre");
			}
			if(this.posx<8){
			System.out.println("d pour aller à droite");
			}
		
		String mouv = sc.nextLine();

        while(!mouv.equals("z") && mouv.equals("q") && mouv.equals("s") && mouv.equals("d") ){
            System.out.println("Ce déplacement n'est pas valide ; chosissez en un autre :");
            mouv = sc.nextLine(); // ici ce ne serait pas plutot J.posy ou J.posx???
        }
        if(mouv.equals("z")){
            this.posy = this.posy-1;
        }
        if(mouv.equals("d")){
            this.posx = this.posx+1;
        }
        if(mouv.equals("s")){
            this.posy = this.posy+1;
        }
        if(mouv.equals("q")){
            this.posx = this.posx-1;
        }
}
}
