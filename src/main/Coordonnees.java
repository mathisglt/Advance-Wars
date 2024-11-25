package main;
import main.Terrains.*;
import ressources.*;


public class Coordonnees {
	int x;
	int y;
	int prop;
	Terrain terrain;
	String terrainImage;
	
	String nameunite;
	Unite unite;
	String uniteImage;

	/**
	 * Créer une Coordonnees simple sans unité
	 * @param i
	 * @param j
	 * @param terrain
	 */
	public Coordonnees(int i,int j,String terrain) {
		this.x = j;
		this.y = Config.longueurCarteYCases-i-1;
		trouverTerrain(terrain,prop,this.x,this.y);
		this.nameunite = null;
	}
	/**
	 * Créer une Coordonnees avec propriété sans unité
	 * @param i, la coordonnée x
	 * @param j, la coordonnée y
	 * @param terrain, un string du nom du terrain
	 * @param prop, int représentant le numéro du joueur
	 */
	public Coordonnees(int i,int j,String terrain ,int prop) {
		this.x = j;
		this.y = Config.longueurCarteYCases-i-1;
		trouverTerrain(terrain,prop,this.x,this.y);
		this.prop=prop;
		this.nameunite = null ;
	}
	/**
	 * Créer une Coordonnée avec unité
	 * @param i, la coordonnée x
	 * @param j, la coordonnée y
	 * @param prop, int représentant le numéro du joueur
	 * @param unite , le nom de l'unite
	 */
	public Coordonnees(int i,int j,int prop, String unite) {
		this.x = j;
		this.y = Config.longueurCarteYCases-i-1;
		trouverUnite(unite);
		this.nameunite = unite;
		this.prop=prop;

	}
	/**
	 * Renvoie le nom du chemin de l'unite correspondant au string donné en paramètre
	 * @param unite
	 */
	public void trouverUnite(String unite) {
		switch (unite) {
		case "Infanterie":this.uniteImage = Chemins.FICHIER_INFANTERIE;break;
		case "Bazooka":this.uniteImage = Chemins.FICHIER_BAZOOKA;break;
		case "Tank":this.uniteImage = Chemins.FICHIER_TANK;break;
		case "DCA":	this.uniteImage = Chemins.FICHIER_ANTIAIR;break;
		case "Helico":this.uniteImage = Chemins.FICHIER_HELICOPTERE;break;
		case "Convoit":this.uniteImage = Chemins.FICHIER_GENIE;break;
		case "Artillerie":	this.uniteImage = Chemins.FICHIER_ARTILLERIE;break;
		case "Bombardier": this.uniteImage = Chemins.FICHIER_BOMBARDIER;break;
		}

	}
	/**
	 * A partir des paramètrs , créer le Terrain  et initialise son paramètre terrainImage en fonction de son nom
	 * @param terrain
	 * @param prop
	 * @param x
	 * @param y
	 */
	public void trouverTerrain(String terrain,int prop,int x,int y) {
		switch (terrain) {
		case "Ville":this.terrain = new Ville(terrain,prop,x,y);this.terrainImage = Chemins.FICHIER_VILLE;break;
		case "QG":this.terrain = new QG(terrain,prop,x,y);this.terrainImage = Chemins.FICHIER_QG;break;
		case "Usine":this.terrain = new Usine(terrain,prop,x,y);this.terrainImage = Chemins.FICHIER_USINE;break;
		case "Plaine":this.terrain = new Plaine(terrain,x,y);this.terrainImage = Chemins.FICHIER_PLAINE;break;
		case "Foret":this.terrain = new Foret(terrain,x,y);this.terrainImage = Chemins.FICHIER_FORET;break;
		case "Eau":this.terrain = new Eau(terrain,x,y);this.terrainImage = Chemins.FICHIER_EAU;break;
		case "Montagne":this.terrain = new Montagne(terrain,x,y);this.terrainImage = Chemins.FICHIER_MONTAGNE;break;
		}
	}

	// Getters/Setters des attributs nécessaires dans les fonctions/classes auxiliaires
	public int getProp() {
		return prop;
	}

	public void setProp(int prop) {
		this.prop = prop;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
