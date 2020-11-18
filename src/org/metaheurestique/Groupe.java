package org.metaheurestique;

import java.util.ArrayList;

public class Groupe {

	private ArrayList<Integer> listDeTrois;

	Groupe() {
		this.setListDeTrois(new ArrayList<Integer>(3));
	}

	public ArrayList<Integer> getListDeTrois() {
		return listDeTrois;
	}

	public void setListDeTrois(ArrayList<Integer> listDeTrois) {
		this.listDeTrois = listDeTrois;
	}

}
