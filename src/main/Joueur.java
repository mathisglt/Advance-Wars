package main;

import java.util.List;

public class Joueur {
	private List<Unite> armee;
	private int argent;
	private int nbProprietes;
	private boolean optionsFinTourAuto;
	public Joueur() {
		super();
		
	}
	// Getters/Setters des attributs nécessaires dans les fonctions/classes auxiliaires
	public List<Unite> getArmee() {
		return armee;
	}
	public void setArmee(List<Unite> armee) {
		this.armee = armee;
	}
	public int getArgent() {
		return argent;
	}
	public void giveArgent(int argentajoute) {
		argent += argentajoute;
	}
	public void setArgent(int argent) {
		this.argent = argent;
	}
	public int getNbProprietes() {
		return nbProprietes;
	}
	public void setNbProprietes(int nbProprietes) {
		this.nbProprietes = nbProprietes;
	}
	public boolean isOptionsFinTourAuto() {
		return optionsFinTourAuto;
	}
	public void setOptionsFinTourAuto(boolean optionsFinTourAuto) {
		this.optionsFinTourAuto = optionsFinTourAuto;
	}

}
