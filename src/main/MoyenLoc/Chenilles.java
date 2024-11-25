package main.MoyenLoc;

import java.util.List;

import main.Coordonnees;
import main.Unite;
import ressources.*;

public class Chenilles extends MoyenLoc {

	public Chenilles() {
		setMoyen("Chenilles");
	}

	@Override
	/**
	 * Renvoie si la casesuivante est traversable sous forme d'un booléen en fonction de l'unite qui veut traverser
	 * AIR = traverse tout sans ralentissements
	 * Chenilles = traverse la foret 2 fois moins vite et ne traverse pas les montagnes et l'eau
	 * Pied = traverse la montagne 2 fois moins vite et ne traverse pas l'eau
	 */
	public boolean traversable(String terrain, Coordonnees casesuivante, List<Unite> listUnite, int nous,Unite unitemoving) {
		boolean end = true;
		if (unitemoving.getType().getNbdeplactuel()>=unitemoving.getType().getDepmax()) {
			
			end = false;;
		}
		
		switch (terrain) {
		case Chemins.FICHIER_FORET:unitemoving.getType().addNbdeplactuel(2);break;
		case Chemins.FICHIER_EAU:
		case Chemins.FICHIER_MONTAGNE:end = false;break;
		default:
			unitemoving.getType().addNbdeplactuel(1);
			end = true;
		}
		return end;
	}

}
