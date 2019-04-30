package Attaque;
import Entreprise.Entreprise;

public class Attaque implements ModeleAttaque{
	private double frequence;
	private int tempsDerniereAttaque = 0;
	private ScenariiTree scenarii;
	
	public Attaque(double frequence, ScenariiTree scenarii) {
		this.frequence = frequence;
		this.scenarii = scenarii;
	}
	
	public boolean reveilAttaque(int temps) {
		return Math.random() < frequence;
	}
	
	//On utilise la méthode adéquate de l'arbre de scénario
	public Scenario Attaquer(Entreprise e) {
		return scenarii.findScenario(e);
	}
	
}
