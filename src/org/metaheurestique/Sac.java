package org.metaheurestique;

import java.util.ArrayList;
import java.util.Random;

public class Sac {

	private int capacity;

	private int value;

	private int beastValue;

	private int nombreItem;
	
	private int capacityActuel = 0;

	private int capacityActuelle;

	private ArrayList<Groupe> poid;
	private ArrayList<Groupe> cout;
	/**
	 * -1 : on prend rien dans groupe de l'indice courant; 0 : objet indice 0 dans
	 * groupe de l'indice courant; 1 : objet indice 1 dans groupe de l'indice
	 * courant; 2 : objet indice 2 dans groupe de l'indice courant
	 */
	private ArrayList<Integer> choice;

	public Sac() {
		this.setPoid(new ArrayList<Groupe>());
		this.setCout(new ArrayList<Groupe>());
		this.setChoiceToTake(new ArrayList<Integer>());
		this.setValue(0);
		this.setCapacity(0);
		this.setNombreItem(0);
		this.setCapacityActuelle(0);
	}

	
	
	public int getCapacityActuel() {
		return capacityActuel;
	}



	public void setCapacityActuel(int capacityActuel) {
		this.capacityActuel = capacityActuel;
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

	public void initTabChoice() {
		this.choice = new ArrayList<Integer>();
		for (int i = 0; i < this.cout.size(); i++) {
			this.choice.add(-1);
		}
	}

	public void mutation() {
		for (int i = 0; i < this.choice.size(); i++) {
			this.choice.set(i, rand(-1, poid.get(i).getTaille()-1));
		}
		updateValue();
		updateCapacityActuelle();
	}

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

	public Integer rand(int min, int max) {
		Random rand = new Random();
		int nombreAleatoire = rand.nextInt(max - min + 1) + min;
		return nombreAleatoire;
	}
	
	public void calculValue()
	{
		for(int i = 0;i<choice.size();i++ )
		{
			this.value = this.value + cout.get(i).getListDeTrois().get(choice.get(i));
			capacityActuel = capacityActuel + poid.get(i).getListDeTrois().get(choice.get(i));
		}
	}

}
