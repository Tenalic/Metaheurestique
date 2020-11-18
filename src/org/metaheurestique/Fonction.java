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
	
	
}