import java.util.Scanner;

public class perso{

	public String nom;
	public int pv; 					// points de vie
	public int type; 				// retrouver chaque personnage plus facilement
	public int posx; 				// la position du joueur sur x 
	public int posy;				// la position du joueur sur y 
	public Attaque A1;  			// première attaque (celle a utiliser à distance)
	public Attaque A2;				// deuxième attaque	(celle a utiliser au corps à corps)
	
	/** CONSTRUCTEURS **/
	
	public perso(int a, int j){		//a : personnage choisi, j = joueur (1 ou 2)
		
		this.A1 = new Attaque(a,true); 			//attaque à distance
		this.A2 = new Attaque(a, false); 		//attaque au corps à corps
		
		this.posx = 4;
		
		if(j == 1){
				posy = 0; 						// centre haut du jeu
			}else{
				posy = 8;						// centre bas du jeu
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
			this.nom = "Canonnier";
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
	
	/** DESCRIPTIONS **/
	    
	//description
	public String toString(){
		return "_____________________________________________________________________\n"
				+"["+this.nom+"]\n"
				+"❤ : "+this.pv+" pv\n"
				+"\n"
				+" Deux attaques : \n"
				+"---\n"
				+"Attaque 1 : " +this.A1.nom+"\n"
				+"Dégâts à distance : "+this.A1.Adist+"\n"
				+"Dégâts au corps à corps : "+this.A1.Acac+"\n"
				+"\n"
				+"Attaque 2 : " +this.A2.nom+"\n"
				+"Dégâts à distance : "+this.A2.Adist+"\n"
				+"Dégâts au corps à corps : "+this.A2.Acac+"\n"
				+"_____________________________________________________________________\n";
		}

	//description point de vie
	public String toStringPV(int j){
		return "PV joueur "+j+" : "+this.pv;
	}
	
	/** METHODES POUR LE DEPLACEMENT : **/
	
	//Déplacements 
	public void move(perso A){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Où voulez-vous aller ?");
		System.out.println();
		
			if (posx==0){
				if (posy==0){
					System.out.println("      → d");
					System.out.println("    ↓    ");
					System.out.println("    s    ");
				}else if(posy==8){
					System.out.println("    z    ");
					System.out.println("    ↑    ");
					System.out.println("      → d");
				}else{
					System.out.println("    z    ");
					System.out.println("    ↑    ");
					System.out.println("      → d");
					System.out.println("    ↓    ");
					System.out.println("    s    ");
				}
			}else if(posx==8){
				if (posy==0){
					System.out.println("q ←      ");
					System.out.println("    ↓    ");
					System.out.println("    s    ");
				}else if(posy==8){
					System.out.println("    z    ");
					System.out.println("    ↑    ");
					System.out.println("q ←      ");
				}else{
					System.out.println("    z    ");
					System.out.println("    ↑    ");
					System.out.println("q ←      ");
					System.out.println("    ↓    ");
					System.out.println("    s    ");
				}
			}else{
				if (posy==0){
					System.out.println("q ←   → d");
					System.out.println("    ↓    ");
					System.out.println("    s    ");
				}else if(posy==8){
					System.out.println("    z    ");
					System.out.println("    ↑    ");
					System.out.println("q ←   → d");
				}else{
					System.out.println("    z    ");
					System.out.println("    ↑    ");
					System.out.println("q ←   → d");
					System.out.println("    ↓    ");
					System.out.println("    s    ");
				}
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
			if( (!mouv.equals("z") && !mouv.equals("q") && !mouv.equals("d") && !mouv.equals("s")) || (this.posy == 0 && mouv.equals("z")) || (this.posy == 8 && mouv.equals("s")) || (this.posx == 0 && mouv.equals("q")) || (this.posx == 8 && mouv.equals("d")) || (this.posy == A.posy && this.posx == (A.posx - 1) && mouv.equals("d")) || (this.posy == A.posy && this.posx == (A.posx + 1) && mouv.equals("q")) || ((this.posx == A.posx && this.posy == (A.posy+1) && mouv.equals("z")) || (this.posx == A.posx && this.posy == (A.posy - 1) && mouv.equals("s"))) ){
				return false;
			}else{
				return true;
	}

}
	
	/** METHODE POUR L'ATTAQUE **/
	
	//Distance
	public int distance(perso B){
		return Math.abs(this.posx - B.posx) + Math.abs(this.posy - B.posy);
	}
	
	//perte de points de vie
	public void pv(int a){
		this.pv = this.pv - a;
	}	
	 
	//attaque
	public int attaque(perso B){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Quelle attaque voulez-vous utiliser ?");
		System.out.println(this.A1.toString()+" tapez 1");
		System.out.println(this.A2.toString()+" tapez 2");
		int at = jeu.estPossible(1,2);
		int d = this.distance(B);
		switch (at){
			case 1 : 
			if(d > 3){
				B.pv(this.A1.Adist);
					return this.A1.Adist;
			}else{
				B.pv(this.A1.Acac);
					return this.A1.Acac;
			}
			
			case 2 : 
			if(d > 3){
				B.pv(this.A2.Adist);
					return this.A2.Adist;
			}else{
				B.pv(this.A2.Acac);
					return this.A2.Acac;
			}
		}
	return 0;
	}
}
