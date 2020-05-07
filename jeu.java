import java.util.InputMismatchException;
import java.util.Scanner;

public class jeu{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
				System.out.println("NOM DU JEU"); //Inserez le nom du jeu
				pause();
				System.out.println("Bienvenue, vous vous apprêtez à commencer un combat 1 joueur versus 1 joueur qui se déroulera dans une arène (une grille 9x9)");
				System.out.println("Chaque joueur va devoir choisir un personnage qui a un certain nombre de point de vie et deux attaques qui lui sont propre");
				System.out.println("Les joueurs vont chacun devront se deplacer d'une case et attaquer l'adversaire");
				pause();
				perso[] Perso = new perso [2];
				
				Perso[0] = choix(1);
				System.out.println("Vous avez choisi : ");
				System.out.println(Perso[0].toString());
				System.out.println();
				System.out.println("Vous êtes le [O]");
				pause();
				
				Perso[1] = choix(2);
				System.out.println("Vous avez choisi : ");
				System.out.println(Perso[1].toString());
				System.out.println();
				System.out.println("Vous êtes la [X]");
				pause();
				
				//affichage du jeu initial
				timePause(2000);
				System.out.println("Voici l'arène !");
				System.out.println();
				String [][] plat = init();
				maj(plat, Perso[0], Perso[1]);
				affichage(plat);
				timePause(5000);
				//jeu :
		int j = 1; 								//joueur 
		boolean F = fini(Perso[0], Perso[1]); 	//jeu fini ?
		int m = -1;								//choix du mouvement
		while(F == false){
				
			System.out.println("Joueur "+j+", à vous de jouer !");
			
				timePause(2000);
				affichage(plat);
				deplacement(plat, Perso[0], Perso[1], j);
				timePause(2000);
				attaque(Perso[j-1], Perso[j%2], (j%2 +1));
				affichage(plat);
				timePause(2000);
				
			j = j%2 +1; //changement joueur 1<->2
			pause();
			F = fini(Perso[0], Perso[1]);
			}
			
j = j%2 +1;						//FIN DU JEU
System.out.println("BRAVO AU JOUEUR "+j+" C'EST UN VRAI CHAMPION");
		}
		
		//affichage regles mouvements
		public static void rmove(){
			System.out.println("Vous déplacer d'une case (tapez 1)");
			System.out.println("Vous soigner 10 pv (tapez 2)");
			System.out.println("Attaquer l'adversaire (tapez 3)");
			pause();
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
				Perso2.move(Perso2);
				break;
			}
		maj(plat, Perso1, Perso2);
		affichage(plat);
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
		public static void maj(String [][] plateau, perso Perso1, perso Perso2){
			for(int i = 0; i<plateau.length; i++){
					for(int j = 0; j<plateau[i].length; j++){
						plateau[i][j] = " ";
					}
				}
			plateau[Perso1.posy][Perso1.posx] = "O";
			plateau[Perso2.posy][Perso2.posx] = "X";
		}
		
		//choix du personnage
		public static perso choix(int j){ // j : joueur 
			timePause(1000);
			Scanner sc = new Scanner(System.in);
			System.out.println("C'est à vous joueur "+j+", vous devez chosir un personnage parmis les champions suivant :\n"
									+"Archer (tapez 1), Barbare (tapez 2), Canonier (tapez 3), Diable (tapez 4)");
			int numeroPerso = sc.nextInt();
			// Tentative de code alternatif pour forcer l'entrée d'un int
			/*boolean b=false;
			while(!b) {
				try {
					numeroPerso = sc.nextInt();
					b=true;
				}catch(InputMismatchException e) {
					System.out.println("Tu dois choisir entre 1 et 4 jeune padawan");
					sc.next();
					b=false;
				}
			}*/
			while(numeroPerso > 4 || numeroPerso < 1){
				if(numeroPerso > 4 || numeroPerso < 1) {
					System.out.println("Tu dois choisir entre 1 ou 4 jeune padawan");
					sc.next();
				}
				try {
					numeroPerso = sc.nextInt();
				}catch(InputMismatchException e) {
					System.out.println("Tu dois choisir entre 1 et 4 jeune padawan");
					sc.next();
				}
			}
			return new perso(numeroPerso, j);
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

}
