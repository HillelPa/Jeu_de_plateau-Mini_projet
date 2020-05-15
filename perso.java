public void move(perso A){
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Où voulez-vous aller ?");
		System.out.println();
			if(this.posy > 0){
				System.out.println("z ↑︎");
			}
			if(this.posx > 0){
				System.out.println("q ←");
			}	
			if(this.posy<8){
				System.out.println("s ↓");
			}
			if(this.posx<8){
				System.out.println("d︎ →");
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
