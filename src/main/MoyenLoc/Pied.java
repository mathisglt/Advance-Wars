package main.MoyenLoc;

import java.util.List;

import main.Coordonnees;
import main.Unite;
import ressources.Chemins;

public class Pied extends MoyenLoc{

	public Pied() {
		setMoyen("Pied");
	}

	@Override
	public boolean traversable(String terrain, Coordonnees casesuivante, List<Unite> listUnite, int nous,Unite unitemoving) {
		boolean end = true;
		switch (terrain) {
		case Chemins.FICHIER_MONTAGNE:unitemoving.getType().addNbdeplactuel(2);
		case Chemins.FICHIER_EAU:end = false;break;
		default:
			unitemoving.getType().addNbdeplactuel(1);
			end = true;
		}
		return end;
	}

	
}
