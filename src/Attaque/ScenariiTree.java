package Attaque;

import Entreprise.Entreprise;

public class ScenariiTree {
	private Scenario scenario;
	private int guard_index;
	private ScenariiTree left;
	private ScenariiTree right;
	
	public ScenariiTree(Scenario scenario, int guard_index, ScenariiTree left, ScenariiTree right) {
		this.scenario = scenario;
		this.guard_index = guard_index;
		this.left = left;
		this.right = right;
	}
	
	public Scenario findScenario(Entreprise e) {
		if(left != null) {
			if(e.getStructuresIndex(guard_index)) {
				return right.findScenario(e);
			}
			else {
				return left.findScenario(e);
			}
		}
		else {
			return scenario;
		}
	}
}
