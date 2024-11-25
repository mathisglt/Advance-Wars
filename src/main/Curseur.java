package main;
import ressources.Affichage;

public class Curseur {
	// Il n'existera qu'un seul et unique Curseur , alors ses paramètres
	// seront static pour permettre d'être vu et modifiés par toutes les classes
	
	protected static int curseurX; // coordonnée X du curseur dans la grille
	protected static int curseurY; // coordonnée Y du curseur dans la grille
	
	
	public void drawGameCursor(int x , int y) {
		Affichage.dessineCurseur(x, y); //affiche le curseur en (x,y), a modifier
	}
	
	/*
	 * Constructeur du Curseur , prenant en parametre une fleche et une carte
	 */
	public Curseur() {
	}
	
	// Getters/Setters des attributs nécessaires dans les fonctions/classes auxiliaires
	public static int getCurseurX() {
		return curseurX;
	}
	public static void setCurseurX(int curseurX) {
		Curseur.curseurX = curseurX;
	}
	public static int getCurseurY() {
		return curseurY;
	}
	public static void setCurseurY(int curseurY) {
		Curseur.curseurY = curseurY;
	}
	
}
