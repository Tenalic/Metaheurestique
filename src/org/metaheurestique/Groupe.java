package org.metaheurestique;

import java.util.ArrayList;

public class Groupe {

	private ArrayList<Integer> listDeTrois;

	private int taille;

	Groupe() {
		this.setListDeTrois(new ArrayList<Integer>(3));
		setTaille(3);
	}

	Groupe(int taille) {
		this.setListDeTrois(new ArrayList<Integer>(3));
		setTaille(taille);
	}

	public ArrayList<Integer> getListDeTrois() {
		return listDeTrois;
	}

	public void setListDeTrois(ArrayList<Integer> listDeTrois) {
		this.listDeTrois = listDeTrois;
	}

	public int getTaille() {
		return taille;
	}

	public void setTaille(int taille) {
		this.taille = taille;
	}

}
