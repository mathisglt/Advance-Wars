package main;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import main.Terrains.ProprieteSpeciale;
import main.Terrains.Terrain;
import ressources.Affichage;
import ressources.Config;

public class Etat {
	boolean unitedeplace = false;
	boolean Etatattaque = false;
	boolean EtatSelectionAttaque = false;
	int AttaquantX;
	int AttaquantY;
	Unite uniteEnMouvement;

	/**
	 * Fonction gérant la préssion de la touche Haut de la classe Jeu
	 * 
	 * @param none
	 */
	public void actionHaut() {
		boolean unitePeutTraverser = true;

		// Si après déplacement , l'unite ne sort pas de la grille
		if (Curseur.curseurY < Config.longueurCarteYCases - 1) {
			// Si l'unite attaque
			if (Etatattaque && uniteEnMouvement != null) {
				for (Unite cle : uniteEnMouvement.getVoisins().keySet()) {
					// Si un de ses voisins attaquable est en haut d'elle
					if (uniteEnMouvement.getVoisins().get(cle) == "haut") {
						// On y déplace le curseur , EtatSelectionAttaque est mis à vrai
						// Et les coordonnées de l'unité attaquante sont stockés
						Curseur.curseurY++;
						EtatSelectionAttaque = true;
						AttaquantX = uniteEnMouvement.getPosition().getX();
						AttaquantY = uniteEnMouvement.getPosition().getY();
					}
				}
			}
			// Si Une fleche est active , on vérifie sa possibilité de bouger avec la
			// fonction mouvementUnite
			if (Jeu.FL.FlecheActive) {
				unitePeutTraverser = Jeu.FL.mouvementUnite("haut");
			}
			// Si nous sommes en navigation libre , on déplace le curseur vers le haut
			if (Fleche.isFlechefini(Jeu.getFL()) == false && unitePeutTraverser && !Etatattaque && !EtatSelectionAttaque) {
				Curseur.curseurY++;
			}
		}
		// Si une fleche est active et qu'on s'y est déplacé , on modifie
		// dirFlecheFinale à 0
		// Utile dans Fleche.tracefleche()
		if (unitePeutTraverser) {
			Jeu.FL.dirFlecheFinale = 0;
		}
		// Si nous séléctionnons une unité à attaquer , Etatattaque passe à false
		if (EtatSelectionAttaque) {
			Etatattaque = false;
		}
	}

	/**
	 * Fonction gérant la préssion de la touche Bas de la classe Jeu
	 * 
	 * @param none
	 */
	public void actionBas() {
		// Même fonction que actionHaut en prenant en compte que nous nous déplacons
		// vers le bas
		// Voir actionHaut pour en comprendre le principe

		boolean unitePeutTraverser = true;
		if (Curseur.curseurY > 0) {
			if (Etatattaque && uniteEnMouvement != null) {
				for (Unite cle : uniteEnMouvement.getVoisins().keySet()) {
					if (uniteEnMouvement.getVoisins().get(cle) == "bas") {
						Curseur.curseurY--;
						EtatSelectionAttaque = true;
						AttaquantX = uniteEnMouvement.getPosition().getX();
						AttaquantY = uniteEnMouvement.getPosition().getY();
					}
				}
			}
			if (Jeu.FL.FlecheActive) {
				unitePeutTraverser = Jeu.FL.mouvementUnite("bas");
			}
			if (Fleche.isFlechefini(Jeu.getFL()) == false && unitePeutTraverser && !Etatattaque && !EtatSelectionAttaque) {
				Curseur.curseurY--;
			}
		}
		if (unitePeutTraverser) {
			Jeu.FL.dirFlecheFinale = 1;
		}
		if (EtatSelectionAttaque) {
			Etatattaque = false;
		}
	}

	/**
	 * Fonction gérant la préssion de la touche Gauche de la classe Jeu
	 * 
	 * @param none
	 */
	public void actionGauche() {
		// Même fonction que actionHaut en prenant en compte que nous nous déplacons
		// vers la gauche
		// Voir actionHaut pour en comprendre le principe
		boolean unitePeutTraverser = true;
		if (Curseur.curseurX > 0) {
			if (Etatattaque && uniteEnMouvement != null) {
				for (Unite cle : uniteEnMouvement.getVoisins().keySet()) {
					if (uniteEnMouvement.getVoisins().get(cle) == "gauche") {
						Curseur.curseurX--;
						EtatSelectionAttaque = true;
						AttaquantX = uniteEnMouvement.getPosition().getX();
						AttaquantY = uniteEnMouvement.getPosition().getY();
					}
				}
			}
			if (Jeu.FL.FlecheActive) {
				unitePeutTraverser = Jeu.FL.mouvementUnite("gauche");
			}
			if (Jeu.FL.flechefini == false && unitePeutTraverser && !Etatattaque && !EtatSelectionAttaque) {
				Curseur.curseurX--;
			}
		}
		if (unitePeutTraverser) {
			Jeu.FL.dirFlecheFinale = 2;
		}
		if (EtatSelectionAttaque) {
			Etatattaque = false;
		}
	}

	/**
	 * Fonction gérant la préssion de la touche Droite de la classe Jeu
	 * 
	 * @param none
	 */
	public void actionDroite() {
		// Même fonction que actionHaut en prenant en compte que nous nous déplacons
		// vers la droite
		// Voir actionHaut pour en comprendre le principe
		boolean unitePeutTraverser = true;
		if (Curseur.curseurX < Config.longueurCarteXCases - 1) {
			if (Etatattaque && uniteEnMouvement != null) {
				for (Unite cle : uniteEnMouvement.getVoisins().keySet()) {
					if (uniteEnMouvement.getVoisins().get(cle) == "droite") {
						Curseur.curseurX++;
						EtatSelectionAttaque = true;
						AttaquantX = uniteEnMouvement.getPosition().getX();
						AttaquantY = uniteEnMouvement.getPosition().getY();
					}
				}
			}
			if (Jeu.FL.FlecheActive) {
				unitePeutTraverser = Jeu.FL.mouvementUnite("droite");
			}
			if (Jeu.FL.flechefini == false && unitePeutTraverser && !Etatattaque && !EtatSelectionAttaque) {
				Curseur.curseurX++;
			}
		}
		if (unitePeutTraverser) {
			Jeu.FL.dirFlecheFinale = 3;
		}
		if (EtatSelectionAttaque) {
			Etatattaque = false;
		}
	}

	/**
	 * Fonction gérant la pression de la touche Entrée de la classe Jeu
	 * 
	 * @param none
	 */
	public void actionEntree() {
		int indice_uniteIci = -1;
		uniteEnMouvement = null;
		List<Terrain> ProprieteSelect = Jeu.CJ.getListPropriete().stream().filter(e -> ((ProprieteSpeciale) e).getX() == Curseur.curseurX
				&& ((ProprieteSpeciale) e).getY() == Curseur.curseurY)
				.collect(Collectors.toList());
		ProprieteSpeciale selectionné = null;
		
		// Cherche l'unite sur laquelle se repose actuellement le curseur
		if (Jeu.CJ.listUnite != null) {
			for (int i = 0; i < Jeu.CJ.listUnite.size(); i++) {
				if (Jeu.CJ.listUnite.get(i).getPosition().x == Curseur.curseurX
						&& Jeu.CJ.listUnite.get(i).getPosition().y == Curseur.curseurY) {
					// On en récupère son indice
					indice_uniteIci = i;
				}
			}
		}
		
		// Si nous sélectionnons l'unité à attaquer
		if (!Etatattaque && EtatSelectionAttaque) {
			// Attaquer avec cette unité , puis remettre à zéro les paramètres d'attaque
			// Puis sortir de la fonction
			uniteEnMouvement = Jeu.FL.uniteAttacking;
			uniteEnMouvement.attaque(Jeu.CJ.getListUnite().get(indice_uniteIci));
			Jeu.FL.uniteAttacking.setVoisins(new HashMap<Unite, String>());
			EtatSelectionAttaque = false;
			Etatattaque = false;
			Jeu.FL.uniteAttacking = null;
			return;
		}
		
		// Vérifie si l'unité est déjà utilisée ou du mauvais camp
		if (indice_uniteIci != -1) {
			if (Jeu.CJ.listUnite.get(indice_uniteIci).getProp() != Jeu.getIndexJoueurActif() && !Jeu.FL.FlecheActive) {
				System.out.println("L'unité séléctionnée est du mauvais camp ");
				return;
			}
			if (Jeu.CJ.listUnite.get(indice_uniteIci).getType().isDispo() == false && Jeu.FL.FlecheActive == false) {
				System.out.println("Unité au repos pour cette manche");
				return;
			}
		}
		// Si nous sommes sur une case vide mais que l'on est en train d'en déplacer une Unite
		// , on la stock ici
		if (Jeu.FL.uniteMoving != null) {
			uniteEnMouvement = Jeu.FL.uniteMoving;
		}
		// Si nous ne déplacons pas d'unites , et que nous ne cliquons pas sur une case avec unité
		// Nous effectuons l'action de l'usine si s'en est une
		if (indice_uniteIci == -1 && Jeu.FL.uniteMoving == null) {
			if (ProprieteSelect.size() !=0 && !EtatSelectionAttaque && !Etatattaque ) {
					selectionné = (ProprieteSpeciale) ProprieteSelect.get(0);
					if(selectionné.getClass().getSimpleName().equals("Usine")){
						if (selectionné.getProp() == Jeu.getIndexJoueurActif() || selectionné.uniteSurPropriete()) {
							selectionné.action() ;
						}
					}
			}
			return;
		}
		// Si déplacons une unite
		if (uniteEnMouvement != null) {
			// Vérifie si elle peut bouger et le fait si elle le peut
			uniteBouge(indice_uniteIci);
			// Initialise uniteAttacking
			Jeu.getFL().setUniteAttacking(Jeu.getFL().getUniteMoving());
			// Si l'unite a bougé 
			if (unitedeplace) {
				// On vérifie ensuite si elle peut attaquer un de ses 4 voisins
				Jeu.FL.uniteAttacking.selectionAttaque();
				
				// Si elle est sur une Propriété et que nous n'attaquons pas
				if (ProprieteSelect.size() !=0 && !EtatSelectionAttaque && !Etatattaque) {			
					// La propriété spéciale sur laquelle nous nous tenons est stocké dans selectionné
					selectionné = (ProprieteSpeciale) ProprieteSelect.get(0);
					// Si , elle n'est pas de notre camp, on effectue son action (Capturer) 
					if(!selectionné.getClass().getSimpleName().equals("Usine") 
							|| (selectionné.getClass().getSimpleName().equals("Usine") && selectionné.getProp() != Jeu.getIndexJoueurActif() )){
						if (selectionné.getProp() != Jeu.getIndexJoueurActif()) {
							selectionné.action();
							Jeu.getFL().setUniteMoving(null);
							unitedeplace = false;
							
						}
					}		
				}	
				// Disponibilité mise à faux après l'action 
			uniteEnMouvement.getType().setDispo(false);
			Jeu.getFL().setUniteMoving(null);
			}
		} 
		else if (Jeu.CJ.listUnite.get(indice_uniteIci).getType().isDispo()) {
			uniteBouge(indice_uniteIci);
		}
		
	}

	private void uniteBouge(int indice_uniteIci) {
		String[] options = { "Se Déplacer", "Annuler" };
		// Cas 1 Pas de fleche en cours mais une Unite sur la case
		if (!Jeu.FL.FlecheActive && indice_uniteIci != -1) {
			Jeu.FL.FlecheActive = true;
			Jeu.FL.uniteMoving = Jeu.CJ.listUnite.get(indice_uniteIci);
		}
		// Cas 2 Fleche en cours mais pas d'Unite > Mouvement de l'Unite sur la case
		else if (Jeu.FL.FlecheActive && indice_uniteIci == -1) {
			if (Affichage.popup("Confirmer le déplacement ?", options, true, 0) == 0) {
				Jeu.FL.uniteMoving.setPositionXY(Curseur.curseurX, Curseur.curseurY);
				Jeu.FL.traceFleche();
				Jeu.FL.resetFleche();
				unitedeplace = true;
			} else {
				Jeu.FL.resetFleche();
				unitedeplace = false;
			}
		}

		// Cas 3 Fleche en cours et Unite sur la case
		else if (Jeu.FL.FlecheActive && indice_uniteIci != -1) {
			int indice_libre = 0;
			boolean place_trouve = false;
			boolean retour_PDD = false;
			// Si Nous retournons au même endroit , ne rien faire
			// Affichage de la PopUp pour confirmer le déplacement
			if (Affichage.popup("Confirmer le déplacement ?", options, true, 0) == 0) {
				if ((Jeu.FL.trace_flecheList.size() == 0)
						|| (Jeu.FL.trace_flecheList.get(0).getCoordx() == Curseur.curseurX
								&& Jeu.FL.trace_flecheList.get(0).getCoordy() == Curseur.curseurY)) {
					retour_PDD = true;
				}

				if (!retour_PDD) {
					// Parcourir la Liste de coordonnées de la fleche de la fin vers le début
					for (int j = Jeu.FL.trace_flecheList.size() - 1; j >= 0 && !place_trouve; j--) {
						boolean existe = false;
						// Parcourir la liste des Unites
						for (int k = 0; k < Jeu.CJ.listUnite.size(); k++) {
							// On cherche si parmi toutes les Unites, une possede les mêmes coordonnes que
							// cette case de la fleche
							if (Jeu.FL.trace_flecheList.get(j).getCoordx() == Jeu.CJ.listUnite.get(k).getPosition()
									.getX()
									&& Jeu.FL.trace_flecheList.get(j).getCoordy() == Jeu.CJ.listUnite.get(k)
											.getPosition().getY()) {
								existe = true;
							}
						}
						// Si ce n'est pas le cas , on récupère son indice et stop toutes les boucles
						if (!existe) {
							indice_libre = j;
							place_trouve = true;
						}
					}
					if (!place_trouve) {
						indice_libre = 0;
					}

					// On positionne l'Unite à la case la plus proche possible de ce que veut
					// l'utilisateur avec l'indice_libre trouvé avant
					// Si aucune place libre n'a été trouvé sur le chemin il revient à sa place
					// originelle
					Jeu.FL.uniteMoving.setPositionXY(Jeu.FL.trace_flecheList.get(indice_libre).getCoordx(),
							Jeu.FL.trace_flecheList.get(indice_libre).getCoordy());
					Curseur.setCurseurX(Jeu.FL.trace_flecheList.get(indice_libre).getCoordx());
					Curseur.setCurseurY(Jeu.FL.trace_flecheList.get(indice_libre).getCoordy());
					// On remet à zéro tout les paramètrès liés à la fleche
					// Ce qui réinitiallise la fleche et la fait par conséquent disparaitre de
					// l'écran
				}
				Jeu.FL.resetFleche();
				unitedeplace = true;
			} else {
				Jeu.FL.resetFleche();;
				Jeu.FL.uniteMoving = null;
				unitedeplace = false;
			}
		}
		Jeu.getFL().getUniteMoving().getType().setNbdeplactuel(0);
	}

	/**
	 * Fonction gérant la préssion de la touche Echap de la classe Jeu
	 * 
	 * @param none
	 */
	public void actionEchap() {
		// Si nous sommes en train de sélectionner une unité à attaquer
		if (EtatSelectionAttaque || Etatattaque) {
			// Placer le curseur au niveau de l'unité à attaquer
			Curseur.curseurX = AttaquantX;
			Curseur.curseurY = AttaquantY;
			// Remettre les Etats liés à l'attaque à false
			Etatattaque = false;
			EtatSelectionAttaque = false;
			// Recherche les voisins de l'unité qui attaque
			Jeu.FL.uniteAttacking.selectionAttaque();
		}
		// Si une fleche est en train d'être tracée
		if (Jeu.FL.FlecheActive) {
			// On annule la flêche , ses paramètres et replace le curseur
			if (Jeu.FL.trace_flecheList.size()!=0) {Jeu.FL.annulerFleche();}
			else{Jeu.FL.resetFleche();}
			// L'unité qui était en mouvement reste disponible
			Jeu.FL.uniteMoving.getType().setDispo(true);
			;
			Jeu.FL.uniteMoving = null;
		}

	}

	// Getters/Setters des attributs nécessaires dans les fonctions/classes
	// auxiliaires
	public void setEtatSelectionAttaque(boolean etatSelectionAttaque) {
		EtatSelectionAttaque = etatSelectionAttaque;
	}

	public boolean isEtatSelectionAttaque() {
		return EtatSelectionAttaque;
	}

	public boolean isUnitedeplace() {
		return unitedeplace;
	}

	public void setUnitedeplace(boolean unitedeplace) {
		this.unitedeplace = unitedeplace;
	}

	public boolean isEtatattaque() {
		return Etatattaque;
	}

	public void setEtatattaque(boolean etatattaque) {
		Etatattaque = etatattaque;
	}
}
