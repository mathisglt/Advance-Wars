package main;

import java.util.*;

import main.troupes.*;
import ressources.Affichage;
import ressources.Config;

public class Unite {
	private TypeUnite type;
	private double HP;
	public int prop;
	private Coordonnees position;
	private Map<Unite, String> voisins;

	public Unite(Coordonnees position, String type, int prop) {
		HP = 10;
		this.position = position;
		this.prop = prop;
		this.voisins = new HashMap<Unite, String>();
		switch (type) {
		case "Infanterie":
			this.type = new Infanterie();
			break;
		case "Bazooka":
			this.type = new Bazooka();
			break;
		case "Tank":
			this.type = new Tank();
			break;
		case "DCA":
			this.type = new DCA();
			break;
		case "Helico":
			this.type = new Helicoptere();
			break;
		case "Convoit":
			this.type = new Convoi();
			break;
		case "Artillerie":
			this.type = new Artillerie();
			break;
		case "Bombardier":
			this.type = new Bombardier();
			break;
		}

	}

	public static Unite trouverAjouterUnite(Coordonnees pos, String uniteName, int prop) {
		Unite nouvelle = new Unite(pos, uniteName, prop);
		return nouvelle;
	}

	@Override
	/*
	 * Affichage correct d'une Unité , affichant son nom , ses PV et sa position
	 * @return une chaine de caractères
	 */
	public String toString() {
		return "Type: " + type.getClass().getSimpleName() + " | PV : " + HP + " | Actuellement sur la case: ("
				+ position.x + "," + position.y + ")";
	}
	/**
	 * Cherche les ennemies attaquables d'une unité , 
	 * celles collés à elle , haut bas gauche droite et d'un numéro d'armée différent
	 */
	public void selectionAttaque() {
		// X et Y de l'unité qui attaque
		int x = this.getPosition().getX();
		int y = this.getPosition().getY();
		
		// Le convoi n'attaque pas 
		if(this.getType().getArme().getClass().getSimpleName().contains("Desarme")) {
			return;	
		}
		// Si une unité dans la liste de toutes les unités du plateau se trouve juxtaposé à notre unité , 
		// elle est ajoutée à sa liste de voisins en clé et sa direction comme valeur
		// Si le voisin est de la même équipe que l'unité qui attaque , on l'enleve de ses voisins
		for (int k = 0; k < Jeu.CJ.getListUnite().size(); k++) {
			if ((x + 1 <= Config.longueurCarteXCases) && (Jeu.CJ.getListUnite().get(k).getPosition().getX() == x + 1)
					&& (Jeu.CJ.getListUnite().get(k).getPosition().getY() == y)) {
				this.voisins.put(Jeu.CJ.listUnite.get(k), "droite");
			}
			if ((x - 1 >= 0) && (Jeu.CJ.getListUnite().get(k).getPosition().getX() == x - 1)
					&& (Jeu.CJ.getListUnite().get(k).getPosition().getY() == y)) {
				this.voisins.put(Jeu.CJ.listUnite.get(k), "gauche");
			}
			if ((y - 1 >= 0) && (Jeu.CJ.getListUnite().get(k).getPosition().getX() == x)
					&& (Jeu.CJ.getListUnite().get(k).getPosition().getY() == y - 1)) {
				this.voisins.put(Jeu.CJ.getListUnite().get(k), "bas");
			}
			if ((y + 1 <= Config.longueurCarteYCases) && (Jeu.CJ.getListUnite().get(k).getPosition().getX() == x)
					&& (Jeu.CJ.getListUnite().get(k).getPosition().getY() == y + 1)) {
				this.voisins.put(Jeu.CJ.getListUnite().get(k), "haut");
			}
		}
		if (this.voisins.size() != 0) {
			// Si il y a des voisins , nous en supprimons ceux qui sont de la même équipe
			// En créeant une nouvelle map semblable dans laquelle nous ajouterons chaque clé
			// qui a un numéro d'armée différent
			Map<Unite, String> newvoisins = new HashMap<>();
			for (Unite cle : this.voisins.keySet()) {
				if (cle.getProp() != this.prop) {
					Jeu.etat.Etatattaque = true;
					newvoisins.put(cle,this.getVoisins().get(cle));
				} 
				else {
				} 
			}
			// Nous remplacons notre liste de voisins par celle démunie des alliés
			this.setVoisins(newvoisins);
			if (this.voisins.size() != 0) {
				// Si il reste des voisins ennemies , nous demandons s'ils veulent attaquer
					String[] optionsatta = { "Attaquer", "Ne pas Attaquer" };
					if (Affichage.popup("Souhaitez-vous attaquer?", optionsatta, true, 0) == 1) {
						Jeu.etat.setEtatattaque(false);
						Jeu.etat.setEtatSelectionAttaque(false);
						Jeu.etat.setUnitedeplace(false);
					}
			}
		}
	}
	/**
	 * Calcul les dégats subis par les 2 unités , l'attaquant attaque en premier
	 * puis l'autre unité se défend
	 * @param unite
	 */
	public void attaque(Unite unite) {
		// On récupère les degats infligables en fonction de l'unité attaquante et defendante
		Double degatsAttaq = this.getType().getArme().getDegats().get(unite.getType().getClass().getSimpleName());
		Double degatsDefen = unite.getType().getArme().getDegats().get(this.getType().getClass().getSimpleName());
		// Calcul des degats 
		// HPDefenseur = HPAttaquant*DegatsAttaquant/100
		// HPAttaquant = HPDefenseur*DegatsDefenseur/100
		Double HpApresAtt = this.getHP() * degatsAttaq/100;
		unite.HP -= HpApresAtt.intValue();
		// Si l'unité à 0 HP ou moins , elle est détruite
		if (unite.getHP() <=0) {
			for (int k = 0; k < Jeu.CJ.getListUnite().size(); k++) {
				if(Jeu.CJ.getListUnite().get(k).getPosition().getX() == unite.getPosition().getX()
						&& Jeu.CJ.getListUnite().get(k).getPosition().getY() == unite.getPosition().getY()) {
					Jeu.CJ.getListUnite().remove(k);
					Jeu.CJ.getCarteUniteCoord().remove(k);
					return;
				}
			}
			
		}
		Double HpApresDef = unite.getHP() * degatsDefen/100;;
		this.HP -= HpApresDef.intValue();
		// Si l'unité à 0 HP ou moins , elle est détruite
		if (this.getHP() <=0) {
			Jeu.CJ.getListUnite().remove(this);
		}
		// Remise à zéro des paramètres gérant une attaque
		Jeu.etat.Etatattaque = false;
		Jeu.etat.EtatSelectionAttaque = false;
		Jeu.etat.uniteEnMouvement = null;
		Jeu.etat.unitedeplace = false;
		Jeu.FL.uniteMoving = null;
	}
	
	// Getters/Setters des attributs nécessaires dans les fonctions/classes auxiliaires
		public Map<Unite, String> getVoisins() {
			return voisins;
		}

		public void setVoisins(Map<Unite, String> voisins) {
			this.voisins = voisins;
		}

		public void setProp(int prop) {
			this.prop = prop;
		}

		public double getHP() {
			return HP;
		}

		public void setHP(double hP) {
			HP = hP;
		}

		public Coordonnees getPosition() {
			return position;
		}

		public void setPosition(Coordonnees position) {
			this.position = position;
		}

		public void setPositionXY(int x, int y) {
			this.position.x = x;
			this.position.y = y;
		}

		public TypeUnite getType() {
			return type;
		}

		public int getProp() {
			return prop;
		}

		public void setType(TypeUnite type) {
			this.type = type;
		}
		
}
