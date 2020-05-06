public class Attaque{

public String nom;				//nom de l'attaque 
public int Adist; 				//points d'attaque a distance
public int Acac; 				//points d'attaque au corps a corps

//constructeurs 
public Attaque(int a, boolean d){ // a = type de heros, d : TRUE = distance; FALSE = corps a corps
	switch(a){
	case 1:
		if(d == true){
			this.nom = "Flêches";
			this.Adist = 25;
			this.Acac = 10;
		}else{
			this.nom = "Cri";
			this.Adist = 5;
			this.Acac = 15;
		}
	break;
	case 2 :
		if(d == true){
			this.nom = "lancé de couteaux";
			this.Adist = 10;
			this.Acac = 5;
		}else{
			this.nom = "Épée";
			this.Adist = 5;
			this.Acac = 30;
		 }
	break;
	case 3 :
		if(d == true){
			this.nom = "Gros canon";
			this.Adist = 40;
			this.Acac = 10;
		}else{
			this.nom = "Canon de poche";
			this.Adist = 10;
			this.Acac = 30;
		 }
	break;
	case 4 :
		if(d == true){
			this.nom = "Monté aux enfers";
			this.Adist = 25;
			this.Acac = 15;
		}else{
			this.nom = "Boule de feu";
			this.Adist = 15;
			this.Acac = 25;
		 }
	break;
} 
}

//description
public String toString(){
	return this.nom;
}

}
