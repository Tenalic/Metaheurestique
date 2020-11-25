package org.metaheurestique;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Fonction {

	void sauvegarde_donnees(String name) {

	}

	Sac charge_donnees(String name) {
		Sac sac = new Sac();

		try {
			File myObj = new File(name);
			Scanner myReader = new Scanner(myObj);
			int line = 1;
			int nextLine = 0;
			int compteur = 0;
			Groupe groupe = new Groupe();

			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				if (line == 1) {
					sac.setNombreItem(Integer.parseInt(data));
					nextLine = line + 1;
				}
				if (line == 2) {
					sac.setCapacity(Integer.parseInt(data));
					nextLine = line + 1;
				}
				if (line == 3 || line == 4 || line == 13 || line == 14) {
					String[] tab = data.split(" ");
					if (tab.length == 1) {
						sac.setBeastValue(Integer.parseInt(tab[0]));
						nextLine = line + 10;
					}
					if (tab.length > 1 && (line == 3 || line == 13)) {
						for (int i = 0; i < tab.length; i++) {
							if (compteur >= 3) {
								sac.getCout().add(groupe);
								groupe = new Groupe();
								compteur = 0;
							} else {
								compteur++;
								try {
									groupe.getListDeTrois().add(Integer.parseInt(tab[i]));
								} catch (NumberFormatException e) {
									compteur--;
								}
							}
						}
						if (!groupe.getListDeTrois().isEmpty()) {
							groupe.setTaille(groupe.getListDeTrois().size());
							sac.getCout().add(groupe);
							groupe = new Groupe();
						}
						nextLine = line + 1;
					}
					if (tab.length > 1 && (line == 4 || line == 14)) {
						for (int i = 0; i < tab.length; i++) {
							if (compteur >= 3) {
								sac.getPoid().add(groupe);
								groupe = new Groupe();
								compteur = 0;
							} else {
								compteur++;
								try {
									groupe.getListDeTrois().add(Integer.parseInt(tab[i]));
								} catch (NumberFormatException e) {
									compteur--;
								}
							}
						}
						if (!groupe.getListDeTrois().isEmpty()) {
							groupe.setTaille(groupe.getListDeTrois().size());
							sac.getPoid().add(groupe);
						}
					}
				}
				line = nextLine;
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
			sac = null;
		}
		return sac;
	}

	public Sac algoGenetique(Sac sac) {
		Sac sac1 = sac;
		Sac sac2 = sac;
		Sac sac3 = sac;
		Sac sac4 = sac;
		Sac beastSac = sac;
		Sac beastSacTemp = sac;

		for (int i = 0; i < 50000; i++) {
			sac1.mutation();
			sac2.mutation();
			sac3.mutation();
			sac4.mutation();
			beastSacTemp = beastSac(sac1, sac2, sac3, sac4);
			if (beastSacTemp.getValue() > beastSac.getValue()) {
				beastSac = beastSacTemp;
			}
			sac1 = sac;
			sac2 = sac;
			sac3 = sac;
			sac4 = sac;
			beastSacTemp = sac;
		}
		return beastSac;
	}

	public Sac beastSac(Sac sac1, Sac sac2, Sac sac3, Sac sac4) {
		if (sac1.getValue() > sac2.getValue() && sac1.getValue() > sac3.getValue()
				&& sac1.getValue() > sac4.getValue()) {
			return sac1;
		}
		if (sac2.getValue() > sac3.getValue() && sac2.getValue() > sac4.getValue()
				&& sac2.getValue() > sac1.getValue()) {
			return sac2;
		}
		if (sac3.getValue() > sac4.getValue() && sac3.getValue() > sac1.getValue()
				&& sac3.getValue() > sac2.getValue()) {
			return sac3;
		}
		return sac4;
	}

	public ArrayList<Sac> deuxBeastSacs(Sac sac1, Sac sac2, Sac sac3, Sac sac4, Sac sac5, Sac sac6, Sac sac7,
			Sac sac8) {
		ArrayList<Sac> listSac = new ArrayList<Sac>();
		return listSac;
	}

	public ArrayList<Sac> crossover(Sac sac1, Sac sac2) {
		int taille = sac1.getChoiceToTake().size();
		Sac newSac1 = sac1;
		Sac newSac2 = sac2;
		for (int i = 0; i < taille / 2; i++) {
			newSac2.getChoiceToTake().set(i, sac2.getChoiceToTake().get(i));
		}
		for (int j = taille / 2; j < taille; j++) {
			newSac1.getChoiceToTake().set(j, sac1.getChoiceToTake().get(j));
		}
		ArrayList<Sac> listSac = new ArrayList<Sac>();
		listSac.add(newSac1);
		listSac.add(newSac2);
		return null;
	}

	public Sac algoGeneticTournois(Sac sac) {
		Sac sac1 = sac;
		ArrayList<Groupe> cout = sac1.getCout();
		ArrayList<Groupe> poid = sac1.getPoid();
		ArrayList<Integer> choix = new ArrayList<Integer>();
		for (int i = 0; i < cout.size(); i++) {
			choix.add(meilleurDuGroupe(cout.get(i), poid.get(i)));// determiner pour chaque groupe le meilleurs individu
		}
		sac1.setChoiceToTake(choix);
		sac1.calculValue();

		return sac1;
	}

	public int meilleurDuGroupe(Groupe c, Groupe a) {
		float max = 0;
		int j = 0;
		for (int i = 0; i < c.getListDeTrois().size(); i++) {
			int cout = c.getListDeTrois().get(i).intValue();
			int poid = a.getListDeTrois().get(i).intValue();
			if (max < cout / poid) {
				max = cout / poid;
				j = i;
			}
		}
		return j;
	}
}