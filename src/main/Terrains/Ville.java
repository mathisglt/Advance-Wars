package main.Terrains;

import main.Jeu;
import ressources.Affichage;

public class Ville extends ProprieteSpeciale{
	public Ville(String terrain,int prop,int x ,int y) {
		super(terrain,prop,x,y);
		setResistance(20);
	}
	@Override
	public void action() {
		if (prop != Jeu.getFL().getUniteMoving().getProp() && uniteSurPropriete()&& estUneUniteAPied()) {
		String message = "";
		switch(prop) {
		case 0:message="Voulez-vous capturer cette ville neutre ?";break;
		default:message="Voulez-vous capturer cette ville ennemie ?";break;
		}
		String[] options = { "Capturer", "Annuler" };		
		if (Affichage.popup(message, options, true, 0) == 0) {
			this.setResistance(this.getResistance() - Jeu.getFL().getUniteMoving().getHP());
			System.out.println("Resistance de la ville tombé à " + this.getResistance());
		}
		verifResistance();
		}
	}
	@Override
	protected void verifResistance() {
		if (getResistance() <=0){
			System.out.println("Ville Capturé");
			this.prop = Jeu.getFL().getUniteMoving().getProp();
		}
		
	}
}
