package org.metaheurestique;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Fonction {

	/**
	 * Sauvegarde le sac comme suit :
	 * 
	 * nb Groupes
	 * capacite
	 * value (de la solution)
	 * le cout des objets
	 * le poid des objets
	 * la solution normalisé (1 si prend l'objet 0 sinon)
	 * la solution brute (-1 rien 0 item 1, 1 item 2 et 2 item 3)
	 *
	 * /!\ Supprime le contenue de la sollution précédente si elle existé
	 *
	 * 
	 * @param name : nom du fichier
	 * @param sac : sac a sauvegarder
	 */
	void sauvegarde_donnees(String name, Sac sac) {
		try {
			new File(name + ".txt");
			FileWriter writter = new FileWriter(name + ".txt");
			writter.write(String.valueOf(sac.getNombreItem()) + "\n");
			writter.write(String.valueOf(sac.getCapacity()) + "\n");
			writter.write(String.valueOf(sac.getValue()) + "\n");
			for (Groupe groupePrix : sac.getCout()) {
				for (Integer cout : groupePrix.getListDeTrois()) {
					writter.write(cout.toString() + " ");
				}
			}
			writter.write("\n");
			for (Groupe groupeValue : sac.getCout()) {
				for (Integer value : groupeValue.getListDeTrois()) {
					writter.write(value.toString() + " ");
				}
			}
			writter.write("\n");
			int value;
			int compteur = 0;
			for (int i = 0; i < sac.getChoiceToTake().size(); i++) {
				value = sac.getChoiceToTake().get(i);
				for (int j = 0; j < sac.getPoid().get(i).getTaille(); j++) {
					if (compteur == value) {
						writter.write("1 ");
					} else {
						writter.write("0 ");
					}
					compteur++;
				}
				compteur = 0;
			}
			writter.write("\n");
			for (Integer value2 : sac.getChoiceToTake()) {
				writter.write(value2.toString() + " ");
			}
			writter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Charge les donnée d'un sac
	 * 
	 * @param name : nom du fichier
	 * @return le sac chargé
	 */
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

	/**
	 * Fonction de l'algo génétique
	 * 
	 * @param sac                : sac sur le quelle l'algo vas tavailler
	 * @param nombreDeSac        : nombre de sac qui seront manimuler par l'algo a
	 *                           chaque iteration
	 * @param nombreIterationMax : nombre d'iteration
	 * @return le sac contenant la solution
	 */
	public Sac algoGenetique(Sac sac, int nombreDeSac, int nombreIterationMax) {
		if (nombreDeSac > 0) {

			Sac sacVide;

			ArrayList<Sac> listeSacs = new ArrayList<Sac>();
			ArrayList<Sac> listeEnfants = new ArrayList<Sac>();

			int compt = 0;

			for (int i = 0; i < nombreDeSac; i++) {
				listeSacs.add(new Sac(sac));
				listeSacs.get(i).initTabChoiceRand();
			}

			Sac beastSac = sac;
			Sac beastSacTemp;

			for (int i = 0; i < nombreIterationMax; i++) {

				for (int j = 0; j < nombreDeSac; j++) {
					listeSacs.get(j).mutation();
				}

				listeSacs = suprSollutionNonPossible(listeSacs);

				/**
				 * tri par valeurs du sac decroissante
				 */
				Collections.sort(listeSacs);

				beastSacTemp = newBeastSacTemp(listeSacs);

				if (beastSacTemp != null && isNewMeilleurSac(beastSacTemp, beastSac) == true) {
					beastSac = beastSacTemp;
				}

				if (listeSacs.size() >= 2) {
					listeEnfants = crossover(listeSacs.get(0), listeSacs.get(1));
				}

				listeSacs = new ArrayList<Sac>();

				listeSacs.add(beastSac);

				for (int k = 0; k < listeEnfants.size(); k++) {
					if (listeEnfants.get(k).getCapacity() >= listeEnfants.get(k).getCapacityActuelle()) {
						listeSacs.add(listeEnfants.get(k));
					}
				}

				compt = nombreDeSac - listeSacs.size();

				for (int l = 0; l < compt; l++) {
					sacVide = new Sac(sac);
					sacVide.initTabChoiceRand();
					listeSacs.add(sacVide);
				}
			}
			return beastSac;
		}
		return sac;
	}

	/**
	 * Fonction de l'algo génétique
	 * 
	 * @param sac                         : sac sur le quelle l'algo vas tavailler
	 * @param nombreDeSac                 : nombre de sac qui seront manimuler par
	 *                                    l'algo a chaque iteration
	 * @param nombreIterationMax          : nombre d'iteration maximum
	 * @param utiliserValeursMaxTheorique : utiliser la valeurs theorique maximum,
	 *                                    permet a l'algo de s'arreter plus tot si
	 *                                    il trouve la solution theorique
	 * @return le sac contenant la solution
	 */
	public Sac algoGenetique(Sac sac, int nombreDeSac, int nombreIterationMax, boolean utiliserValeursMaxTheorique) {
		if (nombreDeSac > 0) {

			Sac sacVide;

			ArrayList<Sac> listeSacs = new ArrayList<Sac>();
			ArrayList<Sac> listeEnfants = new ArrayList<Sac>();

			int compt = 0;

			for (int i = 0; i < nombreDeSac; i++) {
				listeSacs.add(new Sac(sac));
				listeSacs.get(i).initTabChoiceRand();
			}

			Sac beastSac = sac;
			Sac beastSacTemp;

			int i = 0;

			while (i < nombreIterationMax
					&& (utiliserValeursMaxTheorique == false || (utiliserValeursMaxTheorique == true
							&& beastSac.getBeastValue() != 0 && beastSac.getValue() < beastSac.getBeastValue()))) {

				for (int j = 0; j < nombreDeSac; j++) {
					listeSacs.get(j).mutation();
				}

				listeSacs = suprSollutionNonPossible(listeSacs);

				/**
				 * tri par valeurs du sac decroissante
				 */
				Collections.sort(listeSacs);

				beastSacTemp = newBeastSacTemp(listeSacs);

				if (beastSacTemp != null && isNewMeilleurSac(beastSacTemp, beastSac) == true) {
					beastSac = beastSacTemp;
				}

				if (listeSacs.size() >= 2) {
					listeEnfants = crossover(listeSacs.get(0), listeSacs.get(1));
				}

				listeSacs = new ArrayList<Sac>();

				listeSacs.add(beastSac);

				for (int k = 0; k < listeEnfants.size(); k++) {
					// if (listeEnfants.get(k).getCapacity() >=
					// listeEnfants.get(k).getCapacityActuelle()) {
					listeSacs.add(listeEnfants.get(k));
					// }
				}

				compt = nombreDeSac - listeSacs.size();

				for (int l = 0; l < compt; l++) {
					sacVide = new Sac(sac);
					sacVide.initTabChoiceRand();
					listeSacs.add(sacVide);
				}

				i++;
			}
			return beastSac;
		}
		return sac;
	}

	public ArrayList<Sac> deuxBeastSacs(Sac sac1, Sac sac2, Sac sac3, Sac sac4, Sac sac5, Sac sac6, Sac sac7,
			Sac sac8) {
		ArrayList<Sac> listSac = new ArrayList<Sac>();
		return listSac;
	}

	public boolean isNewMeilleurSac(Sac nouveau, Sac actuelle) {
		if (nouveau.getValue() > actuelle.getValue() && nouveau.getCapacity() >= nouveau.getCapacityActuelle()) {
			return true;
		} else {
			return false;
		}
	}

	public Sac newBeastSacTemp(ArrayList<Sac> listeSacs) {
		for (Sac sac : listeSacs) {
			if (sac.getCapacityActuelle() <= sac.getCapacity()) {
				return sac;
			}
		}
		return null;
	}

	public ArrayList<Sac> suprSollutionNonPossible(ArrayList<Sac> listeSac) {
		ArrayList<Sac> newListeSac = new ArrayList<Sac>();
		for (Sac sac : listeSac) {
			if (sac.getCapacityActuelle() <= sac.getCapacity()) {
				newListeSac.add(sac);
			}
		}
		return newListeSac;
	}

	/**
	 * Fonction réalisant le croisement de 2 sacs
	 * 
	 * @param sac1
	 * @param sac2
	 * @return liste contenant les 2 enfants résultant du croisement
	 */
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
		return listSac;
	}

	// non utilisé
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

	// non utilisé
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