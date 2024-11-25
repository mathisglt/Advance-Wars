package main.armes;

public class MitrailleuseLegere extends Arme{

	public MitrailleuseLegere() {
		super();
		this.degats.put("Infanterie",60.0);
		this.degats.put("Bazooka",55.0);
		this.degats.put("Tank",15.0);
		this.degats.put("DCA",10.0);
		this.degats.put("Helicoptere",30.0);
		this.degats.put("Bombardier",0.0);
		this.degats.put("Convoi",40.0);
		// Bonus mais implanté pour ne pas causé de bug
		this.degats.put("Artillerie",50.0);
		
	}

}
