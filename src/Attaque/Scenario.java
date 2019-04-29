package Attaque;

public class Scenario {
	private String texte;
	private int g_argent;
	private int g_rep_publique;
	private int g_rep_client;
	private int g_rep_employe;
	private int g_infrastructures;
	private int g_donnees;
	
	public Scenario(String texte, int g_argent, int g_rep_publique, int g_rep_client, int g_rep_employe, int g_infrastructures, int g_donnees) {
		this.texte = texte;
		this.g_argent = g_argent;
		this.g_rep_publique = g_rep_publique;
		this.g_rep_client = g_rep_client;
		this.g_rep_employe = g_rep_employe;
		this.g_infrastructures = g_infrastructures;
		this.g_donnees = g_donnees;
	}

	public String getTexte() {
		return texte;
	}

	public int getG_argent() {
		return g_argent;
	}

	public int getG_rep_publique() {
		return g_rep_publique;
	}
	
	public int getG_rep_client() {
		return g_rep_client;
	}

	public int getG_rep_employe() {
		return g_rep_employe;
	}

	public int getG_infrastructures() {
		return g_infrastructures;
	}

	public int getG_donnees() {
		return g_donnees;
	}
	
	public void reduce_gravities(int g_argent, int g_rep_publique, int g_rep_client, int g_rep_employe, int g_infrastructures, int g_donnees) {
		this.g_argent -= g_argent;
		this.g_rep_publique -= g_rep_publique;
		this.g_rep_client -= g_rep_client;
		this.g_rep_employe -= g_rep_employe;
		this.g_infrastructures -= g_infrastructures;
		this.g_donnees -= g_donnees;
	}
	
	
}
