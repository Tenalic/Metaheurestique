package org.metaheurestique;

import java.util.ArrayList;
import java.util.Random;

public class Sac implements Comparable<Sac> {

	/**
	 * Capacité du sac
	 */
	private int capacity;

	/**
	 * Score du sac
	 */
	private int value;

	/**
	 * La meilleur valeur theorique (0 si non renseigner dans le fichier)
	 */
	private int beastValue;

	/**
	 * Nombre de groupe
	 */
	private int nombreItem;

	/**
	 * capacité du sac avec les objet pris actuellement
	 */
	private int capacityActuelle;

	/**
	 * Liste des groupes symbolisant le poid des objet
	 */
	private ArrayList<Groupe> poid;

	/**
	 * Liste des groupes symbolisant le prix des objet
	 */
	private ArrayList<Groupe> cout;

	/**
	 * -1 : on prend rien dans groupe de l'indice courant; 0 : objet indice 0 dans
	 * groupe de l'indice courant; 1 : objet indice 1 dans groupe de l'indice
	 * courant; 2 : objet indice 2 dans groupe de l'indice courant
	 */
	private ArrayList<Integer> choice;

	/**
	 * Inititalise un sac sans aucune donnée
	 */
	public Sac() {
		this.setPoid(new ArrayList<Groupe>());
		this.setCout(new ArrayList<Groupe>());
		this.setChoiceToTake(new ArrayList<Integer>());
		this.setValue(0);
		this.setCapacity(0);
		this.setNombreItem(0);
		this.setCapacityActuelle(0);
	}

	/**
	 * copie le sac en entré
	 * 
	 * @param sac : sac a copié
	 */
	public Sac(Sac sac) {
		setBeastValue(sac.getBeastValue());
		setCapacity(sac.getCapacity());
		setCapacityActuelle(sac.getCapacityActuelle());
		setChoiceToTake(sac.getChoiceToTake());
		setCout(sac.getCout());
		setNombreItem(sac.getNombreItem());
		setPoid(sac.getPoid());
		setValue(sac.getValue());
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getNombreItem() {
		return nombreItem;
	}

	public void setNombreItem(int nombreItem) {
		this.nombreItem = nombreItem;
	}

	public ArrayList<Groupe> getPoid() {
		return poid;
	}

	public void setPoid(ArrayList<Groupe> poid) {
		this.poid = poid;
	}

	public ArrayList<Groupe> getCout() {
		return cout;
	}

	public void setCout(ArrayList<Groupe> cout) {
		this.cout = cout;
	}

	public int getBeastValue() {
		return beastValue;
	}

	public void setBeastValue(int beastValue) {
		this.beastValue = beastValue;
	}

	public ArrayList<Integer> getChoiceToTake() {
		return choice;
	}

	public void setChoiceToTake(ArrayList<Integer> choice) {
		this.choice = choice;
	}

	public int getCapacityActuelle() {
		return capacityActuelle;
	}

	public void setCapacityActuelle(int capacityActuelle) {
		this.capacityActuelle = capacityActuelle;
	}

	/**
	 * Inititalise le tableau des objet que l'on prend a 0 (ne prend aucun objet)
	 */
	public void initTabChoice() {
		this.choice = new ArrayList<Integer>();
		for (int i = 0; i < this.cout.size(); i++) {
			this.choice.add(-1);
		}
	}

	/**
	 * initialise le tableau des objet que l'on prend (prend forcément un objet)
	 */
	public void initTabChoiceRand() {
		this.choice = new ArrayList<Integer>();
		for (int i = 0; i < this.cout.size(); i++) {
			this.choice.add(rand(0, poid.get(i).getTaille()));
		}
	}

	/**
	 * Fonction de mutation
	 */
	public void mutation() {
		int randRes;
		for (int i = 0; i < this.choice.size(); i++) {
			if (this.choice.get(i) == -1) {
				/**
				 * -1 : 75% de chance de muté
				 */
				randRes = rand(-1, poid.get(i).getTaille());
			} else {
				/**
				 * -10 : 30% de chance de muté
				 */
				randRes = rand(-10, poid.get(i).getTaille());
			}
			if (randRes >= -1) {
				this.choice.set(i, randRes);
			}
		}
		updateValue();
		updateCapacityActuelle();
	}

	/**
	 * met a jour la valeur value
	 */
	public void updateValue() {
		int newValue = 0;
		for (int j = 0; j < this.choice.size(); j++) {
			switch (choice.get(j)) {
			case 0:
				newValue = newValue + this.cout.get(j).getListDeTrois().get(0);
				break;
			case 1:
				newValue = newValue + this.cout.get(j).getListDeTrois().get(1);
				break;
			case 2:
				newValue = newValue + this.cout.get(j).getListDeTrois().get(2);
				break;
			}
		}
		this.value = newValue;
	}

	/**
	 * met a jour la valeur capacityActuelle
	 */
	public void updateCapacityActuelle() {
		int newCapacityActuelle = 0;
		for (int j = 0; j < this.choice.size(); j++) {
			switch (choice.get(j)) {
			case 0:
				newCapacityActuelle = newCapacityActuelle + this.poid.get(j).getListDeTrois().get(0);
				break;
			case 1:
				newCapacityActuelle = newCapacityActuelle + this.poid.get(j).getListDeTrois().get(1);
				break;
			case 2:
				newCapacityActuelle = newCapacityActuelle + this.poid.get(j).getListDeTrois().get(2);
				break;
			}
		}
		this.capacityActuelle = newCapacityActuelle;
	}

	/**
	 * fonction qui renvoie un entier aléatoire compris entre [min, max[
	 * 
	 * @param min : inclus
	 * @param max : exclus
	 * @return entier aléatoire
	 */
	public Integer rand(int min, int max) {
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(max - min) + min;
		return nombreAleatoire;
	}

	/**
	 * Calcule la valeur "value" du sac
	 */
	public void calculValue() {
		for (int i = 0; i < choice.size(); i++) {
			this.value = this.value + cout.get(i).getListDeTrois().get(choice.get(i));
			capacityActuelle = capacityActuelle + poid.get(i).getListDeTrois().get(choice.get(i));
		}
	}

	@Override
	public int compareTo(Sac o) {
		/**
		 * pour trier de manière décroissante
		 */
		return o.getValue() - this.value;
	}

}
