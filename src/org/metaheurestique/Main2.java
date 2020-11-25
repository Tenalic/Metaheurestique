package org.metaheurestique;

public class Main2 {
	
	static Fonction fonction = new Fonction();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Sac sac = fonction.charge_donnees("Instances_DKP/sdkp2_4.txt");
		if (sac != null) {
			sac.initTabChoice();
			sac = fonction .algoGeneticTournois(sac);
			sac.calculValue();
			System.out.println("Cout : " + sac.getValue());
			System.out.println("poid : " + sac.getCapacityActuel());
			//pas gerer le fait que la capaciteActuel depasse la taille du sac
		}

	}

}
