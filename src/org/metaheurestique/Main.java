package org.metaheurestique;

public class Main {

	static Fonction fonction = new Fonction();

	public static void main(String[] args) {
		Sac sac = fonction.charge_donnees("idkp1_1.txt");
		if (sac != null) {
			sac.initTabChoice();
			//sac = fonction.algoGenetique(sac);
			sac = fonction.algoGeneticTournois(sac);
			System.out.println(sac.getValue());
			System.out.println(sac.getCapacityActuel());
		}
	}

}
