import java.util.Scanner;

public class jeu{
	public static void main(String[] args) {
Scanner sc = new Scanner(System.in);

		System.out.println("NOM DU JEU");
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
		String [][] plat = init();
		maj(plat, Perso[0], Perso[1]);
		affichage(plat);
		
		//jeu :
int j = 1; 								//joueur 
boolean F = fini(Perso[0], Perso[1]); 	//jeu fini ?
int m = -1;								//choix du mouvement
while(F == false){
		
	System.out.println("Joueur "+j+", a vous de jouer !");
	System.out.println("Vous avez 3 mouvements ; vous pouvez : ");
	rmove();
	for(int i = 0; i<3; i++){
		affichage(plat);
		
		System.out.println("Mouvement "+(i+1)+" : ");
		System.out.println("(Pour revoir les mouvements possibles tapez 4)");
		m = sc.nextInt();
		while(m == 4){
			rmove();
			m = sc.nextInt();
		}
		switch(m){
			case 1 :
			deplacement(plat, Perso[0], Perso[1], j);
			pause();
			break;
			
			case 2 : 
			Perso[j-1].pv(-10);
			System.out.println(Perso[j-1].toStringPV(j));
			pause();
			break;
			
			case 3 : 
			attaque(Perso[j-1], Perso[j%2], (j%2 +1));
			break;
		}
	}
	j = j%2 +1; //changement joueur 1<->2
	pause();
}
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
System.out.println("---");
System.out.println();
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

//attaque
public static void attaque(perso P1, perso P2, int j){
	P1.attaque(P2);
	System.out.println("---");
	System.out.println();
	System.out.println(P2.toStringPV(j));
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
