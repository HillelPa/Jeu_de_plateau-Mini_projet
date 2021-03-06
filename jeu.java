import java.util.InputMismatchException;
import java.util.Scanner;

public class testjeu{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		afficherNom(); 								//affichage du nom du jeu
		timePause(2000);							//pause de 2 secondes
		pause();									//saut de ligne + "---"
		introduction(); 							//affichage des règles
		pause();

		perso[] Perso = new perso [2]; 				//Tableau qui stockera les persos des joueurs 1 et 2
		
		System.out.println("Joueur 1, c'est à vous de choisir un personnage parmis les champions suivant :");
		Perso[0] = choix(1);						//choix du personnage par le joueur
		pause();
		System.out.println("Vous avez choisi "+Perso[0].nom);	
		System.out.println();
		System.out.println("Vous êtes le [O]");
		pause();
		effaceEcran();
		
		System.out.println("Joueur 2, c'est à vous de choisir un personnage parmis les champions suivant :");
		Perso[1] = choix(2);
		pause();
		System.out.println("Vous avez choisi "+Perso[1].nom);	
		System.out.println();
		System.out.println("Vous êtes le [X]");
		pause();
		
		//affichage du jeu initial
		effaceEcran();
		System.out.println("Voici l'arène !");
		System.out.println();
		String [][] plat = init();					//Création de l'échiquier
		int nbrB = -1;								//entier qui donnera la place du dernier bonus dans le tableau de bonus
		bonus [] Bonus = new bonus [100];			//tableau de bonus qui stockera tous les bonus qui apparaissent 
			for(int i = 0; i<Bonus.length; i++){
				Bonus[i] = new bonus();				//le tableau est initialement rempli de bonus inactifs
			}
		nbrB++;
		Bonus[nbrB] = new bonus();
		maj(plat, Perso[0], Perso[1], Bonus, nbrB);	//une mise a jour qui prend en compte les nouveaux bonus et les joueurs
		affichage(plat);							//affichage de l'échiquier 
		timePause(3000);
		effaceEcran();
		
		//jeu :
		int j = 1; 								 
		boolean F = fini(Perso[0], Perso[1]); 		//jeu est-il fini ?	
		int t = 0; 									// nombre de tour
		while(F == false){
			t++;
			effaceEcran();
			System.out.println("[Tour : "+t+"]");
			System.out.println();
			if(t%5 == 0){							//un nouveau bonus tous les 5 tours
				nbrB++;
				Bonus[nbrB] = newbonus(Bonus);
				System.out.println("Un bonus vient d'apparaitre en ("+Bonus[nbrB].posx+" ; "+Bonus[nbrB].posy+") !"+Bonus[nbrB].description);
				maj(plat, Perso[0], Perso[1], Bonus, nbrB);
				pause();
			}
			
			System.out.println("Joueur "+j+", à vous de jouer !");						
			affichagePV(Perso);						// Récapitulatif des points de vie des joueurs entre chaque tour
			affichage(plat);						
			System.out.println("Appuyer sur ENTRER pour lancer votre dé");
			String D = sc.nextLine();				//pause le temps d'appuyer sur entré
			int d = lancerde();						//lancer de dé qui determine combien de fois on se déplace
			timePause(500);
			System.out.println("Vous avez fait "+d+"\n"+"Vous pouvez vous déplacer "+d+" fois !");
			for(int i = 0; i<d; i++){
				pause();
			System.out.println("[Déplacement numéro "+(i+1)+"]");
			affichagePV(Perso);
			affichage(plat);
			deplacement(plat, Perso[0], Perso[1], j); 	//déplacements du joueur
			surbonus(Perso[j-1], Bonus, nbrB);			//Le joueur est-il sur un bonus ? si oui, il le prend
			maj(plat, Perso[0], Perso[1], Bonus, nbrB);
			effaceEcran();
				for(int k = 0; k<6; k++){ 				//Saut de 6 lignes
					System.out.println();
				}
			affichage(plat);
			timePause(500);
			effaceEcran();
			}
			
			affichagePV(Perso);
			affichage(plat);
			int degats = Perso[j-1].attaque(Perso[j%2]);			//attaque du joueur et enregistrement du nombre de dégats
			effaceEcran();
			System.out.println("Vous avez infligé "+degats+" points de dégats");
			timePause(500);
			affichagePV(Perso);
			affichage(plat);
			timePause(2000);
			
			j = j%2 +1; 								//changement joueur 1<->2
			pause();
			F = fini(Perso[0], Perso[1]);
		}
		
		j = j%2 +1;	
		effaceEcran();													
		endgame(j, t);									//affichage du message de fin
	}
	
	
	/** methodes structure du jeu : **/
	
	
	//choix du personnage	
	public static perso choix(int j){ // j : joueur 
		Scanner sc = new Scanner(System.in);
		
		timePause(1000);
		System.out.println("Archer (tapez 1), Barbare (tapez 2), Canonnier (tapez 3), Diable (tapez 4)");
		int numeroPerso = estPossible(1,4);
		perso P = new perso(numeroPerso, j);
		System.out.println(P.toString());
		System.out.println("Etes-vous sûr de votre choix ?");
		System.out.println("1 pour CONFIRMER,\n"+"2 pour MODIFIER");
		int y = estPossible(1,2);
		while(y != 1){
			System.out.println("Archer (tapez 1), Barbare (tapez 2), Canonnier (tapez 3), Diable (tapez 4)");
			numeroPerso = estPossible(1,4);
		    P = new perso(numeroPerso, j);
				System.out.println(P.toString());
				System.out.println("Etes-vous sûr de votre choix ?");
				System.out.println("1 pour CONFIRMER\n"+"2 pour MODIFIER");
				y = estPossible(1,2);
		}
		return P;
	}
	
	//création du plateau
	public static String [][] init(){
		String [][] plateau = new String [9][9];
		for(int i = 0; i<plateau.length; i++){
			for(int j = 0; j<plateau[i].length; j++){
					plateau[i][j] = " ";
			}
		}
		return plateau;
	}
	
	//création d'un bonus
	public static bonus newbonus(bonus [] Bonus){
		bonus B = new bonus((int)(Math.random()*4)+1);
		boolean F = true;
		for(int i = 0; i<Bonus.length; i++){
			if(B.posx == Bonus[i].posx && B.posy == Bonus[i].posy && Bonus[i].bonusAttaque != 0 && Bonus[i].bonusVie != 0){
			F = false;
			}
		}
		while(F == false){
		B = new bonus((int)(Math.random()*4)+1);
		for(int i = 0; i<Bonus.length; i++){
			if(B.posx == Bonus[i].posx && B.posy == Bonus[i].posy && Bonus[i].bonusAttaque != 0 && Bonus[i].bonusVie != 0){
			F = false;
			}else{
			F = true;
			}
		}	
	}
	return B;
}

	//mise à jour du plateau
	public static void maj(String [][] plateau, perso Perso1, perso Perso2, bonus [] B, int nbrbonus){
		for(int i = 0; i<plateau.length; i++){
				for(int j = 0; j<plateau[i].length; j++){
					plateau[i][j] = " ";
				}
			}
		plateau[Perso1.posy][Perso1.posx] = "O";
		plateau[Perso2.posy][Perso2.posx] = "X";
		for(int i = 0; i<nbrbonus+1; i++){
			if(!B[i].name.equals(" ")){  
				plateau[B[i].posy][B[i].posx] = B[i].abrev;
			}
		}
	}
	
	//lancer de dé
	public static int lancerde(){
		int x = (int)(Math.random()*6+1);
		affichagelancer(x);
		return x;
	}
	
	//déplacement
	public static void deplacement(String [][] plat, perso Perso1, perso Perso2, int j){ //j : joueur
		switch (j){
			case 1 :
			Perso1.move(Perso2);
			break;
			
			case 2 :
			Perso2.move(Perso1);
			break;
		}
	}
			
	//attaque
	public static void attaque(perso P1, perso P2, int j){
		P1.attaque(P2);
	}
	
	
	/** methodes graphiques : **/
	
	
	//nom du jeu
	public static void afficherNom(){
		System.out.println("  ___      ___                            ___________");
		System.out.println(" |   |    |   |				 |     	     |        	        __	    __");
		System.out.println(" |   |    |   | ______  _  ____    ____  |     ______|   __   _______  |  |     __| |___");
		System.out.println(" |   |____|   ||   ___|| |/ ___| / __ |  |    |____     |__| /   ____| |  |    |__   ___|");
		System.out.println(" |    ____    ||  |__  |  /      ||  ||  |     ____|     __  |  /      |  |_____  | |");
		System.out.println(" |   |    |   ||   __| | |       ||  ||  |    |         |  | |  |   __ |   ___  | | |");
		System.out.println(" |   |    |   ||  |___ | |       ||__||  |    |         |  | |  |__| | |  |   | | | |");
		System.out.println(" |___|    |___||______||_|       |____/  |____|         |__| |_______| |__|   |_| |_|");
	}

	//introduction
	public static void introduction(){
		System.out.println("_____________________");
		System.out.println("| B I E N V E N U E |");
		System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
		System.out.println();
		System.out.println("Un combat 1 vs 1 est sur le point de commencer, l'arène est prête !");
		System.out.println("Chaque joueur va devoir choisir un personnage qui a un certain nombre de points de vie et deux attaques qui lui sont propre");
		System.out.println("Des bonus vont apparaitre aléatoirement dans l'arène tout au long de la partie, à vous de vous déplacer pour récupérer ces bonus. \n"
							+ "Ces bonus peuvent booster vos attaques ou vous donner un regain de points de vie...");
		System.out.println("Déplacez vous dessus pour les récupérer, une fois récupéré le bonus est effectif jusqu'à la fin de la partie, ne les sous-estimez pas ! ");
		System.out.println();
		System.out.println("ETES-VOUS PRET(E) ??");
		System.out.println();
		timePause(1000);
		System.out.println("C'est parti !");
	}
	
	//affichage du jeu
	public static void affichage(String [][] jeu){
		for(int i = 0; i<jeu.length; i++){
			for(int j = 0; j<jeu[i].length; j++){
				System.out.print("["+jeu[i][j]+"]");
			}System.out.println();
		}
		pause();
	}
		
	//pause graphique
	public static void pause(){
		System.out.println();
		System.out.println("---");
		System.out.println();
	}
	
	//pause temporelle
	public static void timePause (int ms) {
		try {
		Thread.sleep(ms);
		}catch(InterruptedException e){}
	}
		
	//affichage du dé
	public static void affichagelancer(int x){
	
		int a = 0;
		effaceEcran();
		for(int i = 0; i<20; i++){
			a = (int)(Math.random()*6+1);
		System.out.println(" /¯¯¯/|");
		System.out.println("|¯¯¯| |");
		System.out.println("| "+a+" | |");
		System.out.println("|___|/");
		timePause(70);
		effaceEcran();
	
		}
		System.out.println(" /¯¯¯/|");
		System.out.println("|¯¯¯| |");
		System.out.println("| "+x+" | |");
		System.out.println("|___|/");
	}
	
	//affichage rapide des points de vie 
	public static void affichagePV(perso[] Perso){
		System.out.println("J1 : "+Perso[0].pv+" pv");
		System.out.println("J2 : "+Perso[1].pv+" pv");
	}

	//affichage fin du jeu
	public static void endgame(int j, int t){		// j = joueur, t = tours
		System.out.println("___________________________________________");
		System.out.println("|           LA PARTIE EST FINIE           |");
		System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
		System.out.println();
		System.out.println("BRAVO AU JOUEUR "+j);
		System.out.println("Vous avez gagné en "+t+" tours !");
		System.out.println("___________________________________________");
	}
	
	// effacer l'ecran
	public static void effaceEcran () {
		String ESC = "\033[";
		System.out.print(ESC+"2J");
		System.out.print(ESC+"0;0H");
		System.out.flush();
		
		for(int i = 0; i<5; i++){
			System.out.println();
		}
	}

	
	/** methodes d'états : **/
	
		
	//Le joueur vient-il de prendre un bonus ?
	public static void surbonus(perso Perso, bonus [] B, int nbrB){
		for(int i = 0; i<nbrB +1; i++){
			if(Perso.posx == B[i].posx && Perso.posy == B[i].posy && !B[i].name.equals(" ")){
				System.out.println("Vous prenez le "+B[i].toString());
				Perso.A1.Acac = Perso.A1.Acac + B[i].bonusAttaque;
				Perso.A2.Adist = Perso.A2.Adist + B[i].bonusAttaque;
				Perso.pv = Perso.pv + B[i].bonusVie;
				B[i] = new bonus();
				break;
			}
		}
	}
		
	//fini ?
	public static boolean fini(perso P1, perso P2){
		if(P1.pv <1 || P2.pv<1){
			return true;
		}else{
			return false;
		}
	}
	
	
	/** methode qui permet le bon fonctionnement : **/	
	
		
	//faire en sorte qu'a chaque scanner, le jeu ne crash pas si on rentre une mauvaise valeur
	public static int estPossible (int a, int b) {
		Scanner sc = new Scanner(System.in);
		int numeroPerso=-1;
		boolean B=false;
		while(!B) {
			while(numeroPerso > b || numeroPerso < a){
				System.out.println("(Entre "+a+" et "+b+"!)");
				try {
					numeroPerso = sc.nextInt();
					B=true;
				}catch(InputMismatchException e) {
					System.out.print("Un chiffre s'il te plait") ;
					sc.next();
					B=false;
				}
				
			}
		}
		return numeroPerso;
	}

}
