package Attaque;

import Entreprise.Entreprise;

public interface ModeleAttaque {
	
	boolean 	reveilAttaque(int temps);
	
	Scenario	Attaquer(Entreprise e);
}
