package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import main.Case.Case;
import ressources.Affichage;
import ressources.Chemins;

public class Fleche {
	// changé de protected bool a public static pour etre utilisé dans usine
    protected boolean FlecheActive; // true si une flêche est en cours de tracé , false sinon
	public boolean flechefini = false;
	protected Unite uniteMoving;
	protected Unite uniteAttacking;
	
	protected List<Case> trace_flecheList; //Liste des cases de type Case parcouru par la fleche
	protected Map<Case,String> trace_fleche; //Map des cases de type Case parcouru par la fleche en clé et la direction en valeur
	protected int dirFlecheFinale; // 0 = haut, 1 = bas, 2 = gauche, 3= droite
	protected String derniereDir = "";
	
	/**
	 * Constructeur de la classe Fleche, 
	 * Crée à la nouvelle instance Map trace_fleche et liste trace_flecheList-
	 * gérant à eux deux le chemin parcourue par la flêche
	 */
	public Fleche() {
		super();
		this.trace_flecheList = new ArrayList<Case>();
		this.trace_fleche = new HashMap<Case,String>();
	}
	
	/**
	 * Affichage un bout de fleche en fonction de la direction de début et de fin
	 * @param coordx un int , coordonnée x où afficher
	 * @param coordy un int, coordonnée y où afficher
	 * @param dirdeb  un String direction de départ
	 * @param dirdeb un String , direction de fin
	 */
	public void affichageFleche(int coordx,int coordy,String dirdeb,String dirfin) {
		Affichage.dessineImageDansCase(coordx, coordy, Chemins.getCheminFleche(dirdeb,dirfin));
	}
	
	/**
	 *  Remet à zéro la flêche
	 *   @param none
	 */
	public void resetFleche() {
		Curseur.curseurX = Jeu.getFL().getUniteMoving().getPosition().getX();
		Curseur.curseurY = Jeu.getFL().getUniteMoving().getPosition().getY();
		FlecheActive = false;
		flechefini = false;
		trace_flecheList = new ArrayList<Case>();
		trace_fleche = new HashMap<Case,String>();
	}
	/**
	 *  Remet à zéro la flêche
	 *   @param none
	 */
	public void resetTour() {
		FlecheActive = false;
		flechefini = false;
		uniteMoving = null;
		derniereDir = "";
	}
	/**
	 * Remet à zéro la flêche et replace le curseur sur la première case de la flêche
	 * @param none
	 */
	public void annulerFleche() {	
		Curseur.curseurX = trace_flecheList.get(0).getCoordx();
		Curseur.curseurY = trace_flecheList.get(0).getCoordy();
		trace_flecheList = new ArrayList<Case>();
		trace_fleche = new HashMap<Case,String>();
		flechefini = false;
		FlecheActive = false;
	}
	/**
	 * Affiche correctement à l'écran une flêche en fonction de trace_flecheList
	 */
	public void traceFleche() {
		// Mise en mémoire des différents chemins pour éviter une répétition grossière
		String precedent = Chemins.DIRECTION_DROITE;
		final String droite = Chemins.DIRECTION_DROITE;
		final String gauche = Chemins.DIRECTION_GAUCHE;
		final String bas    = Chemins.DIRECTION_BAS;
		final String haut   = Chemins.DIRECTION_HAUT;
		final String debut  = Chemins.DIRECTION_DEBUT;
		for(int i =0; i<trace_fleche.size();i++) {
			// Une telle gestion des cas est du au fait qu'il existe 1 seule image pour plusieurs cas différents d'apparitions
				int x = trace_flecheList.get(i).getCoordx();
				int y = trace_flecheList.get(i).getCoordy();
				if (trace_fleche.get(trace_flecheList.get(i)) =="droite"){
					// Si la fleche se deplace vers la droite
					// Et si elle est la première , prendre l'image de début vers la droite
					if(i==0) {affichageFleche(x,y, droite,debut); }
					else{
						// Si elle vient du haut ou du bas écrire la fleche du bas/haut vers la droite
						if(precedent==bas || precedent == haut) { affichageFleche(x,y, precedent,droite);}
						// Sinon afficher l'image d'une fleche de la gauche vers la droite
						else {affichageFleche(x,y, gauche,droite);}
					}
					precedent = gauche;
				}
				
				else if (trace_fleche.get(trace_flecheList.get(i)) =="gauche"){
					// Si la fleche se deplace vers la gauche
					// Et si elle est la première , prendre l'image de début vers la gauche
					if(i==0) { affichageFleche(x,y, gauche,debut); }
					else{
						// Si elle vient du haut ou du bas écrire la fleche du bas/haut vers la gauche
						if(precedent==bas || precedent==haut) { affichageFleche(x,y, precedent,gauche); }
						// Sinon afficher l'image d'une fleche de la gauche vers la droite
						else { affichageFleche(x,y, gauche,droite); }
					}
					precedent = droite;
				}
				
				else if (trace_fleche.get(trace_flecheList.get(i))=="haut"){
					// Même processus que les 2 précedents cas
					if(i==0) {	affichageFleche(x,y, haut,debut); }
					else{
						if(precedent==gauche || precedent==droite) { affichageFleche(x,y, precedent,haut);}
						else { affichageFleche(x,y, bas,haut);}
					}
					precedent = bas;
				}
				
				else if (trace_fleche.get(trace_flecheList.get(i)) =="bas"){
					// Même processus que les 3 précedents cas
					if(i==0) {	affichageFleche(x,y, bas,debut); }
					else{
						if(precedent==gauche) { affichageFleche(x,y, bas,gauche);}
						else if(precedent==droite) { affichageFleche(x,y, bas,droite);}
						else { affichageFleche(x,y, bas,haut);}
					}
					precedent = haut;
				}
		}
		
		if (flechefini) {
			// Si la fleche est finie à cette étape , dessiner une fleche finale partant du côté précedent
			Affichage.dessineImageDansCase(Curseur.curseurX, Curseur.curseurY, Chemins.getCheminFleche(derniereDir,"end"));
			return;
		}
		if( trace_fleche.size() == 0 && FlecheActive) {
			// Si la fleche vient de commencer à se former , ne placer qu'un point sur la case actuelle
			Affichage.dessineImageDansCase(Curseur.curseurX, Curseur.curseurY, Chemins.getCheminFleche(debut,"end"));
		}
		if((FlecheActive && trace_fleche.size() >0) || flechefini) {
			// Si l'on trace une fleche ou qu'elle est fini , tracé la fleche sur la position actuelle
			// En prenant en compte la direction précédente et la direction actuelle
			switch (dirFlecheFinale) {
			case 0:Affichage.dessineImageDansCase(Curseur.curseurX, Curseur.curseurY, Chemins.getCheminFleche("down","end"));derniereDir= "down"; break;
			case 1:Affichage.dessineImageDansCase(Curseur.curseurX, Curseur.curseurY, Chemins.getCheminFleche("up","end"));derniereDir= "up";break;
			case 2:Affichage.dessineImageDansCase(Curseur.curseurX, Curseur.curseurY, Chemins.getCheminFleche("right","end"));derniereDir= "right";break;
			case 3:Affichage.dessineImageDansCase(Curseur.curseurX, Curseur.curseurY, Chemins.getCheminFleche("left","end"));derniereDir= "left";break;
			}
		}
	}
	/**
	 * Fonction permettant à partir d'une direction , d'ajouter aux listes trace_fleche
	 * et trace_flecheList la nouvelle coordonnée, si la case a déjà été parcourue par la flêche
	 * les listes sont réduites jusqu'à cette cordonnée , cela évite les boucles
	 * 
	 * @param direction un String indiquant une direction
	 */
	public void cheminFleche(String direction) {
		Case place_actuel = new Case(Curseur.curseurX,Curseur.curseurY);
		// Parcourir toute la liste des coordoonnées de la fleche vérifiant si on y est déjà passé
		for(int j=0;j<trace_flecheList.size();j++) {
			if (trace_flecheList.get(j).getCoordx() == Curseur.curseurX && trace_flecheList.get(j).getCoordy() == Curseur.curseurY) {
				// Si c'est le cas on indique modifie caseAlrUsed et on stocke l'indice 
				// On forme 2 nouvelles listes , qui reprennent toutes les coordonnées de trace_fleche
				// et trace_flecheList jusqu'à l'indice de la case déjà parcourue
				uniteMoving.getType().setNbdeplactuel(j-1);
				List<Case> trace_flecheList2 = new ArrayList<Case>();
				Map<Case,String> trace_fleche2 = new HashMap<Case,String>();
				for(int k=0;k<j;k++) {
					trace_flecheList2.add(trace_flecheList.get(k));
					trace_fleche2.put(trace_flecheList.get(k),trace_fleche.get(trace_flecheList.get(k)));
				}
				trace_flecheList =trace_flecheList2;
				trace_fleche =trace_fleche2;
				Jeu.getFL().traceFleche();
			}
		}
		//Vérifie que l'unite ne dépasse pas sa capacité maximale de déplacement
		if (uniteMoving.getType().getNbdeplactuel()<=uniteMoving.getType().getDepmax()) {
			trace_flecheList.add(place_actuel);
			trace_fleche.put(place_actuel,direction);
		}
		
		// Sinon on fini la flêche.
		else {
			flechefini = true;
		}
	}
	
	/**
	 * Indique si une unité peut se déplacer dans la direction demandée 
	 * @param direction un String
	 * @return un booleen
	 */
	public boolean mouvementUnite(String direction) {
		boolean possible =true;
		Coordonnees casesuivante = null ;
		int vraiY = Jeu.CJ.carteStringCoord[Curseur.curseurY][Curseur.curseurX].getY();
		int vraiX = Jeu.CJ.carteStringCoord[Curseur.curseurY][Curseur.curseurX].getX();
		switch (direction) {
		case "haut": casesuivante = Jeu.CJ.carteStringCoord[vraiY-1][vraiX];break;
		case "bas": casesuivante = Jeu.CJ.carteStringCoord[vraiY+1][vraiX];break;
		case "gauche": casesuivante = Jeu.CJ.carteStringCoord[vraiY][vraiX-1];break;
		case "droite": casesuivante = Jeu.CJ.carteStringCoord[vraiY][vraiX+1];
		}
		int ennemi = 0;
		for (int i =0 ; i<Jeu.CJ.carteUniteCoord.size();i++) {
			if ((Jeu.CJ.carteUniteCoord.get(i).getX() == casesuivante.getX()) && (Jeu.CJ.carteUniteCoord.get(i).getY() == casesuivante.getY())) {
				ennemi = Jeu.CJ.listUnite.get(i).getProp();
			}
		}
		if (ennemi != uniteMoving.getProp() && ennemi !=0 && !getUniteMoving().getType().getLoco().getMoyen().equals("Air") ){
			possible = false;
		}
		if (possible) {
			possible = Jeu.FL.getUniteMoving().getType().getLoco().traversable(casesuivante.terrainImage, casesuivante,Jeu.CJ.listUnite,uniteMoving.getProp(),Jeu.getFL().getUniteMoving());			
		}
		if (possible) {Jeu.FL.cheminFleche(direction);}
		return possible;
		
	}
	// Getters/Setters des attributs nécessaires dans les fonctions/classes auxiliaires
	public Unite getUniteMoving() {
		return uniteMoving;
	}

	public static boolean isFlechefini(Fleche f) {
		return f.flechefini;
	}

	public boolean isFlechefini() {
		return flechefini;
	}

	public void setFlechefini(boolean flechefini) {
		this.flechefini = flechefini;
	}

	public Unite getUniteAttacking() {
		return uniteAttacking;
	}

	public void setUniteAttacking(Unite uniteAttacking) {
		this.uniteAttacking = uniteAttacking;
	}

	public void setUniteMoving(Unite uniteMoving) {
		this.uniteMoving = uniteMoving;
	}
	
	
	
}
