/** package principal */
package main;
import librairies.AssociationTouches;
import librairies.StdDraw;
import ressources.Config;
import ressources.ParseurCartes;
import ressources.Affichage;
import java.lang.String;

public class Jeu {
	//Variables relatives au Jeu
	private static int indexJoueurActif; //l'indice du joueur actif:  1 = rouge, 2 = bleu
	
	// Variables relatives au curseur , son mouvement et au mouvement de la fleche
	protected static Fleche FL = new Fleche() ;
	
	// Variables relatives à la gestion de la carte 
	protected static Carte CJ = new Carte() ; // CJ pour Carte du Jeu , sera une classe très importante
											  // Puisque chargée de stocker tout les tableaux nécessaire au fonctionnement
	
	//Création du curseur avec comme paramètre la fleche et la carte
	protected static Curseur curs = new Curseur();
	
	// Création des 2 joueurs
	protected static Joueur J1 = new Joueur();
	protected static Joueur J2 = new Joueur();
	
	// Etat 
	protected static Etat etat = new Etat();

	
	// l'indice 0 est réservé au neutre, qui ne joue pas mais peut posseder des proprietes
	public Jeu(String fileName) throws Exception {
		//appel au parseur, qui renvoie un tableau de String 
		CJ.carteString = ParseurCartes.parseCarte(fileName);	
		CJ.carteStringCoord = new Coordonnees[CJ.carteString.length][CJ.carteString[0].length];
		Config.setDimension(CJ.carteString[0].length, CJ.carteString.length);
		indexJoueurActif = 1; // rouge commence 
	}
	
	public static boolean isOver() {
		return false;
	}
	/**
	 * Reactualise l'affichage de l'écran 
	 * Utilisé après chaque changement visible
	 */
	public static void display() {	
		StdDraw.clear(); // Remettre à 0 la grille
		if (CJ.debut) {		
			CJ.chargementCarte();
			CJ.verifListUnite();
			CJ.debut =!CJ.debut; // Premier chargement de map
			J1.giveArgent(J1.getNbProprietes()*1000);
			J2.giveArgent(J2.getNbProprietes()*1000);}
		else {CJ.afficheCarte();} // affiche la map
		FL.traceFleche();	// Trace la fleche si elle est activée
		curs.drawGameCursor(Curseur.curseurX,Curseur.curseurY); // Place le curseur sur la case actuelle
		CJ.afficheStatutJeu(); // Affiche le Statut du Jeu en haut de l'écran
		StdDraw.show(); //montre a l'ecran les changement demandes
		

	}

	public void initialDisplay() {
		StdDraw.enableDoubleBuffering(); // rend l'affichage plus fluide: tout draw est mis en buffer et ne s'affiche qu'au prochain StdDraw.show();
		display();
	}
	/**
	 * Retourne le Joueur qui joue
	 * @return Joueur , le joueur en question
	 */
	public Joueur getJoueurActuel() {
		switch(indexJoueurActif) {
		case 1: return J1;
		case 2: return J2;
		}
		return null;
	}
	public void VerifFinAutoTour() {
		if(plusUniteDispo() && getJoueurActuel().isOptionsFinTourAuto()) {
	        String[] options = { "Quitter"};
	        Affichage.popup("Plus d'unite à jouer , changement de tour ", options, false, 0);
	        FinDeTour();
		}
	}
	public void FinDeTour() {
		switch (indexJoueurActif) {
	            case 1 : indexJoueurActif=2; J1.giveArgent(J1.getNbProprietes()*1000);
	            break;
	            case 2 : indexJoueurActif=1;J2.giveArgent(J2.getNbProprietes()*1000); break;
	            }
	            for(int i =0;i<CJ.listUnite.size();i++) {
	                Jeu.CJ.listUnite.get(i).getType().setDispo(true);
	            }
	     FL.resetTour();
	}
	public void UniteJouable() {
        int prop = indexJoueurActif;
        for(int i =0;i<CJ.listUnite.size();i++) {
            if (Jeu.CJ.listUnite.get(i).getProp() == prop && Jeu.CJ.listUnite.get(i).getType().isDispo() == true) {
            	Curseur.setCurseurX(Jeu.getCJ().getListUnite().get(i).getPosition().getX());
            	Curseur.setCurseurY(Jeu.getCJ().getListUnite().get(i).getPosition().getY());
            	return;
            }
        }
        String[] options = {"Quitter"};
        Affichage.popup("Plus aucune unité disponible jouable !" + getJoueurActuel().isOptionsFinTourAuto(), options, false, 0);
		
	}
	public void afficheStats() {
        String[] options = {"Quitter"};
        Affichage.popup("Argent :" +getJoueurActuel().getArgent()+ " NbPropriétés:" + getJoueurActuel().getNbProprietes(), options, false, 0);
		
	}
/**
 * Fonction gérant la pression des touches
 */
	public void update() {
		AssociationTouches toucheSuivante = AssociationTouches.trouveProchaineEntree(); //cette fonction boucle jusqu'a la prochaine entree de l'utilisateur
		//Le principe sera le même pour les 4 touches directionnelles
		// Si la touche est préssée, on vérifie que le déplacement ne dépasse pas les limites de la grille
		if (toucheSuivante.isHaut())   { etat.actionHaut();}	
		if (toucheSuivante.isBas())    { etat.actionBas();}
		if (toucheSuivante.isGauche()) { etat.actionGauche();} 
		if (toucheSuivante.isDroite()) { etat.actionDroite();}
		if (toucheSuivante.isEntree()) { etat.actionEntree();}
		if(toucheSuivante.isEchap())   { etat.actionEchap();}
		else if (toucheSuivante.isCaractere('s')) {
			afficheStats();
		}
		else if (toucheSuivante.isCaractere('u')) {
			UniteJouable();
		}
		else if (toucheSuivante.isCaractere('d')) {
			switch(indexJoueurActif) {
			case 1: J1.setOptionsFinTourAuto(!J1.isOptionsFinTourAuto());break;
			case 2: J2.setOptionsFinTourAuto(!J2.isOptionsFinTourAuto());
			}
			String[] options = {"Quitter"};
	            Affichage.popup("Option changement de tour modifié :" + getJoueurActuel().isOptionsFinTourAuto(), options, false, 0);
		}
		else if (toucheSuivante.isCaractere('t')) {
			String[] options = {"Oui", "Non"};
			if (Affichage.popup("Voulez-vous finir votre tour ?", options, true, 0) == 0) {
				//le choix 0, "Oui", a été selectionné
				FinDeTour();
			}
		}
		VerifFinAutoTour();
		display();
	}
	/**
	 * Vérifie si il reste une unite dispo
	 * @return
	 */
	public boolean plusUniteDispo() {
        int prop = indexJoueurActif;
        for(int i =0;i<CJ.listUnite.size();i++) {
            if (Jeu.CJ.listUnite.get(i).getProp() == prop && Jeu.CJ.listUnite.get(i).getType().isDispo() == true) {
                return false;
            }
        }
        return true;
    }
	// Getters/Setters des attributs nécessaires dans les fonctions/classes auxiliaires
	public static int getIndexJoueurActif() {
		return indexJoueurActif;
	}
	public static int autreJoueur() {
		switch (indexJoueurActif) {
		case 1: return 2;
		case 2: return 1;
		}
		return 0;
	}
	public static Carte getCJ() {
		return CJ;
	}
	public static Joueur getJ1() {
		return J1;
	}
	public static void setJ1(Joueur j1) {
		J1 = j1;
	}
	public static Joueur getJ2() {
		return J2;
	}
	public static void setJ2(Joueur j2) {
		J2 = j2;
	}

	public static Fleche getFL() {
		return FL;
	}
	
}
