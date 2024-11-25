package main.Terrains;


import main.Jeu;
import ressources.Affichage;

public class QG extends ProprieteSpeciale{
	public QG(String terrain,int prop,int x ,int y) {
		super(terrain,prop,x,y);
		setResistance(20);
	}
	@Override
	public void action() {
		if (prop != Jeu.getFL().getUniteMoving().getProp() && uniteSurPropriete()&& estUneUniteAPied()) {
			String[] options = { "Capturer", "Annuler" };
			if (Affichage.popup("Voulez-vous capturer le QG ennemi ?", options, true, 0) == 0) {
				this.setResistance(this.getResistance() - Jeu.getFL().getUniteMoving().getHP());
				System.out.println("Resistance de la ville tombé à " + this.getResistance());
			}
			verifResistance();
		}
	}
	@Override
	protected void verifResistance() {
		if (getResistance() <=0){
			System.out.println("QG Capturé, le joueur est vaincu");
			String[] options = { "Quitter"};
			String vainqueur= "";
			switch (prop) {
			case 1:vainqueur="Bleu";
			case 2:vainqueur="Rouge";
			}
			Affichage.popup(vainqueur + " a gagné la Partie !", options, false, 0);
			Jeu.isOver();
			System.exit(1);
		}
		
	}
}
