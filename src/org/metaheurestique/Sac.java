package org.metaheurestique;

import java.util.ArrayList;

public class Sac {

	private int capacity;

	private int value;
	
	private int beastValue;
	
	private int nombreItem;

	private ArrayList<Integer> poid;
	private ArrayList<Integer> cout;

	public Sac() {
		this.setPoid(new ArrayList<Integer>());
		this.setCout(new ArrayList<Integer>());
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

	public ArrayList<Integer> getPoid() {
		return poid;
	}

	public void setPoid(ArrayList<Integer> poid) {
		this.poid = poid;
	}

	public ArrayList<Integer> getCout() {
		return cout;
	}

	public void setCout(ArrayList<Integer> cout) {
		this.cout = cout;
	}

	public int getBeastValue() {
		return beastValue;
	}

	public void setBeastValue(int beastValue) {
		this.beastValue = beastValue;
	}
	
	

}
