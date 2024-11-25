package main.troupes;

import main.MoyenLoc.Chenilles;
import main.armes.*;
import ressources.Chemins;

public class Tank extends TypeUnite{
	public Tank() {
		super();
		setArme(new Canon());
		this.prix = 7000;
		this.image = Chemins.FICHIER_TANK;
		this.setDepmax(6);
		this.setLoco(new Chenilles());
		this.caracteristiques= "L’unité offensive par excellence, rapide, robuste et puissante.\n"
				+ " Fortement ralenti par les terrains accidenté,"
				+ "il est également vulnérable aux attaques à distance et aux unités aériennes.";
		
	}

}
