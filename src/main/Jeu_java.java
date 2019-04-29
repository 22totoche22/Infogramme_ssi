package main;
import Attaque.Attaque;
import Attaque.ListeAttaques;
import Attaque.Scenario;
import Entreprise.Entreprise;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Jeu_java{
	
	private Entreprise e;
	private Attaque[] attaques;
	private Scenario sc_attaque;
	private boolean attaque_en_cours;
	private boolean analyse;
	
	private int argent;
	private int rep_publique;
	private int rep_client;
	private int rep_employe;
	private int infrastructures;
	private int donnees;
	
	public Jeu_java() {
		e = new Entreprise("Air ENAC", 100, 1000, 100, 1000, 100);
		attaques = ListeAttaques.listeAttaques();
		attaque_en_cours = false;
	}

	public void begin() {
		// TODO Auto-generated method stub
		System.out.println("Bienvenue dans le jeu SSI");
	}
	
	
	public boolean loop(int temps) {
		for(int i=0;i<attaques.length;i++) {
					
			if(!attaque_en_cours && attaques[i].reveilAttaque(temps)) {
						
				sc_attaque = attaques[i].Attaquer(e);
				attaque_en_cours = true;
				e.evolution_parametres(sc_attaque.getG_rep_client(), sc_attaque.getG_argent(), sc_attaque.getG_rep_publique(), sc_attaque.getG_infrastructures());
			}
		}
		return attaque_en_cours;
	}
	
	public Entreprise getEntreprise() {
		return e;
	}
	
	public void Evolution_entreprise() {
		e.evolution_parametres(sc_attaque.getG_rep_client(), sc_attaque.getG_argent(), sc_attaque.getG_rep_publique(), sc_attaque.getG_infrastructures());
	}
	
	public String getTexteScenario() {
		return sc_attaque.getTexte();
	}
	
	public void evolution_gravite() {
		if(analyse) {
			sc_attaque.reduce_gravities(argent+1, rep_publique+1, rep_client+1, rep_employe+1, infrastructures+1, donnees+1);
		}
		sc_attaque.reduce_gravities(argent, rep_publique, rep_client, rep_employe, infrastructures, donnees);
	}
	
	public void analyseTrue() {
		analyse = true;
	}
	
	public void analyseFalse() {
		analyse = false;
	}
}