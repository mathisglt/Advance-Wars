package main.MoyenLoc;

import java.util.List;

import main.Coordonnees;
import main.Unite;

public class Air extends MoyenLoc {

	public Air() {
		setMoyen("Air");
	}

	@Override
	public boolean traversable(String terrain,Coordonnees casesuivante, List<Unite> listUnite,int nous,Unite unitemoving) {
		boolean end = true;
		if (unitemoving.getType().getNbdeplactuel()>=unitemoving.getType().getDepmax()) {
			
			end = false;;
		}
		unitemoving.getType().addNbdeplactuel(1);
		return end;
	}

}
