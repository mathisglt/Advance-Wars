package main.Terrains;

import ressources.Chemins;

public abstract class Terrain {
	String terrainImage;
	int x;
	int y;
	int prop;
	/**
	 * Constructeur du type Terrain
	 * @param terrain
	 * @param x
	 * @param y
	 */
	public Terrain(String terrain,int x , int y) {
		switch (terrain) {
		case "city.png":this.terrainImage = Chemins.FICHIER_VILLE;break;
		case "hq.png":this.terrainImage = Chemins.FICHIER_QG;break;
		case "factory.png":this.terrainImage = Chemins.FICHIER_USINE;break;
		case "Plaine":this.terrainImage = Chemins.FICHIER_PLAINE;break;
		case "Foret":this.terrainImage = Chemins.FICHIER_FORET;break;
		case "Eau":this.terrainImage = Chemins.FICHIER_EAU;break;
		case "Montagne":this.terrainImage = Chemins.FICHIER_MONTAGNE;break;
		}
		this.x = x;
		this.y = y;
	}
	// Getters & Setters
	public String getTerrainImage() {
		return terrainImage;
	}
	public void setTerrainImage(String terrainImage) {
		this.terrainImage = terrainImage;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getProp() {
		return prop;
	}
	public void setProp(int prop) {
		this.prop = prop;
	}
	
	
}
