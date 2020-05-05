import java.util.Scanner;

public class jeu{
	public static void main(String[] args) {
		System.out.println("NOM DU JEU");
		for
	}
public static perso choix(int j){ // j : joueur 
Scanner sc = new Scanner(System.in);
	System.out.println("Bienvenue dans l'arène joueur "+j+", vous devez chosir un personnage parmis les champions suivant :\n"
							+"Archer (tapez 1), Barbare (tapez 2), Canonier (tapez 3), Diable (tapez 4)");
	int numeroPerso = sc.nextInt();
	while(numeroPerso > 4 || numeroPerso < 1){
		System.out.println("Tu dois choisir entre 1 et 4 jeune padawan");
		int numeroPerso = sc.nextInt();
	}
	return new perso(numeroPerso);
}
}
