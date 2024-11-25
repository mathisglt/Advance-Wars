package main.armes;

public class Canon extends Arme{

	public Canon() {
		super();
		this.degats.put("Infanterie",45.0);
		this.degats.put("Bazooka",45.0);
		this.degats.put("Tank",55.0);
		this.degats.put("DCA",60.0);
		this.degats.put("Helicoptere",30.0);
		this.degats.put("Bombardier",0.0);
		this.degats.put("Convoi",70.0);
		// Bonus mais implanté pour ne pas causé de bug
		this.degats.put("Artillerie",50.0);
		
	}

}
