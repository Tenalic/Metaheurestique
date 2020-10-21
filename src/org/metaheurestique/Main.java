package org.metaheurestique;

public class Main {

	static Fonction fonction = new Fonction();

	public static void main(String[] args) {
		Sac sac = fonction.charge_donnees("idkp1_1.txt");
		if (sac != null) {
			for (int entier : sac.getCout()) {
				System.out.println(entier);
			}
		}

	}

}
