import java.util.Scanner;

import java.util.Scanner;

public class jeu{
	public static void main(String[] args) {
		System.out.println("Bienvenue dans l'arène, vous devez chosir un personnage parmis les champions suivant :\n"
							+"Archer (tapez 1), Barbare (tapez 2), Canonier (tapez 3), Diable (tapez 4)");
		Scanner sc = new Scanner(System.in);					
		int numeroPerso = sc.nextInt();
		System.out.println("Voulez vous démarer en bas (tapez 0) où en haut (tapez 1) de la zone de combat ?");
		int posDepart = sc.nextInt();
		Perso monPerso = new Perso(numeroPerso,posDepart);
		System.out.println("Vous avez choisis le personnage : "+monPerso.getNom()+", il possède "+monPerso.getPv()+" points de vie.");
							
		
		
		
		
		
		sc.close();
	}
}
