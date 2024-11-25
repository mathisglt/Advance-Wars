package main.troupes;

import main.MoyenLoc.*;
import main.armes.*;
import ressources.Chemins;

public class Convoi extends TypeUnite {
	public Convoi() {
		super();
		this.prix = 5000;
		this.image =  Chemins.FICHIER_GENIE;
		setArme(new Desarme());
		this.setDepmax(6);
		this.setLoco(new Chenilles());
		this.caracteristiques = "Ne peut pas attaquer mais possède des capacités spéciales (ne fait pas partie du projet de\r\n"
				+ "base, cf. partie 6).\r\n";
	}
}
