package main.troupes;

import main.MoyenLoc.*;
import main.armes.*;
import ressources.Chemins;
public class Infanterie extends TypeUnite {
	public Infanterie() {
		super();
		setArme(new MitrailleuseLegere());
		this.prix = 1500;
		this.image = Chemins.FICHIER_INFANTERIE;
		this.setDepmax(3);
		this.setLoco(new Pied());
		this.caracteristiques = "Unité la plus fragile du jeu, mais qui peut capturer les propriétés adverses. "
				+ "Déplacements plutôt lents mais compatibles avec des terrains accidentés. Peu efficace en combat.";
	}
}
