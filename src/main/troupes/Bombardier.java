package main.troupes;
import main.MoyenLoc.*;
import main.armes.*;
import ressources.Chemins;

public class Bombardier extends TypeUnite{
	public Bombardier() {
		super();
		setArme(new Bombes());
		this.setLoco(new Air());
		this.prix = 20000;
		this.image =  Chemins.FICHIER_BOMBARDIER;
		this.setDepmax(7);
		this.caracteristiques = "Avion qui peut rapidement réduire les unités terrestres en miettes. Ne peut pas attaquer les\n"
				+ "autres avions.";
	}

}
