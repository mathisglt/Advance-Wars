package main.troupes;

import main.MoyenLoc.Chenilles;
import main.armes.*;
import ressources.Chemins;

public class DCA extends TypeUnite{
	public DCA() {
		super();
		setArme(new MitrailleuseLourde());
		this.prix = 6000;
		this.image = Chemins.FICHIER_ANTIAIR;
		this.setDepmax(6);
		this.setLoco(new Chenilles());
		this.caracteristiques = "Abréviation de \"Défense contre l’aviation\","
				+ " cette unité est particulièrement efficace contre les avions "
				+ "et hélicoptères, mais peu efficace contre les blindés.";
	}

}
