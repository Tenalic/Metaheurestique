package org.metaheurestique;

public class Main {

	static Fonction fonction = new Fonction();

	public static void main(String[] args) {
		Sac sac = fonction.charge_donnees("idkp1_1.txt");
		if (sac != null) {
			sac.initTabChoice();
			sac = fonction.algoGenetique(sac);
			System.out.println("value = : " + sac.getValue());
			System.out.println("capacity = : " + sac.getCapacityActuelle());
			System.out.println("capacity free = : " + (sac.getCapacity() - sac.getCapacityActuelle()));
		}
	}

}
