package main.armes;

public class Desarme extends Arme{

	public Desarme() {
		super();
		this.degats.put("Infanterie",0.0);
		this.degats.put("Bazooka",0.0);
		this.degats.put("Tank",0.0);
		this.degats.put("DCA",0.0);
		this.degats.put("Helicoptere",0.0);
		this.degats.put("Bombardier",0.0);
		this.degats.put("Convoi",0.0);
		// Bonus mais implanté pour ne pas causé de bug
		this.degats.put("Artillerie",0.0);
		
	}

}
