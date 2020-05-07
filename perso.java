import java.util.Scanner;

public class perso{

	public String nom;
	public int pv; 					//points de vie
	public int type; 				//retrouver chaque perso plus facilement
	public int posx; 				// la position du joueur sur x 
	public int posy;				// la position du joueur sur y 
	public Attaque A1;  			// premiere attaque
	public Attaque A2;				//deuxieme attaque
	
	//constructeur
	public perso(int a, int j){		//a : personnage choisi, j = joueur (1 ou 2)
		
		this.A1 = new Attaque(a,true); //attaque a distance
		this.A2 = new Attaque(a, false); //attaque au corps a corps
		
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
		return this.nom+", vous avez "+this.pv+" points de vie \n"+"Vos attaques sont : "+this.A1.toString()+" et "+this.A2.toString();
		}

	//description point de vie
	public String toStringPV(int j){
		return "PV joueur "+j+" : "+this.pv;
	}
	
	//Déplacements 
	public void move(perso A){
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
		boolean P = this.possible(A, mouv);
		
		while(P == false){
			System.out.println("Vous ne pouvez pas vous déplacer ici !");
			mouv = sc.nextLine();
			P = this.possible(A, mouv);
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
	
	//le mouvement est-il valide ?
	public boolean possible(perso A, String mouv){
			if( (!mouv.equals("z") && !mouv.equals("q") && !mouv.equals("d") && !mouv.equals("s")) || (this.posy == 0 && mouv.equals("z")) || (this.posy == 8 && mouv.equals("s")) || (this.posx == 0 && mouv.equals("q")) || (this.posx == 0 && mouv.equals("d")) || (this.posy == A.posy && this.posx == (A.posx - 1) && mouv.equals("d")) || (this.posy == A.posy && this.posx == (A.posx + 1) && mouv.equals("q")) ){
				return false;
			}else{
				if((this.posx == A.posx && this.posy == (A.posy+1) && mouv.equals("z")) || (this.posx == A.posx && this.posy == (A.posy - 1) && mouv.equals("s"))){
			return false;
		}else{
			return true;
	}
}
}
	
	//Distance
	public int distance(perso B){
		return Math.abs(this.posx - B.posx) + Math.abs(this.posy - B.posy);
	}
	
	//perte de point de vie
	public void pv(int a){
		this.pv = this.pv - a;
	}	
	 
	//attaque
	public void attaque(perso B){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Quelle attaque voulez vous utiliser ?");
		System.out.println(this.A1.toString()+" tapez 1");
		System.out.println(this.A2.toString()+" tapez 2");
		int at = sc.nextInt();
		int d = this.distance(B);
		switch (at){
			case 1 : 
			if(d > 3){
				B.pv(this.A1.Adist);
			}else{
				B.pv(this.A1.Acac);
			}
			break;
				case 2 : 
				if(d > 3){
				B.pv(this.A2.Adist);
			}else{
				B.pv(this.A2.Acac);
			}
		}
	}
}
