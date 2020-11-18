package org.metaheurestique;

import java.io.File;
import java.io.FileNotFoundException;
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
								groupe.getListDeTrois().add(Integer.parseInt(tab[i]));
							}
						}
						if (!groupe.getListDeTrois().isEmpty()) {
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
								groupe.getListDeTrois().add(Integer.parseInt(tab[i]));
							}
						}
						if (!groupe.getListDeTrois().isEmpty()) {
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

}