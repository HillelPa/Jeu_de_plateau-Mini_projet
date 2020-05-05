import java.util.Scanner;

public class jeu{
	public static void main(String[] args) {
		System.out.println("NOM DU JEU");
		perso Perso1 = choix(1);
		System.out.println("Vous avez choisi : ");
		//System.out.println(Perso1.toString);
		perso Perso2 = choix(2);
		System.out.println("Vous avez choisi : ");
		//System.out.println(Perso2.toString);
		String [][] plat = init();
		maj(plat, Perso1, Perso2);
		affichage(plat);
		
		System.out.println("Joueur 1, a vous de jouer !");
		deplacement(plat, Perso1, Perso2, 1);
		
	}

//deplacement
public static void deplacement(String [][] plat, perso Perso1, perso Perso2, int j){ //j : joueur
	switch (j){
		case 1 :
		Perso1.move();
		break;
		
		case 2 :
		Perso2.move();
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
Scanner sc = new Scanner(System.in);
	System.out.println("Bienvenue dans l'arène joueur "+j+", vous devez chosir un personnage parmis les champions suivant :\n"
							+"Archer (tapez 1), Barbare (tapez 2), Canonier (tapez 3), Diable (tapez 4)");
	int numeroPerso = sc.nextInt();
	while(numeroPerso > 4 || numeroPerso < 1){
		System.out.println("Tu dois choisir entre 1 et 4 jeune padawan");
		numeroPerso = sc.nextInt();
	}
	return new perso(numeroPerso, j);
}
}
