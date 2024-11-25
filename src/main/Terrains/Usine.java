package main.Terrains;

import java.util.ArrayList;
import java.util.List;

import main.Coordonnees;
import main.Jeu;
import main.Joueur;
import main.Unite;
import main.Fleche;
import ressources.Affichage;
import ressources.Config;
	

public class Usine  extends ProprieteSpeciale{
	boolean uniteCreeCeTour;
	public Usine(String terrain,int prop,int x,int y) {
		super(terrain,prop,x,y);
		setResistance(20);
		uniteCreeCeTour= false;
	}
	@Override
	public void action() {
		if (prop != Jeu.getIndexJoueurActif() && uniteSurPropriete()&& estUneUniteAPied()) {
			String message = "";
			switch(prop) {
			case 0:message="Voulez-vous capturer cette usine neutre ?";break;
			default:message="Voulez-vous capturer cette usine ennemie ?";break;
			}
			String[] options = { "Capturer", "Annuler" };		
			if (Affichage.popup(message, options, true, 0) == 0) {
				this.setResistance(this.getResistance() - Jeu.getFL().getUniteMoving().getHP());
				System.out.println("Resistance de la ville tombé à " + this.getResistance());
			}
		}
		else if (prop == Jeu.getIndexJoueurActif() && uniteSurPropriete() == false && !Fleche.isFlechefini(Jeu.getFL())) {
			
			Joueur joueur;
			if (Jeu.getIndexJoueurActif() == 1) {
				joueur = Jeu.getJ1();
			}
			else {
				joueur = Jeu.getJ2();
			}
			String[] achetable = Uniteachetable(joueur.getArgent());
			if( achetable.length !=0 ) {
				int res = Affichage.popup("Unités achetables:", achetable, true, 0);
				switch(res) {
					case 0 : ajouterUnite("Infanterie",this.x,this.y,prop);joueur.setArgent(joueur.getArgent()-1500);break;
					case 1 : ajouterUnite("Bazooka",this.x,this.y,prop);joueur.setArgent(joueur.getArgent()-3500);break;
					case 2 : ajouterUnite("Convoit",this.x,this.y,prop);joueur.setArgent(joueur.getArgent()-5000);break;
					case 3 : ajouterUnite("DCA",this.x,this.y,prop);joueur.setArgent(joueur.getArgent()-6000);break;
					case 4 : ajouterUnite("Tank",this.x,this.y,prop);joueur.setArgent(joueur.getArgent()-7000);break;
					case 5 : ajouterUnite("Helico",this.x,this.y,prop);joueur.setArgent(joueur.getArgent()-12000);break;
					case 6 : ajouterUnite("Bombardier",this.x,this.y,prop);joueur.setArgent(joueur.getArgent()-20000);break;
				}
			}
		}
		verifResistance();
	}
	private static void ajouterUnite(String u,int x , int y,int prop) {
		Coordonnees ajout = new Coordonnees(Config.longueurCarteYCases-y-1,x,prop,u);
		Jeu.getCJ().getCarteUniteCoord().add(ajout);
		Jeu.getCJ().getListUnite().add(Unite.trouverAjouterUnite(ajout,u,prop));
		Jeu.getCJ().getListUnite().get(Jeu.getCJ().getListUnite().size()-1).getType().setDispo(false);
	}
	private static String[] Uniteachetable(int money){
		List<String> res = new ArrayList<>();
		if (money>=1500) {
			res.add("Infanterie | Prix : 1500or");
		}
		if (money>=3500) {
			res.add("Bazooka | Prix : 3500or");
		}
		if (money>=5000) {
			res.add("Convoi | Prix : 5000or");
		}
		if (money>=6000) {
			res.add("DCA | Prix : 6000or");
		}
		if (money>=7000) {
			res.add("Tank | Prix : 7000or");
		}
		if (money>=12000) {
			res.add("Helicoptère | Prix : 12000or");
		}
		if (money>=20000) {
			res.add("Bombardier | Prix : 20000or");
		}
		String[] res2 = new String[res.size()];
		return res.toArray(res2);
	}
	@Override
	protected void verifResistance() {
		if (getResistance() <=0){
			System.out.println("Usine Capturé");
			this.prop = Jeu.getFL().getUniteMoving().getProp();
		}
		
	}
	public boolean isUniteCreeCeTour() {
		return uniteCreeCeTour;
	}
	public void swapUniteCreeCeTour(boolean uniteCreeCeTour) {
		if (uniteCreeCeTour) {
			this.uniteCreeCeTour = false;
		}
		else {
			this.uniteCreeCeTour = true;
		}
	}
	
}
