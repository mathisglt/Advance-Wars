package main.armes;

import java.util.HashMap;
import java.util.Map;


public abstract class Arme {
	Map<String,Double> degats;
	String caracteristiques;
	
	public Map<String, Double> getDegats() {
		return degats;
	}

	public Arme() {
		degats = new HashMap<>();
	}

	public String toString() {
		return "Type d'arme: "+ this.getClass();
	}
}
