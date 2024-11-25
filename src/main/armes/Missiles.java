package main.armes;

public class Missiles extends Arme{

	public Missiles() {
		super();
		this.degats.put("Infanterie",50.0);
		this.degats.put("Bazooka",50.0);
		this.degats.put("Tank",70.0);
		this.degats.put("DCA",40.0);
		this.degats.put("Helicoptere",70.0);
		this.degats.put("Bombardier",70.0);
		this.degats.put("Convoi",70.0);
		// Bonus mais implanté pour ne pas causé de bug
		this.degats.put("Artillerie",50.0);
	}

}
