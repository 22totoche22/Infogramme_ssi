package Attaque;

public class ListeAttaques {
	
	public static Attaque[] listeAttaques() {
		Scenario d2 = new Scenario("Attaque bien",6,6,6,6,6,6);
		ScenariiTree c = new ScenariiTree(d2,1,null,null);
		Attaque b = new Attaque(0.05,c);
		Attaque a[] = {b};
		return a;
	}
}
