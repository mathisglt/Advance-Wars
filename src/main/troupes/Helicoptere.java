package main.troupes;
import main.MoyenLoc.Air;
import main.armes.*;
import ressources.Chemins;

public class Helicoptere extends TypeUnite{
	public Helicoptere() {
		super();
		setArme(new Missiles());
		this.prix = 12000;
		this.image = Chemins.FICHIER_HELICOPTERE;
		this.setDepmax(6);
		this.setLoco(new Air());
		this.caracteristiques = "Unité aérienne la plus basique, son déplacement n’est pas affecté par le terrain. Efficace\n"
				+ "contre les blindés.\n";
	}

}
