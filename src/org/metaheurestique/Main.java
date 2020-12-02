package org.metaheurestique;

import java.util.Date;

public class Main {

	static Fonction fonction = new Fonction();

	public static void main(String[] args) {
		Sac sac = fonction.charge_donnees("Instances_DKP/sdkp2_4.txt");
		if (sac != null) {
			sac.initTabChoice();
			Date dateDebut = new Date();
			System.out.println(dateDebut.getMinutes() + "m " + dateDebut.getSeconds() + "s");
			sac = fonction.algoGenetique(sac, 8, 2000000, true);
			Date dateFin = new Date();
			System.out.println(dateFin.getMinutes() + "m " + dateFin.getSeconds() + "s");
			System.out.println("value = : " + sac.getValue());
			System.out.println("capacity = : " + sac.getCapacityActuelle());
			System.out.println("capacity free = : " + (sac.getCapacity() - sac.getCapacityActuelle()));
			if (sac.getBeastValue() != 0) {
				System.out.println("la meilleur valeur theorique est : " + sac.getBeastValue());
				System.out.println("Meilleur valeur - la notre : " + (sac.getBeastValue() - sac.getValue()));
			}
		}
	}

}
