import java.util.InputMismatchException;
import java.util.Scanner;

public class joseph{
    
    
    
    //modifs à faire pour la classe de création du personnage
        //ajout d'attributs booléens correspondant à la possession de bonus
        public boolean bonus1;
        public boolean bonus1;
        
        //et la modif du constructeur qui va avec
        bonus1=false;
        bonus2=true;
    
    
    
    
    
    
    
    //Au moment de la création du plateau de jeu initial, f aire appel à cette méthode pour créer un plateau de jeu secondaire qui servira à stocker les bonus cachés
    int platBis = initBis();
    
    
    //création plateauBis
    public static int [][] initBis(){
        int [][] plateauBis = new int [9][9];
            for(int i = 0; i<plateauBis.length; i++){
                for(int j = 0; j<plateauBis[i].length; j++){
                    plateauBis[i][j] = " ";
                }
            }
        return plateauBis;
    }
    
   
    // méthode à activer à chaque tour pour générer des bonus aléatoirement, cachés sur le plateauBis et affichés par un "B" sur le plateau
    public static String BonusAleatoires (String [][] plateau, String [][] plateauBis){
        for(int i = 0; i<plateau.length; i++){
            for(int j = 0; j<plateau[i].length; j++){
                if (plateau[i][j] != " "){
                    int A = int Math.random()*100; // je me suis dit qu'il fallait une proba assez grande, car il en y aurais à chaque tour et il y a 81 cases sur le plateau
                    if (A==25){
                        plateauBis[i][j] = Choix.Bonus();
                        plateau [i][j] = "B";
                    }
                }
            }
        }
    }
    
    // méthode pour choisir aléatoirement un bonus
    public static int ChoixBonus(){
        int B = int Math.random()*2+1;
        return B;
    }
    
    //méthode à appliquer à chaque modification de la position d'un perosnnage : elle permet de lui attribuer un éventuel bonus présent sur sa nouvelle position
    public static void Bonus (perso p, String [][] plateau, String [][] plateauBis){    // p est le personnage
        if (plateauBis[p.posy][p.posx] == 1){
            p.pv = perso.pv+10;
            System.out.println("Vous avez gagané 10 pv!");
            majPlat(plat, platBis);
        }
        if (plateauBis[p.posy][p.posx] == 2){
            p.bonus1 = true;
            System.out.println("Votre prochaine attaque sera 20% plus efficace!");
        }
        if (plateauBis[p.posy][p.posx] == 3){
            p.bonus2 = true;
            System.out.println("Vous pourrez vous déplacer 2 fois d'affilé au prochain tour!");
        }
    }
    
    
    //modification de l'attaque ; plusieurs possibilités : voir la plus simple : principe :
    //if bonus1 == true alors on copie cole ce qui existe déjà en gros et multiplie les pts d'attaque par 1,2 et on arrondie au sup?inf? ++++ il faut ajouter la modif du plateauBis pour supprimer le bonus utilisé
    //else on remet le truc déjà existant
    
    
    
    //méthode de mise à jour du plateau quand un bonus est utilisé
    public static void majPlat (String [][] plateau, String [][] plateauBis){
        for(int i = 0; i<plateau.length; i++){
            for(int j = 0; j<plateau[i].length; j++){
                if (plateauBis[i][j]!=0 && plateau[i][j]=="X")
                    plateauBis[i][j]=0;
                }
            }
        }
    }
    
    //modif de la méthode déplacement
    //je ne sais pas où le faire car je ne vois pas où est utilisée la méthode "rmove"

}
