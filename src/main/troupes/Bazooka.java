package main.troupes;

import main.MoyenLoc.*;
import main.armes.*;
import ressources.*;

public class Bazooka extends TypeUnite {
	public Bazooka() {
		super();
		setArme(new Canon());
		this.setLoco(new Pied());
		this.prix = 3500;
		this.image =  Chemins.FICHIER_BAZOOKA;
		this.setDepmax(2);
		this.caracteristiques = "Similaire à l’infanterie mais équipé d’un bazooka,"
				+ " rendant l’unité très lente mais efficace contre les unités blindées.";

	}

}
