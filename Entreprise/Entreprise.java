package Entreprise;


public class Entreprise {
	private String name;
	private double reputationClient;
	private double argent;
	private double reputationPublic;
	private double infrastructures;
	private double donnees;
	
	private int temps_formation=10;
	private int temps_debut_formation;
	private int index_formation;
	
	private int temps_upgrade = 10;
	private int temps_debut_upgrade;
	private int index_upgrade;
	
	private boolean formation_en_cours = false;
	private boolean upgrade_en_cours = false;
	private boolean structures[] = {false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false};
	
	
	
	public Entreprise(String name, double reputationClient, double argent, double reputationPublic, double infrastructures, double donnees) {
		this.name = name;
		this.reputationClient = reputationClient;
		this.argent = argent;
		this.reputationPublic = reputationPublic;
		this.infrastructures = infrastructures;
		this.donnees = donnees;
	}
	
	public boolean getStructuresIndex(int i) {
		if(i<structures.length) {
			return structures[i];
		}
		else {
			return false;
			//TODO faire une vraie gestion d'exception
		}
	}
	
	public void evolution_parametres(double e_rep_client, double e_argent, double e_rep_public, double e_infrastructures) {
		reputationClient-=e_rep_client;
		argent-=e_argent;
		reputationPublic-=e_rep_public;
		infrastructures-=e_infrastructures;
	}
	
	public boolean debut_formation(double Argent, int i, int temps) {
		System.out.println("La froamtion commence !");
		if(!formation_en_cours && argent >= Argent) {
			index_formation = i;
			formation_en_cours = true;
			temps_debut_formation = temps;
			argent -= Argent;
			return true;
		}
		else
			return false;
	}
	
	public boolean fin_formation(int temps_courant) {
		if(formation_en_cours && temps_courant - temps_debut_formation > temps_formation) {
			structures[index_formation] = true;
			formation_en_cours = false;
			return true;
		}
		else
			return false;
	}
	
	public double pourcent_formation(int temps_courant) {
		return (temps_courant - temps_debut_formation)/temps_formation;
	}
	
	public double pourcent_upgrade(int temps_courant) {
		return (temps_courant - temps_debut_upgrade)/temps_upgrade;
	}
	
	public boolean debut_upgrade(double Argent, int i, int temps) {
		if(!upgrade_en_cours && argent >= Argent) {
			index_upgrade = i;
			upgrade_en_cours = true;
			temps_debut_upgrade = temps;
			argent -= Argent;
			return true;
		}
		else
			return false;
	}
	
	public boolean fin_upgrade(int temps_courant) {
		if(upgrade_en_cours && temps_courant - temps_debut_upgrade > temps_upgrade) {
			structures[index_upgrade] = true;
			upgrade_en_cours = false;
			return true;
		}
		else
			return false;
	}
	
	public void RSSI_true() {
		structures[0] = true;
	}
	
	public void finance_formation_true() {
		structures[1] = true;
	}
	
	public void comm_formation_true() {
		structures[2] = true;
	}
	
	public void juridique_formation_true() {
		structures[3] = true;
	}
	
	public void director_formation_true() {
		structures[4] = true;
	}
	
	public void firewall_true() {
		structures[5] = true;
	}
	
	public void antivirus_true() {
		structures[6] = true;
	}
	
	public void maintenance_true() {
		structures[7] = true;
	}
	
	public void protection_true() {
		structures[8] = true;
	}
	
	public void logiciels_a_jour_true() {
		structures[9] = true;
	}
	
	
	public void plan_reprise_true() {
		structures[10] = true;
	}
	
	public void backup_true() {
		structures[11] = true;
	}
	
	public void risques_true() {
		structures[12] = true;
	}
	
	public void fournisseur_1() {
		structures[13] = true;
	}
	
	public void fournisseur_2() {
		structures[14] = true;
	}
	
	public void cryptage_donnees() {
		structures[15] = true;
	}
	
	public void protection_donnees() {
		structures[16] = true;
	}
	
	public double getArgent() {
		return argent;
	}

	public String getName() {
		return name;
	}

	public double getReputationClient() {
		return reputationClient;
	}

	public double getReputationPublic() {
		return reputationPublic;
	}

	public double getInfrastructures() {
		return infrastructures;
	}

	public double getDonnees() {
		return donnees;
	}
	
	public boolean getFormation() {
		return formation_en_cours;
	}
	
	public boolean getUpgrade() {
		return upgrade_en_cours;
	}
	
}
