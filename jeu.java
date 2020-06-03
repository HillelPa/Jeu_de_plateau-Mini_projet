import java.util.InputMismatchException;
import java.util.Scanner;

public class jeu{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
				
		afficherNom(); //affichage du nom du jeu
		timePause(2000);
		pause();
		introduction(); //affichage des regles
		pause();

		perso[] Perso = new perso [2]; //Tableau qui stockera les persos des joueurs 1 et 2
		
		System.out.println("joueur 1, c'est a vous de choisir un personnage parmis les champions suivant :");
		Perso[0] = choix(1);
		pause();
		System.out.println("Vous avez choisi "+Perso[0].nom);	
		System.out.println();
		System.out.println("Vous êtes le [O]");
		pause();
		
		System.out.println("joueur 2, c'est a vous de choisir un personnage parmis les champions suivant :");
		Perso[1] = choix(2);
		pause();
		System.out.println("Vous avez choisi "+Perso[1].nom);	
		System.out.println();
		System.out.println("Vous êtes le [X]");
		pause();
		
		//affichage du jeu initial
		timePause(2000);
		System.out.println("Voici l'arène !");
		System.out.println();
		String [][] plat = init();
		int nbrB = -1;
		bonus [] Bonus = new bonus [100];
		nbrB++;
		Bonus[nbrB] = new bonus();
		maj(plat, Perso[0], Perso[1], Bonus, nbrB);
		affichage(plat);
		timePause(5000);
		
		//jeu :
		int j = 1; 								//joueur 
		boolean F = fini(Perso[0], Perso[1]); 	//jeu fini ?	
		int t = 0; 								// nombre de tour
		while(F == false){
			t++;
			System.out.println("[Tour : "+t+"]");
			System.out.println();
			if(t%6 == 0){					//un nouveau bonus tous les six tours
				nbrB++;
				Bonus[nbrB] = newbonus();
				System.out.println("Un bonus vient d'apparaitre en ("+Bonus[nbrB].posx+" ; "+Bonus[nbrB].posy+") !"+Bonus[nbrB].description);
				maj(plat, Perso[0], Perso[1], Bonus, nbrB);
				affichage(plat);
				pause();
			}
			
			System.out.println("Joueur "+j+", à vous de jouer !");
			timePause(2000);
			
			affichage(plat);
			deplacement(plat, Perso[0], Perso[1], j);
			surbonus(Perso[j-1], Bonus, nbrB);
			maj(plat, Perso[0], Perso[1], Bonus, nbrB);
			affichage(plat);
			timePause(2000);
			
			attaque(Perso[j-1], Perso[j%2], (j%2 +1));
			affichage(plat);
			timePause(2000);
			
			j = j%2 +1; //changement joueur 1<->2
			pause();
			F = fini(Perso[0], Perso[1]);
		}
		
		j = j%2 +1;				
		//FIN DU JEU
		System.out.println("La partie est finie !");
		System.out.println("Bravo au joueur "+j+", vous avez gagné !");
	}
		
	
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
						
	//creation d'un bonus
	public static bonus newbonus(){
		bonus B = new bonus((int)(Math.random()*4)+1);
		return B;
	}

	//pause graphique
	public static void pause(){
		System.out.println();
		System.out.println("---");
		System.out.println();
	}
		
	//deplacement
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
		
	//affichage du jeu
	public static void affichage(String [][] jeu){
		for(int i = 0; i<jeu.length; i++){
			for(int j = 0; j<jeu[i].length; j++){
				System.out.print("["+jeu[i][j]+"]");
			}System.out.println();
		}
		pause();
	}
		
	//mis a jour du plateau
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
	
	//choix du personnage	
	public static perso choix(int j){ // j : joueur 
		Scanner sc = new Scanner(System.in);
		
		timePause(1000);
		System.out.println("Archer (tapez 1), Barbare (tapez 2), Canonier (tapez 3), Diable (tapez 4)");
		int numeroPerso = estPossible(1,4);
		perso P = new perso(numeroPerso, j);
		System.out.println(P.toString());
		System.out.println("Etes vous sur de votre choix ?");
		System.out.println("1 pour CONFIRMER,\n"+"2 pour MODIFIER");
		int y = estPossible(1,2);
		while(y != 1){
			System.out.println("Archer (tapez 1), Barbare (tapez 2), Canonier (tapez 3), Diable (tapez 4)");
			numeroPerso = estPossible(1,4);
		    P = new perso(numeroPerso, j);
				System.out.println(P.toString());
				System.out.println("Etes vous sur de votre choix ?");
				System.out.println("1 pour CONFIRMER\n"+"2 pour MODIFIER");
				y = estPossible(1,2);
		}
		return P;
	}
		
	//attaque
	public static void attaque(perso P1, perso P2, int j){
		P1.attaque(P2);
		System.out.println("---");
		System.out.println();
		System.out.println(P2.toStringPV(j));
	}
		
	//effaceEcran
	public static void effaceEcran () {
		String ESC = " \033[ ";
		System .out . print (ESC +"2J");
		System .out . print (ESC +"0;0 H");
		System .out . flush ();
	}
		
	//pause 
	public static void timePause (int ms) {
		try {
		Thread.sleep(ms);
		}catch(InterruptedException e){}
	}
		
	//fini ?
	public static boolean fini(perso P1, perso P2){
		if(P1.pv <1 || P2.pv<1){
			return true;
		}else{
			return false;
		}
	}
			
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
		System.out.println("_____________________");
		System.out.println();
		System.out.println("Un combat 1 vs 1 est sur le point de commencer, l'arene est prete !");
		System.out.println("Chaque joueur va devoir choisir un personnage qui a un certain nombre de point de vie et deux attaques qui lui sont propre");
		System.out.println("Des bonus vont apparaitre aléatoirement dans l'arène tout au long de la partie, à vous de vous déplacer pour récuperer ces bonus. \n"
							+ "Ces bonus peuvent booster vos attaques ou vous donner un regain de points de vie...");
		System.out.println();
		System.out.println("ETES-VOUS PRET(E) ??");
		System.out.println();
		timePause(1000);
		System.out.println("C'est parti !");
	}
		
}


