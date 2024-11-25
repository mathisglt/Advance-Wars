package main.troupes;

import main.MoyenLoc.MoyenLoc;
import main.armes.*;

public abstract class TypeUnite{
	private Arme arme;
	private MoyenLoc loco;
	
	int prix;
	private int depmax;
	private int nbdeplactuel;
	
	int prop;
	String image;
	boolean dispo;
	String caracteristiques;
	
	/**
	 * Constructeur du TypeUnite , mettant par défaut son paramètre dispo à vrai
	 */
	public TypeUnite() {
		this.dispo = true;
		nbdeplactuel =0;
	}
	// Getters/Setters des attributs nécessaires dans les fonctions/classes auxiliaires
	public int getNbdeplactuel() {
		return nbdeplactuel;
	}
	public void setNbdeplactuel(int nbdeplactuel) {
		this.nbdeplactuel = nbdeplactuel;
	}
	public void addNbdeplactuel(int nbdepl) {
		this.nbdeplactuel += nbdepl;
	}
	
	public int getDepmax() {
		return depmax;
	}
	public void setDepmax(int depmax) {
		this.depmax = depmax;
	}
	public void UniteUtilise() {
		this.dispo = false;
	}
	public Arme getArme() {
		return arme;
	}
	public int getPrix() {
		return prix;
	}
	public int getProp() {
		return prop;
	}
	public String getImage() {
		return image;
	}
	public boolean isDispo() {
		return dispo;
	}
	public String getCaracteristiques() {
		return caracteristiques;
	}
	public void setArme(Arme arme) {
		this.arme = arme;
	}
	public void setPrix(int prix) {
		this.prix = prix;
	}
	public void setProp(int prop) {
		this.prop = prop;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setDispo(boolean dispo) {
		this.dispo = dispo;
	}
	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques = caracteristiques;
	}
	public MoyenLoc getLoco() {
		return loco;
	}
	public void setLoco(MoyenLoc loco) {
		this.loco = loco;
	}
	
}
