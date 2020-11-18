package org.metaheurestique;

import java.util.ArrayList;

public class Sac {

	private int capacity;

	private int value;

	private int beastValue;

	private int nombreItem;

	private ArrayList<Groupe> poid;
	private ArrayList<Groupe> cout;
	private ArrayList<Integer> choice;

	public Sac() {
		this.setPoid(new ArrayList<Groupe>());
		this.setCout(new ArrayList<Groupe>());
		this.setChoiceToTake(new ArrayList<Integer>());
		this.setValue(0);
		this.setCapacity(0);
		this.setNombreItem(0);
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

	public void initTabChoice() {
		this.choice = new ArrayList<Integer>();
		for (int i = 0; i < this.cout.size(); i++) {
			this.choice.add(-1);
		}
	}

}
