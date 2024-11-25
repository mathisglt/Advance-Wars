package main.troupes;

import main.MoyenLoc.*;
import main.armes.Mortier;
import ressources.Chemins;

public class Artillerie extends TypeUnite {
	public Artillerie() {
		super();
		setArme(new Mortier());
		this.setLoco(new Chenilles());
		this.prix = 6000;
		this.image =  Chemins.FICHIER_ARTILLERIE;
		this.setDepmax(5);
		this.caracteristiques = "Attaque à distance, ce qui la rend particulièrement efficace défensivement. Vulnérable aux\r\n"
				+ "assauts de front (ne fait pas partie du projet de base, cf. partie 6).";
	}
}
